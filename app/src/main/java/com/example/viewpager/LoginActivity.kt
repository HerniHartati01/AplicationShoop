package com.example.viewpager

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.viewpager.session.PreferenceHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var textusername : EditText
    private lateinit var textpassword : EditText
    private lateinit var username : String
    private lateinit var password : String
    private lateinit var btnLogin : Button
    private lateinit var preferenceHelper: PreferenceHelper

    //Data Validation
    private var validUsername = "herni@gmail.com"
    private var validPassword = "1234"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        checkLogin()

        /*textusername = findViewById(R.id.username)
        textpassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.buttonLogin)*/

        /*btnLogin.setOnClickListener {
            val username = textusername.text.toString().trim()
            val password = textpassword.text.toString().trim()

            if (username.isEmpty()){
                textusername.error = "Username Required"
                return@setOnClickListener
            }else if (password.isEmpty()){
                textpassword.error = "Password Required"
                return@setOnClickListener
            }else{
                Intent(applicationContext, HomeActivity::class.java).also {
                    Toast.makeText(this, "Complete Login", Toast.LENGTH_SHORT).show()
                    startActivity(it)
                    finish()
                }
            }
        }*/

    }

    private fun init(){
        preferenceHelper = PreferenceHelper(this)
        textusername = findViewById(R.id.username)
        textpassword = findViewById(R.id.password)
    }

    private fun checkLogin(){
        if(preferenceHelper.isLogin()!!){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun clickLogin(view : View){
        username = textpassword.text.toString().trim()
        password = textpassword.text.toString().trim()

        if(username.isEmpty() || username == ""){
            textusername.error = "Username is required"
            textusername.requestFocus()
        } else if (password.isEmpty() || password == ""){
            textpassword.error = "Password is required"
            textpassword.requestFocus()
        } else if (username == validUsername){
            textusername.error = "Username is not valid"
            textusername.requestFocus()
        }else if (password != validPassword){
            textpassword.error = "Password is not valid"
            textpassword.requestFocus()
        }else{
            preferenceHelper.setLogin(true)
            preferenceHelper.setUsername(username)

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}