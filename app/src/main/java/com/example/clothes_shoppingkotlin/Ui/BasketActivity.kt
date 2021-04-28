package com.example.clothes_shoppingkotlin.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothes_shoppingkotlin.Adapter.BasketAdapter
import com.example.clothes_shoppingkotlin.Model.Basket
import com.example.clothes_shoppingkotlin.Model.ProductTable
import com.example.clothes_shoppingkotlin.R
import com.example.clothes_shoppingkotlin.ViewModel.ProductViewModel

class BasketActivity : AppCompatActivity(), UserClickListener {
    private var viewModel: ProductViewModel? = null
    private var mRecyclerView: RecyclerView? = null
    private var mTotalPrice: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        viewModel = ProductViewModel(application, this)
        mRecyclerView = findViewById(R.id.user_recyclerview)
        mTotalPrice = findViewById(R.id.totalPrice)

        viewModel!!.getmBaskets().observe(
                this,
                Observer<List<Basket>> { basket ->
                    if (basket.isNotEmpty()) {
                        val myAdapter = BasketAdapter(this@BasketActivity, basket, object : UserClickListener {
                            override fun onUserClicked(product: ProductTable?) {
                                TODO("Not yet implemented")
                            }

                            override fun onCloseClicked(basket: Basket?) {
                                this@BasketActivity.onCloseClicked(basket)
                            }
                        })
                        mRecyclerView!!.adapter = myAdapter
                        mRecyclerView!!.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

                        mTotalPrice!!.text = "£"+myAdapter.getTotalPrice().toString()
                    }
                })

    }

    override fun onUserClicked(product: ProductTable?) {
        TODO("Not yet implemented")
    }

    override fun onCloseClicked(product: Basket?) {
        TODO("Not yet implemented")
    }
}
