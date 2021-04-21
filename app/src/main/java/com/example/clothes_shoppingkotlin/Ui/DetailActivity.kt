package com.example.clothes_shoppingkotlin.Ui

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.clothes_shoppingkotlin.R

class DetailActivity : AppCompatActivity() {
    var plus: ImageView? = null
    var minus: ImageView? = null
    var ratingBar: RatingBar? = null
    var quantitySize: TextView? = null
    var cover: ImageView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val mBundle = intent.extras
        val mTextView = findViewById<TextView>(R.id.title_song)
        mTextView.text = mBundle!!.getString("title")
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
        ratingBar = findViewById<View>(R.id.ratingbar) as RatingBar
        val stars = ratingBar!!.progressDrawable as LayerDrawable
        stars.getDrawable(2)
            .setColorFilter(resources.getColor(R.color.yellow), PorterDuff.Mode.SRC_ATOP)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}