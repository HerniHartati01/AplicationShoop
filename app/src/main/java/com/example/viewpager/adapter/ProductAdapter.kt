package com.example.viewpager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.model.Product


class ProductAdapter (private val productList: ArrayList<Product>)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var onItemClick : ((Product) -> Unit)? = null
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgProduct : ImageView = itemView.findViewById(com.example.viewpager.R.id.product_image)
        val nameProduct : TextView = itemView.findViewById(com.example.viewpager.R.id.product_name)
        val priceProduct : TextView = itemView.findViewById(com.example.viewpager.R.id.product_price)
        val imgBtnLike : ImageButton = itemView.findViewById(com.example.viewpager.R.id.btnLike)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.viewpager.R.layout.product_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.imgProduct.setImageResource(product.imgProduct)
        holder.nameProduct.text = product.nameProduct
        holder.priceProduct.text = product.priceProduct
        holder.imgBtnLike.setImageResource(product.imgBtnLike)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)
        }
    }
}