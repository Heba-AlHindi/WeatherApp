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
class LiveRealmResults<T : RealmModel?>(val realmResults: RealmResults<T>) :
    LiveData<RealmResults<T>>() {

    private val listener = RealmChangeListener<RealmResults<T>> { results -> value = results }

    // starts observing the RealmResults, if it is still valid.
    override fun onActive() {
        super.onActive()
        realmResults.addChangeListener(listener)

    }

    // stops observing the RealmResults.
    override fun onInactive() {
        super.onInactive()
        realmResults.removeChangeListener(listener)
    }
}