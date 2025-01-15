package com.furkanakyel.visaapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val rememberMeCheckBox = findViewById<CheckBox>(R.id.cbRememberMe)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val isRemembered = sharedPreferences.getBoolean("isRemembered", false)

        if (isRemembered) {
            username.setText(savedUsername)
            password.setText(savedPassword)
            rememberMeCheckBox.isChecked = true
        }

        loginButton.setOnClickListener {
            val inputUsername = username.text.toString()
            val inputPassword = password.text.toString()

            if (inputUsername == "Furkan" && inputPassword == "123") {
                Toast.makeText(this, "Giriş Başarılı!", Toast.LENGTH_SHORT).show()

                if (rememberMeCheckBox.isChecked) {
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isRemembered", true)
                    editor.putString("username", inputUsername)
                    editor.putString("password", inputPassword)
                    editor.apply()
                } else {
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()
                }

                val intent = Intent(this, FoodListActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Hatalı Giriş!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
