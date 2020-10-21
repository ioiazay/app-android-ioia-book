package com.ioia.book.utang.add.contact

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ioia.book.customer.Customer
import com.ioia.book.customer.CustomerRepository
import com.ioia.book.utang.Hutang
import com.ioia.book.utang.HutangRepository
import com.ioia.book.utang.add.hutang.HutangActivity
import com.ioia.book.utils.DateHelper

class ContactViewModel(
    private val context: Context,
    private val businessId: Long,
    private val hutangRepository: HutangRepository,
    private val customerRepository: CustomerRepository
): ViewModel() {
    val hutang = MutableLiveData("")

    val name = MutableLiveData("")
    val phone = MutableLiveData("")

    fun insertCustomer(v: View){
        if(name.value != null && name.value != ""){
            val customer = Customer(null, name.value, phone.value)
            customerRepository.insert(customer){isSuccess, id ->
                if(isSuccess){
                    HutangActivity.startActivity(context, businessId, id!!)
                }
            }
        }else{
            val toast = Toast.makeText(context, "Masukkan Nama Pelanggan", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }
    }

    fun insert(businessId: Long, name: String, phone: String, status: Int){
        val dateCreated = DateHelper.parseDateNowToDateStringFormat(DateHelper.DATE_FORMAT_LITE, 0)
        val dateUpdated = DateHelper.parseDateNowToDateStringFormat(DateHelper.DATE_FORMAT_LITE, 0)

        val h = Hutang(
            null,
            status,
            hutang.value?.toLong(),
            name,
            phone,
            businessId,
            false,
            dateCreated,
            dateUpdated
        )

        hutangRepository.insert(h){isSuccess, _ ->
            if(isSuccess){
                (context as Activity).finish()
            }
        }
    }

}