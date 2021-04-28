package com.example.clothes_shoppingkotlin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clothes_shoppingkotlin.Model.Basket
import com.example.clothes_shoppingkotlin.Model.ProductTable
import com.example.clothes_shoppingkotlin.R
import com.example.clothes_shoppingkotlin.Ui.UserClickListener

class BasketAdapter(context: Context, userList: List<Basket>, listener: UserClickListener
) : RecyclerView.Adapter<BasketAdapter.UserViewHolder>() {
    private val mContext: Context
    private var mUserList: List<Basket>

    //Step 2 of onClickListener example
    private val mListener: UserClickListener
    fun setList(userList: List<Basket>) {
        mUserList = userList
        //this method refreshes the recyclerview so that the new items display
        notifyDataSetChanged()
    }

    //Step 6: implement all the methods
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        //Create a view by first retrieving context, then using the context to get LayoutInflater
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        //Finally, use layoutInflater to create your view
        //research what attach to root is for if you're REALLY interested
        val view: View = inflater.inflate(R.layout.basket_item, parent, false)

        //return the UserViewHolder we defined
        return UserViewHolder(view)
    }

    //binds the items from our list to the view based on its position
    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val currentUser: Basket = mUserList[position]
        val price: String = java.lang.String.valueOf(currentUser.price)
        val displayPrice = "£$price"
        holder.title.text = currentUser.title
        holder.price.text = displayPrice
        holder.quantity.text = "Quantity :: " + currentUser.quantity.toString()
        Glide.with(mContext)
            .asBitmap()
            .load(currentUser.image)
            .dontAnimate()
            .into(holder.cover)
    }

    //returns the size of the list
    override fun getItemCount(): Int {
        return mUserList.size
    }

    fun getTotalPrice():Double{
        var totalPrice = 0.0
        for(e in mUserList){
            totalPrice+=e.price!!
        }
        return totalPrice
    }

    //STEP 5: Create a ViewHolder class extending ViewHolder
    inner class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var cover: ImageView
        var holder: ImageView
        var title: TextView
        var price: TextView
        var quantity:TextView
        override fun onClick(v: View) {
            //get the position of an item in your recyclerview by calling getAdapterPosition()
            mListener.onCloseClicked(mUserList[bindingAdapterPosition])
        }

        init {
            cover = itemView.findViewById<View>(R.id.cover) as ImageView
            title = itemView.findViewById(R.id.title_song)
            quantity = itemView.findViewById(R.id.quantity_item)
            price = itemView.findViewById(R.id.price)
            holder = itemView.findViewById(R.id.remove_basket)
            holder.setOnClickListener(this)
        }
    }

    //here we retrieve a context because we need it to initialize a view in one
    //of our methods
    //once you create an ONClickListener interface, you can pass in a listener as well
    init {
        mUserList = userList
        mContext = context
        mListener = listener
    }
}
