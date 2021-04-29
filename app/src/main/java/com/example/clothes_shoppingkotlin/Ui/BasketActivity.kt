package com.example.clothes_shoppingkotlin.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothes_shoppingkotlin.Adapter.BasketAdapter
import com.example.clothes_shoppingkotlin.Model.Basket
import com.example.clothes_shoppingkotlin.Model.ProductTable
import com.example.clothes_shoppingkotlin.R
import com.example.clothes_shoppingkotlin.Utils.toast
import com.example.clothes_shoppingkotlin.ViewModel.ProductViewModel

class BasketActivity : AppCompatActivity(), UserClickListener {
    private var viewModel: ProductViewModel? = null
    private var mRecyclerView: RecyclerView? = null
    private var mEmpty: LinearLayout? = null
    private var mPrice: LinearLayout? = null
    private var mTotalPrice: TextView? = null
    private lateinit var myAdapter: BasketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ProductViewModel(application, this)
        mRecyclerView = findViewById(R.id.user_recyclerview)
        mPrice = findViewById(R.id.price_hold)
        mEmpty = findViewById(R.id.empty_hold)
        mTotalPrice = findViewById(R.id.totalPrice)

        viewModel!!.getmBaskets().observe(
                this,
                Observer<List<Basket>> { basket ->
                    if (basket.isNotEmpty()) {
                        myAdapter = BasketAdapter(this@BasketActivity, basket, object : UserClickListener {
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

                        mRecyclerView!!.visibility = VISIBLE
                        mEmpty!!.visibility = GONE
                        mPrice!!.visibility = VISIBLE
                    }
                    else{
                        mRecyclerView!!.visibility = GONE
                        mEmpty!!.visibility = VISIBLE
                        mPrice!!.visibility = GONE
                    }
                })

    }

    override fun onUserClicked(product: ProductTable?) {
        TODO("Not yet implemented")
    }

    override fun onCloseClicked(basket: Basket?) {
        viewModel!!.deleteBasketItem(basket!!.id!!)
        myAdapter.notifyDataSetChanged()
        toast("Item removed from basket")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
