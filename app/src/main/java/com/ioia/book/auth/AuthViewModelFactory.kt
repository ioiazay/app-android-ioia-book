package com.ioia.book.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.account.AccountRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val context: Context,
    private val accountRepository: AccountRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(context, accountRepository) as T
    }
}