package com.example.clothes_shoppingkotlin.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
class ProductTable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "price")
    var price: Double? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "category")
    var category: String? = null

    @ColumnInfo(name = "image")
    var image: String? = null
}