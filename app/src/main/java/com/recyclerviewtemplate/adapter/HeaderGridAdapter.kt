package com.recyclerviewtemplate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.recyclerviewtemplate.AbstractModel
import com.recyclerviewtemplate.R
import java.util.*

/**
 * A custom adapter to use with the RecyclerView widget.
 */
class HeaderGridAdapter(private val mContext: Context, private var modelList: ArrayList<AbstractModel>, private val mHeaderTitle: String) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var mHeaderClickListener: OnHeaderClickListener? = null
    private var mItemClickListener: OnItemClickListener? = null
    fun updateList(modelList: ArrayList<AbstractModel>) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_header, parent, false)
            return HeaderViewHolder(v)
        } else /*if (viewType == TYPE_ITEM)*/ {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_header_grid, parent, false)
            return ViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.txtTitleHeader.text = mHeaderTitle
        } else if (holder is ViewHolder) {
            val model = getItem(position - 1)
            val genericViewHolder = holder
            genericViewHolder.itemTxtTitle.text = model.title
            genericViewHolder.itemTxtMessage.text = model.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isPositionHeader(position)) {
            TYPE_HEADER
        } else TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    override fun getItemCount(): Int {
        return modelList.size + 1
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    fun SetOnHeaderClickListener(headerClickListener: OnHeaderClickListener?) {
        mHeaderClickListener = headerClickListener
    }

    private fun getItem(position: Int): AbstractModel {
        return modelList[position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int, model: AbstractModel?)
    }

    interface OnHeaderClickListener {
        fun onHeaderClick(view: View?, headerTitle: String?)
    }

    internal inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitleHeader: TextView

        init {
            txtTitleHeader = itemView.findViewById<View>(R.id.txtHeader) as TextView
            itemView.setOnClickListener { mHeaderClickListener!!.onHeaderClick(itemView, mHeaderTitle) }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgUser: ImageView
        val itemTxtTitle: TextView
        val itemTxtMessage: TextView

        init {
            imgUser = itemView.findViewById<View>(R.id.img_user) as ImageView
            itemTxtTitle = itemView.findViewById<View>(R.id.item_txt_title) as TextView
            itemTxtMessage = itemView.findViewById<View>(R.id.item_txt_message) as TextView
            itemView.setOnClickListener { mItemClickListener!!.onItemClick(itemView, adapterPosition, modelList[adapterPosition - 1]) }
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
}