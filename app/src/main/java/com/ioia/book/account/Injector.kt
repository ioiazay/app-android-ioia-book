package com.ioia.book.account

import android.content.Context
import com.ioia.book.db.AppDatabase

object Injector {

    private fun getAccountRepository(context: Context): AccountRepository{
        return AccountRepository(AppDatabase.database(context).accountDao)
    }

    fun provideAccountViewModelFactory(context: Context): AccountViewModelFactory{
        return AccountViewModelFactory(context, getAccountRepository(context))
    }

}