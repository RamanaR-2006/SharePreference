package com.example.sharepreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //declaring
        lateinit var name: EditText
        lateinit var email: EditText
        lateinit var password: EditText
        lateinit var confirm_password: EditText
        lateinit var number: EditText
        lateinit var button1: Button
        lateinit var sharedPreferences: SharedPreferences


        //function for email confirmation
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email)
                    && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing
        name = findViewById(R.id.name)
        email = findViewById(R.id.mail)
        password = findViewById(R.id.password)
        number = findViewById(R.id.number)
        confirm_password = findViewById(R.id.confirm_password)
        button1 = findViewById(R.id.button)
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        button1.setOnClickListener {
            val username = name.text.toString().trim()
            val email1 = email.text.toString().trim()
            val password1 = password.text.toString().trim()
            val number1 = number.text.toString().trim()
            val confirm_password1 = confirm_password.text.toString().trim()

            //click button, go from main activity to second activity, provided the fields are valid
            //field values stay same in second activities

            if(username.isEmpty()){
                Toast.makeText(this, "The field 'First Name' is blank", Toast.LENGTH_SHORT).show()
            }
            else if (!(isValidEmail(email1))){
                Toast.makeText(this, "The field 'Email' has not been entered in a proper format", Toast.LENGTH_SHORT).show()
            }
            else if(number1.length != 10){
                Toast.makeText(this, "The field 'Phone Number' has not been entered in a proper format", Toast.LENGTH_SHORT).show()
            }

            else if(password1.isEmpty()){
                Toast.makeText(this, "The field 'Password' is empty", Toast.LENGTH_SHORT).show()
            }
            else if(password1 != confirm_password1){
                Toast.makeText(this, "The fields 'Password' and 'Confirm password' are not the same", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "The fields are all valid", Toast.LENGTH_SHORT).show()
                val editor = sharedPreferences.edit()
                editor.putString("name", username)
                editor.putString("email", email1)
                editor.putString("number", number1)
                editor.putString("password", password1)
                editor.putBoolean("signup", false)
                editor.apply()
                val intent = Intent(this, LoginView::class.java)
                startActivity(intent)
                finish()
            }
        }


    }
}