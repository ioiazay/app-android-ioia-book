package com.ioia.book.utang

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.customer.CustomerRepository

@Suppress("UNCHECKED_CAST")
class HutangViewModelFactory(
    private val context: Context,
    private val hutangRepository: HutangRepository,
    private val customerRepository: CustomerRepository,
    private val businessId: Long
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HutangViewModel(context, hutangRepository, customerRepository, businessId) as T
    }
}