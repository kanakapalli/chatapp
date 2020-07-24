package com.sulphuricacid.anuragfamilyapp

import android.content.Context
import android.content.SharedPreferences

class SharedPref  (context: Context){
    val PREFERENCE_APP= "Preference_App"
    val PREFRENCE_LOGIN= "UserName"
    val PREFRENCE_Password= "Password"

    val sharedPreferences= context.getSharedPreferences(PREFERENCE_APP, Context.MODE_PRIVATE)

    fun saverDetail(email:String,password:String){

        val editor=sharedPreferences.edit()
        editor.putString(PREFRENCE_LOGIN,email)
        editor.putString(PREFRENCE_Password,password)

editor.apply()
    }
    fun getuserdetails():String
    {
       return PREFRENCE_LOGIN
    }
    fun getPassword():String{
        return PREFRENCE_Password
    }

}