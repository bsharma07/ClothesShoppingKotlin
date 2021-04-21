package com.example.clothes_shoppingkotlin.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.clothes_shoppingkotlin.Model.Product
import com.example.clothes_shoppingkotlin.Model.ProductRepository
import com.example.clothes_shoppingkotlin.Model.ProductTable
import com.example.clothes_shoppingkotlin.Network.RetrofitClient
import com.example.clothes_shoppingkotlin.Network.ShoppingInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ProductViewModel(application: Application?, context: Context) : ViewModel() {

    private val mRepository: ProductRepository = ProductRepository(application)
    private val mProducts: LiveData<List<ProductTable>> = mRepository.allProduct
    private val mContext by lazy { context }

    fun getmProducts(): LiveData<List<ProductTable>> {
        return mProducts
    }

        //here we'll define what we want to do if the network call is unsuccessful
        //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        //this is where we get our response

        //this service is what we use to make our network call
    fun apiCallback(){
        val retrofitClient: Retrofit? = RetrofitClient.getClient(mContext)
        val songService: ShoppingInterface =
            retrofitClient!!.create(ShoppingInterface::class.java)

        //this service is what we use to make our network call
        songService.shopping.enqueue(object : Callback<List<Product>> {
                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    if (response.isSuccessful) {
                        //this is where we get our response
                        for (i in response.body()!!.indices) {
                            val product: Product = response.body()!![i]
                            val productTable = ProductTable()
                            productTable.id = product.id
                            productTable.title = product.title
                            productTable.price = product.price
                            productTable.description = product.description
                            productTable.image = product.image
                            productTable.category = product.category
                            mRepository.insert(productTable)
                        }
                    } else {
                        //Log.e("api_error", response.message())
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    //here we'll define what we want to do if the network call is unsuccessful
                    //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            })
    }

}
