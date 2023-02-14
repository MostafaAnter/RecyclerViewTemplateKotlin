package com.recyclerviewtemplate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.recyclerviewtemplate.AbstractModel
import com.recyclerviewtemplate.R
import java.util.*

/**
 * A custom adapter to use with the RecyclerView widget.
 */
class ToggleListAdapter(private val mContext: Context, private var modelList: ArrayList<AbstractModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mItemClickListener: OnItemClickListener? = null
    private var mOnCheckedListener: OnCheckedListener? = null
    private val checkSet: MutableSet<Int> = HashSet()
    fun updateList(modelList: ArrayList<AbstractModel>) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_toggle_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        //Here you can fill your row view
        if (holder is ViewHolder) {
            val model = getItem(position)
            val genericViewHolder = holder
            genericViewHolder.itemTxtTitle.text = model.title
            genericViewHolder.itemTxtMessage.text = model.message
            genericViewHolder.itemSwitchList.setOnCheckedChangeListener(null)

            //if true, your checkbox will be selected, else unselected
            genericViewHolder.itemSwitchList.isChecked = checkSet.contains(position)
            genericViewHolder.itemSwitchList.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    checkSet.add(position)
                } else {
                    checkSet.remove(position)
                }
                mOnCheckedListener!!.onChecked(buttonView, isChecked, position, modelList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    fun SetOnCheckedListener(onCheckedListener: OnCheckedListener?) {
        mOnCheckedListener = onCheckedListener
    }

    private fun getItem(position: Int): AbstractModel {
        return modelList[position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int, model: AbstractModel?)
    }

    interface OnCheckedListener {
        fun onChecked(view: View?, isChecked: Boolean, position: Int, model: AbstractModel?)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgUser: ImageView
        val itemTxtTitle: TextView
        val itemTxtMessage: TextView
        val itemSwitchList: SwitchCompat

        init {
            imgUser = itemView.findViewById<View>(R.id.img_user) as ImageView
            itemTxtTitle = itemView.findViewById<View>(R.id.item_txt_title) as TextView
            itemTxtMessage = itemView.findViewById<View>(R.id.item_txt_message) as TextView
            itemSwitchList = itemView.findViewById<View>(R.id.switch_list) as SwitchCompat
            itemView.setOnClickListener { mItemClickListener!!.onItemClick(itemView, adapterPosition, modelList[adapterPosition]) }
        }
    }
}