package com.example.weatherapp.network.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
     *
     *   update 09/6/21 : since the app deals with database as the main source to return to the ui
     *   and observe I changed it to MutableLiveData.
     *
     */
    private val result = MutableLiveData<Resource<D_RESULT>>()
    internal val asLiveData: LiveData<Resource<D_RESULT>>
        get() = result // to observe result from ui

    init {
        result.value = Resource.loading(null)
        fetchFromDataBase()
    }

    // fetch data from network using Rx
    private fun fetchFromNetwork(dbResult: D_RESULT) {
        result.value = Resource.loading(dbResult)
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
                    saveResultAndReInit(response) // update source
                    Log.e("fetchFromNetwork", "onSuccess")
                }

                override fun onError(e: Throwable) {
                    // handle error
                    result.value = Resource.error(e.message.toString(), dbResult)
                    onFetchFailed()
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
            .subscribe(object : SingleObserver<D_RESULT> {
                override fun onSubscribe(d: Disposable) {
                    if (!d.isDisposed) {
                        Log.e("fetchFromDataBase", "onSubscribe")
                    }
                }

                override fun onSuccess(response: D_RESULT) {
                    // handle success
                    Log.e("fetchFromDataBase", "onSuccess")
                    // handle caching
                    if (shouldFetch(response)) {
                        // fetch updated data
                        Log.e("shouldFetch", "")
                        fetchFromNetwork(response)
                    } else {
                        result.value = Resource.success("Success", response)
                    }
                }

                override fun onError(e: Throwable) {
                    // handle error
                    Log.e("fetchFromDataBase", e.message.toString())
                    onFetchFailed()
                }
            })
    }

    // abstract method to handle process of fetching
    @MainThread
    protected abstract fun shouldFetch(data: D_RESULT): Boolean // check network availability

    @MainThread
    protected abstract fun fetchData(): Single<N_RESULT> // call api service

    @MainThread
    protected abstract fun onFetchFailed() // called when error occurred during fetching

    @MainThread
    protected abstract fun loadFromDb(): D_RESULT // load cached data from DB

    @WorkerThread
    protected abstract fun saveCallResult(item: N_RESULT) // save network result into database
}
