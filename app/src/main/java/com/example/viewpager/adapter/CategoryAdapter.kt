package com.example.viewpager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.model.Category


class CategoryAdapter (private val categoryList: ArrayList<Category>)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var onItemClick : ((Category) -> Unit)? = null
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgCategory : ImageView = itemView.findViewById(com.example.viewpager.R.id.category_image)
        val nameCategory : TextView = itemView.findViewById(com.example.viewpager.R.id.category_name)
        val totCategory : TextView = itemView.findViewById(com.example.viewpager.R.id.category_total)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.viewpager.R.layout.category_layout, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.imgCategory.setImageResource(category.imgCategory)
        holder.nameCategory.text = category.nameCategory
        holder.totCategory.text = category.totCategory

        holder.imgCategory.setOnClickListener {
            onItemClick?.invoke(category)
        }
    }


}