package com.ioia.book.utang

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ioia.book.R
import com.ioia.book.customer.Customer
import com.ioia.book.customer.CustomerRepository
import com.ioia.book.utang.add.AddActivity
import com.ioia.book.utang.laporan.LaporanActivity
import com.ioia.book.utils.PopupHelper

class HutangViewModel(
    private val context: Context,
    private val hutangRepository: HutangRepository,
    private val customerRepository: CustomerRepository,
    private val businessId: Long
): ViewModel() {
    val isSearchAction = MutableLiveData(false)
    val hutangListByBusiness = MutableLiveData<List<Hutang>>(listOf())

    fun getCustomerById(customerId: Long, callback: (customer: Customer) -> Unit){
        customerRepository.getCustomerById(customerId){isSuccess, customer ->
            if(isSuccess){
                callback(customer!!)
            }
        }
    }

    fun getHutangListByBusiness(){
        hutangRepository.getHutangListByBusiness(businessId){ isSuccess, hutang ->
            if(isSuccess){
                hutangListByBusiness.value = hutang
            }
        }
    }

    fun getHutangListByBusinessMax(){
        hutangRepository.getHutangListByBusinessMax(businessId){ isSuccess, hutang ->
            if(isSuccess){
                hutangListByBusiness.value = hutang
            }
        }
    }

    fun getHutangListByBusinessMin(){
        hutangRepository.getHutangListByBusinessMin(businessId){ isSuccess, hutang ->
            if(isSuccess){
                hutangListByBusiness.value = hutang
            }
        }
    }

    fun getHutangListByBusinessCustomer(){
        hutangRepository.getHutangListByBusinessMin(businessId){ isSuccess, hutang ->
            if(isSuccess){
                hutangListByBusiness.value = hutang
            }
        }
    }

    fun getHutangListByBusinessSelf(){
        hutangRepository.getHutangListByBusinessMin(businessId){ isSuccess, hutang ->
            if(isSuccess){
                hutangListByBusiness.value = hutang
            }
        }
    }

    fun getHutangListByBusinessLunas(){
        hutangRepository.getHutangListByBusinessMin(businessId){ isSuccess, hutang ->
            if(isSuccess){
                hutangListByBusiness.value = hutang
            }
        }
    }

    fun getHutangListByBusinessSearch(search: String){
        hutangRepository.getHutangListByBusinessSearch(businessId, search){isSuccess, hutang ->
            if(isSuccess){
                hutangListByBusiness.value = hutang
            }
        }
    }

    fun resetDataHutang(){
        hutangListByBusiness.value = listOf()
    }

    fun downloadHutangAction(v: View){
        // TODO
    }

    fun lihatLaporanHutangAction(v: View){
        LaporanActivity.startActivity(context, businessId)
        // TODO
    }

    fun sortAction(view: View){
        PopupHelper.showPopupMenu(context, view, R.menu.hutang_sort_menu){
            when(it){
                R.id.item_max -> {
                    resetDataHutang()
                    getHutangListByBusinessMax()
                }

                R.id.item_min -> {
                    resetDataHutang()
                    getHutangListByBusinessMin()
                }

                R.id.item_new -> {
                    resetDataHutang()
                    getHutangListByBusiness()
                }
            }
        }
    }

    fun filterAction(view: View){
        PopupHelper.showPopupMenu(context, view, R.menu.hutang_filter_menu){
            when(it){
                R.id.item_all -> {
                    resetDataHutang()
                    getHutangListByBusiness()
                }

                R.id.item_customer -> {
                    resetDataHutang()
                    getHutangListByBusinessCustomer()
                }

                R.id.item_self -> {
                    resetDataHutang()
                    getHutangListByBusinessSelf()
                }

                R.id.item_lunal -> {
                    resetDataHutang()
                    getHutangListByBusinessLunas()
                }
            }
        }
    }

    fun addHutang(v: View){
        AddActivity.startActivity(context, businessId)
        // TODO
    }

    fun openSearch(v: View){
        isSearchAction.value = true
    }

    fun closeSearch(v: View){
        isSearchAction.value = false
    }
}