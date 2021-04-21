package com.example.clothes_shoppingkotlin.Model

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.clothes_shoppingkotlin.Model.Database.BasketDao
import com.example.clothes_shoppingkotlin.Model.Database.ProductDao
import com.example.clothes_shoppingkotlin.Model.Database.ProductDatabase

class ProductRepository(application: Application?) {
    private val mProductDao: ProductDao
    private val mBasketDao: BasketDao

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allProduct: LiveData<List<ProductTable>>

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(product: ProductTable?) {
        ProductDatabase.databaseWriteExecutor.execute { mProductDao.insert(product) }
    }

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    init {
        val db = ProductDatabase.getDatabase(application!!)
        mBasketDao = db!!.basketDao!!
        mProductDao = db.productDao!!
        allProduct = mProductDao.allPrice
    }
}
