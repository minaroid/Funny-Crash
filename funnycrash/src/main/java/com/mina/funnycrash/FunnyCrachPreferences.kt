/*
 * Created by Mina George on 2020.
 */

package com.mina.funnycrash

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class FunnyCrachPreferences(private val context: Context) {

    /**
     * this method used to save new boolean or update an existing one
     *
     * @param key   that will identifies the passed element
     * @param value that needs to be persisted
     */
    fun saveOrUpdateBoolean(key: String, value: Boolean) {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * this method used to retrieve a boolean value
     *
     * @param key that identifies the element
     * @return the value or false if not found
     */
    fun getBoolean(key: String): Boolean {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        return sp.getBoolean(key, false)
    }

    /**
     * this method used to save new long or update an existing one
     *
     * @param key   that will identifies the passed element
     * @param value that needs to be persisted
     */
    fun saveOrUpdateLong(key: String, value: Long) {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * this method used to retrieve a long value
     *
     * @param key that identifies the element
     * @return the value or -1 if not found
     */
    fun getLong(key: String): Long {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        return sp.getLong(key, -1)
    }

    /**
     * this method used to save new int or update an existing one
     *
     * @param key   that will identifies the passed element
     * @param value that needs to be persisted
     */
    fun saveOrUpdateInt(key: String, value: Int) {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * this method used to retrieve aa int value
     *
     * @param key that identifies the element
     * @return the value or -1 if not found
     */
    fun getInt(key: String, defaultValue: Int): Int {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        return sp.getInt(key, defaultValue)
    }

    /**
     * this method used to save new string or update an existing one
     *
     * @param key   that will identifies the passed element
     * @param value that needs to be persisted
     */
    fun saveOrUpdateString(key: String, value: String) {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * this method used to retrieve a string value
     *
     * @param key that identifies the element
     * @return the value or null if not found
     */
    fun getString(key: String): String? {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        return sp.getString(key, null)
    }

    fun getString(key: String, defaultStr: String): String? {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        return sp.getString(key, defaultStr)
    }

    /**
     * this method used to delete specific element from the sharedPreferences
     *
     * @param key that identifies the element
     */
    fun delete(key: String) {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        editor.apply()
    }

    /**
     * this method used to delete all the elements from the sharedPreferences
     */
    fun deleteAll() {
        val sp = context.getSharedPreferences(PREFERENCES, Activity.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        editor.apply()
    }

    companion object {

        private val PREFERENCES = "funnyCrashPreferences"
    }
}
