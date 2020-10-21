package com.ioia.book.auth

import android.content.Context
import com.ioia.book.account.AccountRepository
import com.ioia.book.auth.login.LoginViewModel
import com.ioia.book.auth.login.LoginViewModelFactory
import com.ioia.book.db.AppDatabase
import com.ioia.book.fcm.FCMRepository

object Injector {

    private fun getAccountRepository(context: Context): AccountRepository{
        return AccountRepository(AppDatabase.database(context).accountDao)
    }

    private fun getFCMRepository(): FCMRepository{
        return FCMRepository()
    }

    fun provideAuthViewModelFactory(context: Context): AuthViewModelFactory{
        return AuthViewModelFactory(context, getAccountRepository(context))
    }

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory{
        return LoginViewModelFactory(context, getAccountRepository(context), getFCMRepository())
    }

}