package com.ioia.book.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ioia.book.account.AccountRepository

class AuthViewModel(
    private val context: Context,
    private val accountRepository: AccountRepository
): ViewModel() {

}