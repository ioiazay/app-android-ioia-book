package com.ioia.book.business

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ioia.book.R
import com.ioia.book.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.business_item.view.*

class BusinessRVAdapter(
    private val business: MutableLiveData<List<Business>>,
    private val dashboardViewModel: DashboardViewModel,
    private val onItemSelected: (businessId: Long, businessName: String) -> Unit
) : RecyclerView.Adapter<BusinessRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessRVAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.business_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = business.value!!.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(business.value?.get(position)!!, dashboardViewModel, onItemSelected)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val context = view.context
        private val llMain = view.ll_main
        private val tvName = view.tv_name
        private val ivStatus = view.iv_status

        fun onBind(
            business: Business,
            dashboardViewModel: DashboardViewModel,
            onItemSelected: (businessId: Long, businessName: String) -> Unit
        ){
            tvName.text = business.name

            if(dashboardViewModel.account.value?.activeBusinessId == business.id.toString()){
                ivStatus.isSelected = true
                onItemSelected(business.id!!, business.name!!)
            }

            llMain.setOnClickListener {
                dashboardViewModel.updateActiveBusiness(business.id.toString())
            }
        }
    }

}