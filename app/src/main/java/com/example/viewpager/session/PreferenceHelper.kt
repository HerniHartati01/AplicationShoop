package com.example.viewpager.session

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context?) {

    //Shared pref mode
    val PRIVATE_MODE =  0

    //Shared pref File Name
    private val PREF_NAME = "SharedPreference"
    private val IS_LOGIN = "ls_login"

    val pref : SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    val editor : SharedPreferences.Editor? = pref?.edit()

    fun setLogin(isLogin : Boolean){
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }

    fun setUsername(username : String){
        editor?.putString("username", username)
        editor?.commit()
    }

    fun isLogin() : Boolean?{
        return pref?.getBoolean(IS_LOGIN, false)
    }

    fun getUsername() : String?{
        return pref?.getString("username", "")
    }

    fun removeData(){
        editor?.clear()
        editor?.commit()
    }
}