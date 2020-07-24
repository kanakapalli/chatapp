package com.sulphuricacid.anuragfamilyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class forgotpassword: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.forgotpassword)
    }
}