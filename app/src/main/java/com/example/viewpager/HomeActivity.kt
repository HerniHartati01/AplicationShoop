package com.example.viewpager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.adapter.CategoryAdapter
import com.example.viewpager.adapter.ProductAdapter
import com.example.viewpager.model.Category
import com.example.viewpager.model.Product
import com.example.viewpager.session.PreferenceHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {


    //BottomNavBar
    private val profileFragment = ProfileFragment()
    private lateinit var bottomNav : BottomNavigationView
    private val shopFragment = ShopFragment()
    private val favoriteFragment = FavoriteFragment()

    //Shared Preference Login
    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var username : String
    private lateinit var tvdata : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        checkLogin()


        supportFragmentManager.beginTransaction().replace(R.id.container,ShopFragment()).commit()

        //BottomNavBar
        bottomNav = findViewById(R.id.botttom_nav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_account -> {
                    replaceFragment(profileFragment)
                    true
                }
                R.id.nav_shop ->{
                    replaceFragment(shopFragment)
                    true
                }
                R.id.nav_fav ->{
                    replaceFragment(favoriteFragment)
                    true
                }
            }
            true
        }


    }

    //Replace container Fragment with Bottom Nav bar
    private fun replaceFragment(fragment : Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }

    private fun init(){
        preferenceHelper = PreferenceHelper(this)
        username = preferenceHelper.getUsername().toString()
       /* tvdata = findViewById(R.id.emailProfile)*/

    }

    private fun checkLogin(){
        if (preferenceHelper.isLogin() == false){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }





}



