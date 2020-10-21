package com.ioia.book.utang.add.hutang

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.customer.CustomerRepository
import com.ioia.book.utang.HutangRepository

@Suppress("UNCHECKED_CAST")
class HutangViewModelFactory(
    private val context: Context,
    private val hutangRepository: HutangRepository,
    private val customerRepository: CustomerRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HutangViewModel(context, hutangRepository, customerRepository) as T
    }
}