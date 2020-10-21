package com.ioia.book.utils.support

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


class SessionManager {
    private var context: Context
    private val PRIVATE_MODE = 0

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor? = null

    private val ACCOUNT_DATA_KEY = "com.ioia.book.utils.support.SessionManager.ACCOUNT_DATA_KEY"
    private val ACCOUNT_PHONE_KEY = "com.ioia.book.utils.support.SessionManager.ACCOUNT_PHONE_KEY"
    private val ACCOUNT_TOKEN_KEY = "com.ioia.book.utils.support.SessionManager.ACCOUNT_TOKEN_KEY"

    @SuppressLint("CommitPrefEdits")
    constructor(context: Context) {
        this.context = context
        pref = context.getSharedPreferences(ACCOUNT_DATA_KEY, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun createLoginSession(phone: String, token: String) {
        editor?.putString(ACCOUNT_PHONE_KEY, phone)
        editor?.putString(ACCOUNT_TOKEN_KEY, token)
        editor?.commit()
    }

    fun updateToken(token: String) {
        editor?.putString(ACCOUNT_TOKEN_KEY, token)
        editor?.commit()
    }

    fun clearSession() {
        editor?.clear()
        editor?.commit()
    }

    fun getPhone(): String?{
        return pref.getString(ACCOUNT_PHONE_KEY, null)
    }

    fun getToken(): String?{
        return pref.getString(ACCOUNT_TOKEN_KEY, null)
    }
}