package com.sulphuricacid.anuragfamilyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class new_message_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message_activity)
        supportActionBar?.title = "select user"
    }
}
