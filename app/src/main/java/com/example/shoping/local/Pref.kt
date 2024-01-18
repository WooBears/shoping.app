package com.example.shoping.local

import android.content.Context

class Pref(context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)


    fun saveLogin(login: String)
    {
        pref.edit().putString(LOGIN_GET, login).apply()
    }

    fun getLogin():String?
    {
        return pref.getString(LOGIN_GET,"")
    }

    fun savePassword(password: String)
    {
        pref.edit().putString(PASSWORD_GET,password).apply()
    }

    fun getPassword():String?
    {
        return pref.getString(PASSWORD_GET,"")
    }

    companion object
    {
        const val PREF_NAME = "pref.folder.name"
        const val LOGIN_GET = "login.name"
        const val PASSWORD_GET = "password.name"
    }
}