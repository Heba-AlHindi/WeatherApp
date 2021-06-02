package com.example.weatherapp.database

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults


/**
 *  This class represents a RealmResults wrapped inside a LiveData.
 */
class LiveRealmResults<T : RealmModel?> @MainThread constructor(results: RealmResults<T>) :
    LiveData<RealmResults<T>>() {

    private val results: RealmResults<T>

    // The listener notifies the observers whenever a change occurs.
    // This could be expanded to also return the change set in a pair.
    private val listener =
        OrderedRealmCollectionChangeListener<RealmResults<T>> { results, changeSet ->
            this@LiveRealmResults.setValue(
                results
            )
        }


    // starts observing the RealmResults, if it is still valid.
    override fun onActive() {
        super.onActive()
        if (results.isValid) {
            results.addChangeListener(listener)
        }
    }


    // stops observing the RealmResults.
    override fun onInactive() {
        super.onInactive()
        if (results.isValid) {
            results.removeChangeListener(listener)
        }
    }


    // wraps the provided managed RealmResults as a LiveData.
    init {
        require(results.isManaged) { "LiveRealmResults only supports managed RealmModel instances!" }
        require(results.isValid) { "The provided RealmResults is no longer valid because the Realm instance that owns it is closed. It can no longer be observed for changes." }
        this.results = results
        if (results.isLoaded) {
            value = results
        }
    }
}