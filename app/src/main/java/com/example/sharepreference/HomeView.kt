package com.example.sharepreference

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.app.AlertDialog


class HomeView : AppCompatActivity() {
    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ ->
                finishAffinity()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val password1 = sharedPreferences.getString("password", "")
        val email1 = sharedPreferences.getString("email", "")
        val number1 = sharedPreferences.getString("number", "")
        val editor = sharedPreferences.edit()


        var firstname2 = findViewById<TextView>(R.id.FirstName2)
        firstname2.text = "Your name is " + name
        var phoneNum = findViewById<TextView>(R.id.PhoneNumber2)
        phoneNum.text = "Your number is " + number1
        var email2 = findViewById<TextView>(R.id.Email2)
        email2.text = "Your email is " + email1
        var password2 = findViewById<TextView>(R.id.Password2)
        password2.text = "Your Password is " + password1

        var button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            editor.putBoolean("homecheck", false)
            editor.apply()
            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)
            finish()
        }
    }
}