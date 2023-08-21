package com.example.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IntroSliderAdapter(private val introSlide : List<IntroSlider>)
    : RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>() {
    inner class IntroSliderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val textTitle = view.findViewById<TextView>(R.id.titleText)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageCon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introSlide: IntroSlider){
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageCon.setImageResource(introSlide.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
        return IntroSliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return introSlide.size
    }

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        holder.bind(introSlide[position])
    }
}