package com.example.clothes_shoppingkotlin.Ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.clothes_shoppingkotlin.R
import com.example.clothes_shoppingkotlin.Utils.toast
import com.example.clothes_shoppingkotlin.ViewModel.ProductViewModel

class DetailActivity : AppCompatActivity() {
    var plus: ImageView? = null
    var minus: ImageView? = null
    var quantitySize: TextView? = null
    var cover: ImageView? = null
    private lateinit var mBundle:Bundle
    private var viewModel: ProductViewModel? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = ProductViewModel(application, this)
        mBundle = intent.extras!!
        val mTextView = findViewById<TextView>(R.id.title_song)
        mTextView.text = mBundle.getString("title")
        val textView = findViewById<TextView>(R.id.description)
        textView.text = mBundle.getString("desc")
        val cost = findViewById<TextView>(R.id.price)
        cost.text = "£" + mBundle.getDouble("price")
        cover = findViewById(R.id.cover)
        Glide.with(this).load(mBundle.getString("image")).dontAnimate().into(cover!!)
        plus = findViewById<View>(R.id.plus) as ImageView
        minus = findViewById<View>(R.id.minus) as ImageView
        quantitySize = findViewById<View>(R.id.sizeno) as TextView
        val number = intArrayOf(1)
        quantitySize!!.text = "" + number[0]
        minus!!.setOnClickListener {
            if (number[0] == 0) {
                quantitySize!!.text = "" + number[0]
            }
            if (number[0] > 0) {
                number[0] = number[0] - 1
                quantitySize!!.text = "" + number[0]
            }
        }
        plus!!.setOnClickListener {
            if (number[0] == 9) {
                quantitySize!!.text = "" + number[0]
            }
            if (number[0] < 9) {
                number[0] = number[0] + 1
                quantitySize!!.text = "" + number[0]
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.action_notifications){
            startActivity(Intent(this,BasketActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    fun addBasket(view:View){
        viewModel?.addToBasket(mBundle.getString("title"),mBundle.getString("desc"),
                mBundle.getDouble("price"),mBundle.getString("category"),mBundle.getString("image"),
                quantitySize!!.text.toString().toInt())

        toast("Item added to basket")
    }
}