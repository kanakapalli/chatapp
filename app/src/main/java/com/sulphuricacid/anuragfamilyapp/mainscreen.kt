package com.sulphuricacid.anuragfamilyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class mainscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar!!.hide()
        setContentView(R.layout.activity_mainscreen)
        verifyuser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_mgs -> {
                val intent = Intent(this,
                    new_message_activity::class.java)
                startActivity(intent)
            }
            R.id.signout -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this,
                    login_screen::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun verifyuser() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null){
            val intent = Intent(this,
                login_screen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
