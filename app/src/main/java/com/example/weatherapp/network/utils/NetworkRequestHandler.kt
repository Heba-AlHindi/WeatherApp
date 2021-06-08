package com.example.weatherapp.network.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.RealmResults

/**
 *  network handler class
 *  manage fetching data from network and caching it
 */

abstract class NetworkRequestHandler<D_RESULT, N_RESULT> {
    /**
     *  result = MediatorLiveData<Resource<Result>>()
     *   -> can add or remove sources plus observe its changes
     *      sources : database / network.
     */
    private val result = MediatorLiveData<Resource<D_RESULT>>()
    private var _databaseResult = MutableLiveData<D_RESULT>()
    private val databaseResult: LiveData<D_RESULT> get() = _databaseResult
    internal val asLiveData: LiveData<Resource<D_RESULT>>
        get() = result // to observe result from ui

    init {
        result.value = Resource.loading(null)
        fetchFromDataBase()
        if (shouldFetch(databaseResult.value)) {
            // fetch updated data
            Log.e("NETWORK_HANDLER", "shouldFetch = true")
            fetchFromNetwork(databaseResult)
        } else {
            result.addSource(databaseResult) { newData ->
                result.setValue(
                    Resource.success("Success", newData)
                ) // source is updated don't call
            }
        }
    }

    // fetch data from network using Rx
    private fun fetchFromNetwork(dbResult: LiveData<D_RESULT>) {
        result.addSource(dbResult) { newData -> result.setValue(Resource.loading(newData)) }
        fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<N_RESULT> {
                override fun onSubscribe(d: Disposable) {
                    if (!d.isDisposed) {
                        Log.e("fetchFromNetwork", "onSubscribe")
                    }
                }

                override fun onSuccess(response: N_RESULT) {
                    // handle success
                    result.removeSource(dbResult)
                    saveResultAndReInit(response) // update source
                    Log.e("fetchFromNetwork", "onSuccess")
                }

                override fun onError(e: Throwable) {
                    // handle error
                    onFetchFailed()
                    result.removeSource(databaseResult)
                    result.addSource(databaseResult) { newData ->
                        result.setValue(Resource.error(e.message.toString(), newData))
                    }
                    Log.e("fetchFromNetwork", "onError")
                }
            })
    }

    private fun saveResultAndReInit(response: N_RESULT) {
        Completable
            .fromCallable { saveCallResult(response) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    Log.e("saveResultAndReInit", "onSubscribe")
//                    if (!d.isDisposed) {
//                    }
                }

                override fun onComplete() {
                    fetchFromDataBase()
                    Log.e("saveResultAndReInit", "onComplete")
                }

                override fun onError(e: Throwable) {
                    Log.e("saveResultAndReInit", "onError")
                }
            })
    }

    private fun fetchFromDataBase() {
        Single.fromCallable { loadFromDb() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<LiveData<D_RESULT>> {
                override fun onSubscribe(d: Disposable) {
                    if (!d.isDisposed) {
                        Log.e("fetchFromDataBase", "onSubscribe")
                    }
                }

                override fun onSuccess(response: LiveData<D_RESULT>) {
                    // handle success
                    _databaseResult.value = response.value
                    result.removeSource(response)
                    result.addSource(response) { newData ->
                        result.setValue(
                            Resource.success(
                                "Success",
                                newData
                            )
                        )
                    }
                    Log.e("fetchFromDataBase", "onSuccess")
                }

                override fun onError(e: Throwable) {
                    // handle error
                    onFetchFailed()
                    Log.e("fetchFromDataBase", e.message.toString())
                }
            })
    }

    // abstract method to handle process of fetching
    @MainThread
    protected abstract fun shouldFetch(data: D_RESULT?): Boolean // check network availability

    @MainThread
    protected abstract fun fetchData(): Single<N_RESULT> // call api service

    @MainThread
    protected abstract fun onFetchFailed() // called when error occurred during fetching

    @MainThread
    protected abstract fun loadFromDb(): LiveData<D_RESULT> // load cached data from DB

    @WorkerThread
    protected abstract fun saveCallResult(item: N_RESULT) // save network result into database
}
