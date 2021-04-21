package com.example.clothes_shoppingkotlin.Model.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clothes_shoppingkotlin.Model.Basket
import com.example.clothes_shoppingkotlin.Model.ProductTable
import java.util.concurrent.Executors

@Database(entities = [ProductTable::class, Basket::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    //always going to have abstract function to retrieve instance of DAO
    abstract val productDao: ProductDao?

    abstract val basketDao: BasketDao?

    companion object {
        //Volatile keyword makes this instance available to all threads
        @Volatile
        private var PERSON_DATABASE_INSTANCE: ProductDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): ProductDatabase? {
            if (PERSON_DATABASE_INSTANCE == null) {
                //this allows our instantiating class to hold a lock on this object
                synchronized(ProductDatabase::class.java) {
                    if (PERSON_DATABASE_INSTANCE == null) {
                        PERSON_DATABASE_INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ProductDatabase::class.java,
                            "person_database"
                        ) //this destroys old database inside android device whenever new
                            //version is created
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return PERSON_DATABASE_INSTANCE
        }
    }
}