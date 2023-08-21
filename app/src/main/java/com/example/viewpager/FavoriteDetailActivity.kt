package com.example.viewpager

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.viewpager.model.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_detail)

        val favorite = intent.getParcelableExtra<Favorite>("favorite")
        Log.d("FavoriteDetailActivity", "Favorite : $favorite")

        val favTitle = findViewById<TextView>(R.id.favTitleText)
        val favPrice = findViewById<TextView>(R.id.favPriceDetail)
        val favImage = findViewById<ImageView>(R.id.favDetailImage)
        val favDecription = findViewById<TextView>(R.id.favDescriptionText)

        if (favorite != null){
            favTitle.text = favorite.title
            favPrice.text = favorite.price
            favDecription.text = favorite.description
            Glide.with(this)
                .load(favorite.image)
                .into(favImage)
        } else{
            Log.e("FavoriteDetailActivity", "Favorite Is Null")
        }

        val backImg : ImageView = findViewById(R.id.favBack)
        backImg.setOnClickListener {
            finish()
        }

        val btnDeleteFavorite : Button = findViewById(R.id.deleteFav)
        btnDeleteFavorite.setOnClickListener {
            if(favorite != null){
                lifecycleScope.launch {
                    withContext(Dispatchers.IO){
                        App.database.favoriteDao().deleteFavoriteProduct(favorite)
                    }
                }
                Toast.makeText(this, "Removed Favorite Product", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}