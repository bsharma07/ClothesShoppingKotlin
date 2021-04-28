package com.example.clothes_shoppingkotlin.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.clothes_shoppingkotlin.R
import com.example.clothes_shoppingkotlin.ViewModel.ProductViewModel

class BasketActivity : AppCompatActivity() {

    var plus: ImageView? = null
    var minus: ImageView? = null
    var quantitySize: TextView? = null
    var cover: ImageView? = null
    private lateinit var mBundle:Bundle
    private var viewModel: ProductViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
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
    }
