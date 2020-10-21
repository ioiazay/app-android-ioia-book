package com.ioia.book.utang

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ioia.book.R
import com.ioia.book.utang.detail.DetailActivity
import com.ioia.book.utils.GeneralHelper
import kotlinx.android.synthetic.main.hutang_item.view.*

class HutangRVAdapter(
    private val hutangViewModel: HutangViewModel,
    private val hutang: MutableLiveData<List<Hutang>>
): RecyclerView.Adapter<HutangRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HutangRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hutang_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = hutang.value?.size!!
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(hutang.value?.get(position)!!)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val context = view.context
        private val llMain = view.ll_main
        private val tvChar = view.tv_char
        private val tvName = view.tv_name
        private val tvHutang = view.tv_hutang
        private val tvStatus = view.tv_status

        fun onBind(hutang: Hutang){
            tvChar.text = GeneralHelper.getFirstCharFromString(hutang.customerName!!)
            tvName.text = hutang.customerName
            tvHutang.text = "Rp. ${hutang.hutang}"

            when(hutang.status){
                0 -> tvStatus.text = "Utang Pelanggan"
                1 -> tvStatus.text = "Utang Saya"
            }

            llMain.setOnClickListener {
                DetailActivity.startActivity(context, hutang.id!!)
            }
        }

    }

}