package com.example.clothes_shoppingkotlin.Model.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.clothes_shoppingkotlin.Model.ProductTable

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductTable?)

    @Update
    fun updateProduct(vararg products: ProductTable?)

    @get:Query("SELECT * from product_table ORDER BY id ASC")
    val allPrice: LiveData<List<ProductTable>>

    @Query("SELECT * FROM product_table WHERE title LIKE :title LIMIT 1")
    fun getSpecificProduct(title: String?): LiveData<ProductTable>

    @Query("DELETE FROM product_table")
    fun deleteAll()
}