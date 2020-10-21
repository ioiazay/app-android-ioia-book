package com.ioia.book.business.add_business

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.account.AccountRepository
import com.ioia.book.business.BusinessRepository
import com.ioia.book.fcm.FCMRepository

@Suppress("UNCHECKED_CAST")
class AddBusinessViewModelFactory(
    private val context: Context,
    private val repository: BusinessRepository,
    private val accountRepository: AccountRepository,
    private val fcmRepository: FCMRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddBusinessViewModel(context, repository, accountRepository, fcmRepository) as  T
    }
}