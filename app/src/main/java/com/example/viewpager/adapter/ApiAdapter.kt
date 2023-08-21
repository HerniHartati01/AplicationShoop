package com.example.viewpager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewpager.R
import com.example.viewpager.model.Product
import com.example.viewpager.model.ProductApiItem

class ApiAdapter(var context: Context, var list: List<ProductApiItem>)
    : RecyclerView.Adapter<ApiAdapter.ApiViewHolder>() {


    var onItemClick : ((ProductApiItem) -> Unit)? = null
    inner class ApiViewHolder(v : View) : RecyclerView.ViewHolder(v)
    {
        var imgProduct = v.findViewById<ImageView>(R.id.product_image)
        var nameProduct = v.findViewById<TextView>(R.id.product_name)
        var priceProduct = v.findViewById<TextView>(R.id.product_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.product_layout, parent, false)
        return ApiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {

        val product = list[position]
        //load Image with Glide
        Glide.with(context).load(product.image).into(holder.imgProduct)

        holder.nameProduct.text = product.title
        holder.priceProduct.text = product.price

        holder.imgProduct.setOnClickListener {
            onItemClick?.invoke(product)
        }

    }


}