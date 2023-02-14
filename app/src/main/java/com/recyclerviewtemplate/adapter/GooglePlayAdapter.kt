package com.recyclerviewtemplate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.recyclerviewtemplate.AbstractGooglePlay
import com.recyclerviewtemplate.R
import java.util.*

/**
 * A custom adapter to use with the RecyclerView widget.
 */
class GooglePlayAdapter(private val mContext: Context, private var modelList: ArrayList<AbstractGooglePlay>) : RecyclerView.Adapter<GooglePlayAdapter.ViewHolder>() {

    private var mItemClickListener: OnItemClickListener? = null
    private var mMoreClickListener: OnMoreClickListener? = null
    fun updateList(modelList: ArrayList<AbstractGooglePlay>) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_recycler_google_play, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Here you can fill your row view
        val model = getItem(position)
        val singleSectionItems = model.singleItemArrayList
        holder.itemTxtTitle.text = model.title
        val itemListDataAdapter = SingleItemListAdapter(mContext, singleSectionItems)
        holder.recyclerViewHorizontalList.setHasFixedSize(true)
        holder.recyclerViewHorizontalList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerViewHorizontalList.adapter = itemListDataAdapter
        holder.recyclerViewHorizontalList.isNestedScrollingEnabled = false
        itemListDataAdapter.SetOnItemClickListener(object : SingleItemListAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, itemPosition: Int, model: AbstractGooglePlay?) {
                mItemClickListener!!.onItemClick(view, position, itemPosition, model)
            }
        })
        holder.itemTxtMore.setOnClickListener { view -> mMoreClickListener!!.onMoreClick(view, position, model) }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    private fun getItem(position: Int): AbstractGooglePlay {
        return modelList[position]
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    fun SetOnMoreClickListener(onMoreClickListener: OnMoreClickListener?) {
        mMoreClickListener = onMoreClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, absolutePosition: Int, relativePosition: Int, model: AbstractGooglePlay?)
    }

    interface OnMoreClickListener {
        fun onMoreClick(view: View?, position: Int, model: AbstractGooglePlay?)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerViewHorizontalList: RecyclerView
        var itemTxtMore: TextView
        var itemTxtTitle: TextView

        init {
            itemTxtTitle = itemView.findViewById<View>(R.id.item_txt_title) as TextView
            recyclerViewHorizontalList = itemView.findViewById<View>(R.id.recycler_view_horizontal_list) as RecyclerView
            itemTxtMore = itemView.findViewById<View>(R.id.item_txt_more) as TextView
        }
    }
}