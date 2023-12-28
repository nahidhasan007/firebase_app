package com.example.authapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val name = findViewById<EditText>(R.id.signup_name)
        val email = findViewById<EditText>(R.id.signup_email)
        val username = findViewById<EditText>(R.id.signup_username)
        val password = findViewById<EditText>(R.id.signup_password)

        val signupBtn = findViewById<TextView>(R.id.btn_signup)
        val signInBtn = findViewById<TextView>(R.id.redirect_to_signIn)

        signupBtn.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val reference = database.getReference("users")
            val user = User(
                name.text.toString(),
                email.text.toString(),
                username.text.toString(),
                password.text.toString()
            )

            reference.child(username.text.toString()).setValue(user)
            Toast.makeText(this, "You have sign up successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        signInBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}