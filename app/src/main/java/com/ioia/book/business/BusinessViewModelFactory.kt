package com.ioia.book.business

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class BusinessViewModelFactory(
    private val context: Context,
    private val repository: BusinessRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BusinessViewModel(context, repository) as T
    }
}