package com.ioia.book.utang.add

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ioia.book.customer.Customer
import com.ioia.book.customer.CustomerRepository
import com.ioia.book.utang.add.contact.ContactActivity

class AddViewModel(
    private val context: Context,
    private val customerRepository: CustomerRepository,
    private val businessId: Long
): ViewModel() {
    val customers = MutableLiveData<List<Customer>>(listOf())

    fun addNewContactAction(v: View){
        ContactActivity.startActivity(context, businessId, "", "")
    }

    fun getAllCustomer(){
        customerRepository.getAllCustomer { isSuccess, customerList ->
            if(isSuccess){
                customers.value = customerList
            }
        }
    }

    fun getAllCustomerBySearch(search: String){
        customerRepository.getAllCustomerBySearch(search){isSuccess, customerList ->
            if(isSuccess){
                resetCustomers()
                customers.value = customerList
            }
        }
    }

    fun resetCustomers(){
        customers.value = listOf()
    }

}