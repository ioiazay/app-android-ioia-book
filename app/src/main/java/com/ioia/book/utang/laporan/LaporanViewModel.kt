package com.ioia.book.utang.laporan

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ioia.book.R
import com.ioia.book.utang.Hutang
import com.ioia.book.utang.HutangRepository
import com.ioia.book.utils.PopupHelper

class LaporanViewModel(
    private val context: Context,
    private val hutangRepository: HutangRepository
): ViewModel() {
    val hutangListByBusiness = MutableLiveData<List<Hutang>>(listOf())

    fun getHutangListByBusiness(businessId: Long){
        hutangRepository.getHutangListByBusiness(businessId){ isSuccess, hutang ->
            if(isSuccess){
                hutangListByBusiness.value = hutang
            }
        }
    }

    fun resetDataHutang(){
        hutangListByBusiness.value = listOf()
    }

    fun filterAction(v: View){
        PopupHelper.showPopupMenu(context, v, R.menu.hutang_laporan_filter_menu){
            when(it){
                R.id.item_day -> {
                    resetDataHutang()

                }

                R.id.item_yesterday -> {
                    resetDataHutang()

                }

                R.id.item_7_day -> {
                    resetDataHutang()

                }

                R.id.item_30_day -> {
                    resetDataHutang()

                }

                R.id.item_pick_date -> {
                    resetDataHutang()

                }

                R.id.item_pick_range_date -> {
                    resetDataHutang()

                }
            }
        }
    }

    fun downloadReport(v: View){
        // TODO
    }
}