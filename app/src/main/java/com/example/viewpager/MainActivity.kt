package com.example.viewpager

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {

    private val IntroSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlider(
                "Sunlight",
                "Sunlight is the light and energy that comes from the sun.",
                R.drawable.welcome1
            ),
            IntroSlider(
                "Pay Online",
                "Electronic bill payment is a feature of online, mobile and telephone banking.",
                R.drawable.welcome2
            ),
            IntroSlider(
                "Video Streaming",
                "Streaming media is multimedia that is constantly received by and presented to an end-User.",
                R.drawable.welcome3
            )

        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        val viewPager2 : ViewPager2 = findViewById(R.id.introSliderViewPager)
        viewPager2.adapter = IntroSliderAdapter
        dotsIndicator.attachTo(viewPager2)

        val buttonNext : Button = findViewById(R.id.buttonNext)
        buttonNext.setOnClickListener {
            if(viewPager2.currentItem + 1 < IntroSliderAdapter.itemCount){
                viewPager2.currentItem += 1
            } else{
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        val textSkipIntro : TextView = findViewById(R.id.textSkipIntro)
        textSkipIntro.setOnClickListener {
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

    }

}