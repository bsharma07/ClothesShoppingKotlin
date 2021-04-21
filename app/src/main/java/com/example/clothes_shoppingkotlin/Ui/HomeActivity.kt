package com.example.clothes_shoppingkotlin.Ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.clothes_shoppingkotlin.Adapter.Adapter
import com.example.clothes_shoppingkotlin.Model.ProductTable
import com.example.clothes_shoppingkotlin.R
import com.example.clothes_shoppingkotlin.ViewModel.ProductViewModel

class HomeActivity : AppCompatActivity(), UserClickListener {
    private var mRecyclerView: RecyclerView? = null
    private var viewModel: ProductViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mRecyclerView = findViewById(R.id.user_recyclerview)
        viewModel = ProductViewModel(application, this)

        //viewModel.getApiCallback();
        viewModel!!.getmProducts().observe(
            this,
            Observer<List<ProductTable>> { productTables ->
                if (productTables.isEmpty()) {
                    viewModel!!.apiCallback()
                }
                val myAdapter = Adapter(this@HomeActivity, productTables, object : UserClickListener {
                    override fun onUserClicked(product: ProductTable?) {
                        this@HomeActivity.onUserClicked(product)
                    }
                })
                mRecyclerView!!.adapter = myAdapter
                mRecyclerView!!.layoutManager =
                    StaggeredGridLayoutManager(
                        2,
                        LinearLayoutManager.VERTICAL

                )
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onUserClicked(product: ProductTable?) {
        val mIntent = Intent(this, DetailActivity::class.java)
        val mBundle = Bundle()
        mBundle.putString("title", product!!.title)
        mBundle.putDouble("price", product.price!!)
        mBundle.putString("desc", product.description)
        mBundle.putString("image", product.image)
        mIntent.putExtras(mBundle)
        startActivity(mIntent)
    }
}