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
class FooterGridAdapter(private val mContext: Context, private var modelList: ArrayList<AbstractModel>, private val mFooterTitle: String) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private var mFooterClickListener: OnFooterClickListener? = null
    private var mItemClickListener: OnItemClickListener? = null
    fun updateList(modelList: ArrayList<AbstractModel>) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_FOOTER) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_footer, parent, false)
            return FooterViewHolder(v)
        } else /*if (viewType == TYPE_ITEM)*/ {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_footer_grid, parent, false)
            return ViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FooterViewHolder) {
            holder.txtFooter.text = mFooterTitle
        } else if (holder is ViewHolder) {
            val model = getItem(position)
            val genericViewHolder = holder
            genericViewHolder.itemTxtTitle.text = model.title
            genericViewHolder.itemTxtMessage.text = model.message
        }
    }

    //    need to override this method
    override fun getItemViewType(position: Int): Int {
        return if (isPositionFooter(position)) {
            TYPE_FOOTER
        } else TYPE_ITEM
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == modelList.size
    }

    override fun getItemCount(): Int {
        return modelList.size + 1
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    fun SetOnFooterClickListener(footerClickListener: OnFooterClickListener?) {
        mFooterClickListener = footerClickListener
    }

    private fun getItem(position: Int): AbstractModel {
        return modelList[position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int, model: AbstractModel?)
    }

    interface OnFooterClickListener {
        fun onFooterClick(view: View?, headerTitle: String?)
    }

    internal inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtFooter: TextView

        init {
            txtFooter = itemView.findViewById<View>(R.id.txtFooter) as TextView
            itemView.setOnClickListener { mFooterClickListener!!.onFooterClick(itemView, mFooterTitle) }
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
            itemView.setOnClickListener { mItemClickListener!!.onItemClick(itemView, adapterPosition, modelList[adapterPosition]) }
        }
    }

    companion object {
        private const val TYPE_ITEM = 1
        private const val TYPE_FOOTER = 2
    }
}