package com.example.authapp

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun onBind(user: User){
        itemView.findViewById<TextView>(R.id.name).text = user.name
        itemView.findViewById<TextView>(R.id.email).text = user.email
    }
}