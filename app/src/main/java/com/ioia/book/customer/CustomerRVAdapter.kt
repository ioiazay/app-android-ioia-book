package com.ioia.book.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ioia.book.R
import com.ioia.book.utang.add.contact.ContactActivity
import com.ioia.book.utang.add.hutang.HutangActivity
import com.ioia.book.utils.GeneralHelper
import kotlinx.android.synthetic.main.customer_item.view.*

class CustomerRVAdapter(
    private val customers: MutableLiveData<List<Customer>>,
    private val businessId: Long
): RecyclerView.Adapter<CustomerRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = customers.value!!.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(customers.value?.get(position)!!, businessId)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val context = view.context
        private val llMain = view.ll_main
        private val tvChar = view.tv_char
        private val tvName = view.tv_name
        private val tvPhone = view.tv_phone

        fun onBind(customer: Customer, businessId: Long){
            tvChar.text = GeneralHelper.getFirstCharFromString(customer.name!!)
            tvName.text = customer.name
            tvPhone.text = customer.phone

            llMain.setOnClickListener {
                HutangActivity.startActivity(context, businessId, customer.id!!)
            }
        }

    }
}