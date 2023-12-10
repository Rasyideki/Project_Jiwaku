package com.example.jiwaku.LoginSignup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.jiwaku.HomePage.MenuActivity
import com.google.firebase.auth.FirebaseAuth

class SessionManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    // Check if the user is already logged in
    fun checkLoginStatus(): Boolean {
        if (isLoggedIn()) {
            val intent = Intent(context, MenuActivity::class.java)
            context.startActivity(intent)
            return true
        }
        return false
    }

    // Function to save login status to SharedPreferences
    fun saveLoginStatus(isLoggedIn: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    // Function to check login status from SharedPreferences
    private fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    // Function to perform logout
    fun logout() {
        saveLoginStatus(false)
        firebaseAuth.signOut()
        // You can also add additional logic for clearing user data or performing other actions during logout
    }
}

