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
import com.example.clothes_shoppingkotlin.Model.ProductTable
import com.example.clothes_shoppingkotlin.R
import com.example.clothes_shoppingkotlin.Ui.UserClickListener

class Adapter(context: Context, userList: List<ProductTable>, listener: UserClickListener
) : RecyclerView.Adapter<Adapter.UserViewHolder>() {
    private val mContext: Context
    private var mUserList: List<ProductTable>

    //Step 2 of onClickListener example
    private val mListener: UserClickListener
    fun setList(userList: List<ProductTable>) {
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
        val view: View = inflater.inflate(R.layout.row_item_user, parent, false)

        //return the UserViewHolder we defined
        return UserViewHolder(view)
    }

    //binds the items from our list to the view based on its position
    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val currentUser: ProductTable = mUserList[position]
        val price: String = java.lang.String.valueOf(currentUser.price)
        val displayPrice = "£$price"
        holder.title.setText(currentUser.title)
        holder.price.text = displayPrice
        holder.category.setText(currentUser.category)
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

    //STEP 5: Create a ViewHolder class extending ViewHolder
    inner class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var cover: ImageView
        var holder: LinearLayout
        var title: TextView
        var price: TextView
        var category: TextView
        override fun onClick(v: View) {
            //get the position of an item in your recyclerview by calling getAdapterPosition()
            mListener.onUserClicked(mUserList[bindingAdapterPosition])
        }

        init {
            cover = itemView.findViewById<View>(R.id.cover) as ImageView
            title = itemView.findViewById(R.id.title_song)
            price = itemView.findViewById(R.id.price)
            category = itemView.findViewById(R.id.category)
            holder = itemView.findViewById(R.id.holder)
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
