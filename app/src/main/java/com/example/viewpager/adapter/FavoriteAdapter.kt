package com.example.viewpager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewpager.R
import com.example.viewpager.model.Favorite

class FavoriteAdapter(private var favorite : List<Favorite> ) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){

    var onItemClick : ((Favorite) -> Unit)? = null
    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imagefav : ImageView = itemView.findViewById(R.id.product_image)
        private val nameFav : TextView = itemView.findViewById(R.id.product_name)
        private val priceFav : TextView = itemView.findViewById(R.id.product_price)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val favorite = favorite[position]
                    onItemClick?.invoke(favorite)
                }
            }
        }

        fun bind(favorite: Favorite){
            nameFav.text = favorite.title
            priceFav.text = favorite.price

            Glide.with(itemView.context)
                .load(favorite.image)
                .into(imagefav)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
       val favorite = favorite[position]
        holder.bind(favorite)
    }

    fun updateData(newFavorite : List<Favorite>){
        favorite = newFavorite
        notifyDataSetChanged()
    }

}