package com.example.clothes_shoppingkotlin.Model.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.clothes_shoppingkotlin.Model.Basket

@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(basket: Basket?)

    @Update
    fun updateBasket(vararg baskets: Basket?)

    @get:Query("SELECT * from basket ORDER BY id ASC")
    val allBasket: LiveData<List<Basket>>

    @Query("SELECT * FROM basket WHERE id LIKE :title LIMIT 1")
    fun getBasketById(title: String?): LiveData<Basket>

    @Query("DELETE FROM basket")
    fun deleteAll()
}