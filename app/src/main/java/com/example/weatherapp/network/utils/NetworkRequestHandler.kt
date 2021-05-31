package com.example.weatherapp.network.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


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
    private var mDisposable: Disposable? = null
    private var databaseResult: LiveData<D_RESULT>
    internal val asLiveData: LiveData<Resource<D_RESULT>>
        get() = result // to observe result from ui

    init {
        result.value = Resource.loading(null) // start loading
        if (shouldFetch(null)) {
            // fetch updated data
            Log.e("NETWORK_HANDLER", "shouldFetch = true")
            fetchFromNetwork()
        }
        @Suppress("LeakingThis")
        databaseResult = loadFromDb()
        result.addSource(databaseResult) { data ->
            result.removeSource(databaseResult)
            if (shouldFetch(data)) {
                // fetch updated data
                Log.e("NETWORK_HANDLER", "shouldFetch = true")
                fetchFromNetwork()
            } else {
                result.addSource(databaseResult) { newData ->
                    result.setValue(
                        Resource.success("Success" ,newData, )
                    ) // source is updated don't call
                }
            }
        }
    }

    // fetch data from network using Rx
    private fun fetchFromNetwork() {
        result.addSource(databaseResult) { newData -> result.setValue(Resource.loading(newData)) }
        fetchData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<N_RESULT> {
                override fun onSubscribe(d: Disposable) {
                    if (!d.isDisposed) {
                        Log.e("fetchFromNetwork", "onSubscribe")
                        mDisposable = d
                    }
                }

                override fun onSuccess(response: N_RESULT) {
                    Log.e("fetchFromNetwork", "onSuccess")
                    result.removeSource(databaseResult) // remove un-updated source
                    saveResultAndReInit(response) // update source
                }

                override fun onError(e: Throwable) {
                    Log.e("fetchFromNetwork", "onError")
                    onFetchFailed()
                    result.removeSource(databaseResult)
                    result.addSource(databaseResult) { newData ->
                        result.setValue(Resource.error(e.message.toString(), newData))
                    }
                    mDisposable!!.dispose()
                }
            })
    }

    @MainThread
    private fun saveResultAndReInit(response: N_RESULT) {
        Completable
            .fromCallable { saveCallResult(response) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    if (!d.isDisposed) {
                        mDisposable = d
                    }
                }

                override fun onComplete() {
                    result.addSource(loadFromDb()) { newData ->
                        result.setValue(
                            Resource.success(
                                "Success",
                                newData
                            )
                        )
                    }
                    mDisposable!!.dispose()
                }

                override fun onError(e: Throwable) {
                    mDisposable!!.dispose()
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
