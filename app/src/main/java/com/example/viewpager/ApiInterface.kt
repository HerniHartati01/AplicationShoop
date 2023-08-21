package com.example.viewpager

import com.example.viewpager.model.ProductApiItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("/products")
    fun getData() : Call<List<ProductApiItem>>

    @GET("products/{id}")
    fun getProductsByID(@Path("id") id: String?): retrofit2.Call<ProductApiItem>
}