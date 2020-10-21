package com.ioia.book.business.add_business

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ioia.book.R
import kotlinx.android.synthetic.main.business_type_item.view.*

class BusinessTypeRVAdapter(
    private val businessType: List<String>,
    private val callback: (type: String) -> Unit
): RecyclerView.Adapter<BusinessTypeRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessTypeRVAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.business_type_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = businessType.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(businessType[position], callback)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val context = view.context
        private val tvType = view.tv_type

        fun onBind(type: String, callback: (type: String) -> Unit){
            tvType.text = type

            tvType.setOnClickListener {
                callback(type)
            }
        }
    }

}