package com.example.clothes_shoppingkotlin.Network

import com.example.clothes_shoppingkotlin.Model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ShoppingInterface {
    @get:GET("products/")
    val shopping: Call<List<Product>>
}
