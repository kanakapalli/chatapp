package com.sulphuricacid.anuragfamilyapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.signup.*
import java.util.*

class signIn: AppCompatActivity() {
    companion object {
        val TAG = "RegisterActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        signupbutton.setOnClickListener {
            performRegister()
        }
        imageView.setOnClickListener{
            Log.d(TAG, "Try to show photo selector")
            Log.d("d","selectphoto_button_register is $selectedPhotoUri")


            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    }
    var selectedPhotoUri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("d","onActivityResult 1 is $selectedPhotoUri")

        if (requestCode==0 && resultCode==Activity.RESULT_OK && data!=null){
            // proceed and check what the selected image was....
            Log.d(TAG, "Photo was selected")
            selectedPhotoUri = data.data
            Log.d("d","onActivityResult 2 is $selectedPhotoUri")

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            circleimageview.setImageBitmap(bitmap)

            imageView.alpha = 0f
//            val bitmapDrawable= BitmapDrawable(bitmap)
//            imageView.setBackgroundDrawable(bitmapDrawable)

            textView6.visibility = View.GONE
        }
    }


    private fun performRegister() {
        Log.d("d", "firebaseauth started")
        val email: EditText = findViewById(R.id.signupgmail)
        val password: EditText = findViewById(R.id.password)
        val conformPassword: EditText = findViewById(R.id.conformpassword)

        if (email.text.isEmpty() || password.text.toString()
                .isEmpty() || conformPassword.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "fill the email/password ", Toast.LENGTH_SHORT).show()
            return
        } else {
            if (password.text.toString() == conformPassword.text.toString()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    email.text.toString(),
                    conformPassword.text.toString()
                )
                    .addOnCompleteListener {
                        if (!it.isSuccessful || it.isCanceled) return@addOnCompleteListener

                        Log.d("d", "uid = ${it.result?.user?.uid}")
                        uploadImageToFirebaseStorage()
                        val Intent = Intent(this, login_screen::class.java)
                        startActivity(Intent)
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "Failed to create user: ${it.message}")
                        Toast.makeText(
                            this,
                            "Failed to create user: ${it.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }//check
    }
    private fun uploadImageToFirebaseStorage() {
        Log.d("d","uploadImageToFirebaseStorage  $selectedPhotoUri")

        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d(TAG, "Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    Log.d(TAG, "File Location: $it")
                    val uid = FirebaseAuth.getInstance().uid ?: ""
                    val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

                    val user = User(
                        uid,
                        signupgmail.text.toString(),
                        it.toString()
                    )
                    ref.setValue(user)
                        .addOnSuccessListener {
                            Log.d(TAG, "Finally we saved the user to Firebase Database")
                        }
                        .addOnFailureListener {
                            Log.d(TAG, "we failed to save data")
                            Log.d(TAG, "${it.message}")
                        }
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to upload image to storage: ${it.message}")
            }
    }


}
class User(val uid: String, val username: String, val profileImageUrl: String)








































