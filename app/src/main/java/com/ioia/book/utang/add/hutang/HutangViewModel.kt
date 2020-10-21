package com.ioia.book.utang.add.hutang

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ioia.book.customer.Customer
import com.ioia.book.customer.CustomerRepository
import com.ioia.book.utang.HutangRepository

class HutangViewModel(
    private val context: Context,
    private val hutangRepository: HutangRepository,
    private val customerRepository: CustomerRepository
): ViewModel() {
    val customer = MutableLiveData(Customer())

    fun getCustomerById(id: Long){
        customerRepository.getCustomerById(id){isSuccess, cs ->
            if(isSuccess){
                customer.value = cs
            }
        }
    }

}