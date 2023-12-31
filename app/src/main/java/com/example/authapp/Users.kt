package com.example.authapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Users : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val userdata = findViewById<RecyclerView>(R.id.users_list)

        val dbReference = FirebaseDatabase.getInstance().getReference("users")

        var userList = mutableListOf<User>()

        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Replace User with your data model
                for (userSnapshot in dataSnapshot.children) {
                    val user =
                        userSnapshot.getValue(User::class.java) // Replace User::class.java with your data model class
                    user?.let {
                        userList.add(it)
                    }
                    val adapter = UserAdapter(userList)
                    userdata.adapter = adapter
                    Log.e("user_list", userList.toString())
                }
                // Process the list of users here (userList)
                // For example, you can iterate through userList or perform other operations
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle potential errors
            }
        })


    }
}