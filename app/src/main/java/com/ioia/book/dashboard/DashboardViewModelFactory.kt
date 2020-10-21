package com.ioia.book.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.account.AccountDao
import com.ioia.book.account.AccountRepository
import com.ioia.book.account.AccountViewModel
import com.ioia.book.business.BusinessRepository
import com.ioia.book.business.BusinessViewModel

@Suppress("UNCHECKED_CAST")
class DashboardViewModelFactory(
    private val context: Context,
    private val accountRepository: AccountRepository,
    private val businessRepository: BusinessRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(context, accountRepository, businessRepository) as T
    }
}