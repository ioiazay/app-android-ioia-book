package com.ioia.book.utang.add

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.customer.CustomerRepository
import com.ioia.book.utang.HutangRepository

@Suppress("UNCHECKED_CAST")
class AddViewModelFactory(
    private val context: Context,
    private val customerRepository: CustomerRepository,
    private val businessId: Long
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddViewModel(context, customerRepository, businessId) as T
    }

}