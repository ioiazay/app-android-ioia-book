package com.ioia.book.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.account.AccountRepository
import com.ioia.book.fcm.FCMRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val context: Context,
    private val accountRepository: AccountRepository,
    private val fcmRepository: FCMRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(context, accountRepository, fcmRepository) as T
    }
}