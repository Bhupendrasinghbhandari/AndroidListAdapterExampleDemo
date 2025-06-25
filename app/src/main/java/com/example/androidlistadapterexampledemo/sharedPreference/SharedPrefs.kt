package com.example.androidlistadapterexampledemo.sharedPreference

import android.content.SharedPreferences
import javax.inject.Inject


/**
 * [SharedPrefs]
 * Shared Preferences allow you to save and retrieve data in the form of key,value pair.
 * In order to use shared preferences, you have to call a method getSharedPreferences() that returns a
 * SharedPreference instance pointing to the file that contains the values of preferences.
 * */

class  SharedPrefs @Inject constructor(private var sharePref: SharedPreferences) {

    /**
     * Function which will save the integer value to preference with given key
     */
    fun save(@PrefKey preferenceKey: String, integerValue: Int) {
        saveToPreference(
            preferenceKey,
            integerValue
        )
    }

    /**
     * Function which will save the double value to preference with given key
     */
    fun save(@PrefKey preferenceKey: String, doubleValue: Float) {
        saveToPreference(
            preferenceKey,
            doubleValue
        )
    }

    /**
     * Function which will save the long value to preference with given key
     */
    fun save(@PrefKey preferenceKey: String, longValue: Long) {
        saveToPreference(
            preferenceKey,
            longValue
        )
    }

    /**
     * Function which will save the boolean value to preference with given key
     */
    fun save(@PrefKey preferenceKey: String, booleanValue: Boolean) {
        saveToPreference(
            preferenceKey,
            booleanValue
        )
    }

    /**
     * Function which will save the string value to preference with given key
     */
    fun save(@PrefKey preferenceKey: String, stringValue: String?) {
        stringValue?.let {
            saveToPreference(
                preferenceKey,
                stringValue
            )
        }
    }

    /**
     * General function to save preference value
     */
    private fun saveToPreference(@PrefKey key: String, value: Any) {
        with(sharePref.edit()) {
            when (value) {
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
            }
            apply()
        }
    }




    /**
     * Function which will return the string value saved in the preference corresponds to the given preference key
     */
    fun getString(@PrefKey preferenceKey: String): String {
        return sharePref.getString(preferenceKey, "") ?: ""
    }

    annotation class PrefKey

    /**
     * this function is use to clear preference according to [preferenceKey]
     * @param preferenceKey
     * */
    fun remove(@PrefKey preferenceKey: String) {
        with(sharePref.edit()) {
            remove(preferenceKey)
            apply()
        }
    }

    /**
     * this function use to clear Preference
     * */
    fun clearPreference() {
        with(sharePref.edit()) {
            clear()
            apply()
        }
    }
}