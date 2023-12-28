package com.example.authapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        username = findViewById(R.id.login_username)
        password = findViewById(R.id.login_password)
        val loginBtn = findViewById<Button>(R.id.btn_login)
        val signUpredirect = findViewById<TextView>(R.id.redirect_to_signup)

        loginBtn.setOnClickListener {
            if (validateUserNameAndPassword()) {
                checkUser()
            } else {
                Toast.makeText(this, "UserName and password can not be empty!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        signUpredirect.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    fun validateUserNameAndPassword(): Boolean {
        return !(username.text.toString().isEmpty() || password.text.toString().isEmpty())
    }

//    fun checkUser() {
//        val dbReference = FirebaseDatabase.getInstance().getReference("users")
//
//        val userIdToCheck = dbReference.orderByChild("userName").equalTo(username.text.toString()) // Replace with the user ID you want to check
//
//        dbReference.child(userIdToCheck.toString()).addListenerForSingleValueEvent(object :
//            ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // User exists in the database
//                    // Handle the existence of the user
//                    val passFromDb =
//                        dataSnapshot.child(username.text.toString()).child("password").value
//                    if (passFromDb == password.text.toString()) {
//                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                        startActivity(intent)
//                    } else {
//                        password.error = "Invalid Credentials!"
//                        password.requestFocus()
//                    }
//                } else {
//                    // User does not exist in the database
//                    // Handle the non-existence of the user
//                    username.setError("User does not exists!")
//                    username.requestFocus()
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Handle any errors that may occur
//            }
//        })
//
//    }

    fun checkUser() {
        val dbReference = FirebaseDatabase.getInstance().getReference("users")

        val userNameToCheck = username.text.toString()

        val query = dbReference.orderByChild("userName").equalTo(userNameToCheck)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        // Handle the user data or perform actions here
                        // For example, you might check the password:
                        val passFromDb = user?.password

                        if (passFromDb == password.text.toString()) {
                            // Password matches, proceed with login
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            // Invalid credentials
                            password.error = "Invalid Credentials!"
                            password.requestFocus()
                        }
                    }
                } else {
                    // User not found
                    username.error = "User does not exist!"
                    username.requestFocus()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur
            }
        })
    }
}