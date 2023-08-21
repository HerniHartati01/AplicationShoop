package com.example.viewpager

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAGS_CHANGED
        )

        if (isFirstLaunch(this)) {
            saveFirstLaunchStatus(this, true)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (isFirstLaunch(this)) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, HomeActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 3000)
    }

    fun saveFirstLaunchStatus(context: Context, isFirstLaunch: Boolean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isFirstLaunch", isFirstLaunch)
        editor.apply()
    }

    fun isFirstLaunch(context: Context): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFirstLaunch", true)
    }


}