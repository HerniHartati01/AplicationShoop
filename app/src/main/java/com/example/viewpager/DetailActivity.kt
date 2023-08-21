package com.example.viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.viewpager.model.Favorite
import com.example.viewpager.model.Product
import com.example.viewpager.model.ProductApiItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /*val product = intent.getParcelableExtra<Product>("nameProduct")
        if (product != null){
            val textView : TextView = findViewById(R.id.detailText)
            val priceTextView : TextView = findViewById(R.id.priceDetail)
            val imageView : ImageView = findViewById(R.id.detailImage)

            textView.text = product.nameProduct
            priceTextView.text=product.priceProduct
            imageView.setImageResource(product.imgProduct)
        }*/

        val product = intent.getParcelableExtra<ProductApiItem>("intent_title")
        if (product != null){
            val textView : TextView = findViewById(R.id.detailText)
            val priceTextView : TextView = findViewById(R.id.priceDetail)
            val description : TextView = findViewById(R.id.descriptionText)
            val imageView : ImageView = findViewById(R.id.detailImage)

            textView.text = product.title
            priceTextView.text=product.price
            description.text = product.description
            Glide.with(this@DetailActivity).load(product.image).into(imageView)
        }



        val backImg : ImageView = findViewById(R.id.back)
        backImg.setOnClickListener {
            finish()
        }

        val btnFavorite : Button = findViewById(R.id.addFavorite)
        btnFavorite.setOnClickListener {
            if (product != null) {
                val newFavorite = Favorite(
                    image = product.image,
                    title = product.title,
                    description = product.description,
                    price = product.price
                )
                lifecycleScope.launch {
                    withContext(Dispatchers.IO){
                        App.database.favoriteDao().insertFavoriteProduct(newFavorite)
                    }
                }
                Toast.makeText(this, "Added To Favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }
}