package com.baronzhang.android.weather.base

import androidx.recyclerview.widget.RecyclerView
import android.widget.AdapterView

/**
 * @author baronzhang (微信公众号：BaronTalk)
 */
abstract class BaseRecyclerViewAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    var onItemClickListener: AdapterView.OnItemClickListener? = null

    protected fun onItemHolderClick(itemHolder: RecyclerView.ViewHolder) {
        if (onItemClickListener != null) {
            onItemClickListener!!.onItemClick(null, itemHolder.itemView,
                    itemHolder.adapterPosition, itemHolder.itemId)
        } else {
            throw IllegalStateException("Please call setOnItemClickListener method set the click event listeners")
        }
    }

}
