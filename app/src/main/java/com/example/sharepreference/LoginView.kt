package com.example.sharepreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginView : AppCompatActivity() {
    lateinit var user: EditText
    lateinit var pass: EditText
    lateinit var button1: Button
    lateinit var checkBoxRememberMe: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences1 = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        var homecheck = sharedPreferences1.getBoolean("homecheck", false)
        if(homecheck) {
            val intent = Intent(this, HomeView::class.java)
            startActivity(intent)
            return
        }


        setContentView(R.layout.activity_login_view)

        user = findViewById(R.id.User)
        pass = findViewById(R.id.Pass)
        button1 = findViewById(R.id.button1)
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe)
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val password1 = sharedPreferences.getString("password", "")
        val signup = sharedPreferences.getBoolean("signup", true)
        val rememberMe = sharedPreferences.getBoolean("rememberMe", false)
        checkBoxRememberMe.isChecked = rememberMe
        button1.setOnClickListener {

            val username = user.text.toString().trim()
            val password = pass.text.toString().trim()

            if( username != name ){
                Toast.makeText(this, "The field 'Name' is not valid", Toast.LENGTH_SHORT).show()
            }
            else if( password != password1){
                Toast.makeText(this, "The field 'Password' is not valid", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Fields are Valid", Toast.LENGTH_SHORT).show()
                if (checkBoxRememberMe.isChecked) {
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("homecheck", true)
                    editor.apply()
                }

                val intent = Intent(this, HomeView::class.java)
                startActivity(intent)
                finish()
            }

        }
        var button2 = findViewById<Button>(R.id.signup)
        if(signup){
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }}
        else{
            button2.setOnClickListener {
                Toast.makeText(this, "already signed up", Toast.LENGTH_SHORT).show() }
        }
        var button_forgot = findViewById<Button>(R.id.forgot)
        button_forgot.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}