package com.recyclerviewtemplate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.recyclerviewtemplate.AbstractGooglePlay
import com.recyclerviewtemplate.R
import com.recyclerviewtemplate.adapter.SingleItemListAdapter.SingleItemRowHolder
import java.util.*

class SingleItemListAdapter(private val mContext: Context, private val itemsList: ArrayList<AbstractGooglePlay>?) : RecyclerView.Adapter<SingleItemRowHolder>() {
    private var mItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_recycler_item_google_play, viewGroup, false)
        return SingleItemRowHolder(view)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {
        val singleItem = itemsList!![i]
        holder.itemCardTxtTitle.text = singleItem.title


        /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    override fun getItemCount(): Int {
        return itemsList?.size ?: 0
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, itemPosition: Int, model: AbstractGooglePlay?)
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemCardTxtTitle: TextView
        protected var itemCardImg: ImageView

        init {
            itemCardTxtTitle = view.findViewById<View>(R.id.item_card_txt_title) as TextView
            itemCardImg = view.findViewById<View>(R.id.item_card_img) as ImageView
            view.setOnClickListener { v -> mItemClickListener!!.onItemClick(v, adapterPosition, itemsList!![adapterPosition]) }
        }
    }
}