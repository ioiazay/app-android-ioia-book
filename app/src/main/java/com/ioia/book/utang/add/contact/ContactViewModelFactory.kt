package com.ioia.book.utang.add.contact

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.customer.CustomerRepository
import com.ioia.book.utang.HutangRepository

@Suppress("UNCHECKED_CAST")
class ContactViewModelFactory(
    private val context: Context,
    private val businessId: Long,
    private val hutangRepository: HutangRepository,
    private val customerRepository: CustomerRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactViewModel(context, businessId, hutangRepository, customerRepository) as T
    }
}