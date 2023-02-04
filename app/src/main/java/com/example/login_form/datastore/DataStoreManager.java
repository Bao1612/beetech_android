package com.example.login_form.datastore;

import android.content.Context;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import com.example.login_form.UserToken;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class DataStoreManager {

    private Context context;
    RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(context, "UserToken").build();

    Preferences.Key<String> userToken = PreferencesKeys.stringKey("userToken");

    Flowable<String > exampleCounterFlow =
            dataStore.data().map(prefs -> prefs.get(userToken));

//    Single<Preferences> updateResult =  dataStore.updateDataAsync(prefsIn -> {
//        MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
//        Integer currentInt = prefsIn.get(UserToken.getToken());
//        mutablePreferences.set(UserToken.getToken(), currentInt != null ? currentInt + 1 : 1);
//        return Single.just(mutablePreferences);
//    });

}
