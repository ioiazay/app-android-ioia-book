package com.ioia.book.business

import android.content.Context
import com.ioia.book.account.AccountRepository
import com.ioia.book.business.add_business.AddBusinessViewModelFactory
import com.ioia.book.db.AppDatabase
import com.ioia.book.fcm.FCMRepository

object Injector {

    private fun getBusinessRepository(context: Context): BusinessRepository{
        return BusinessRepository(AppDatabase.database(context).businessDao)
    }

    private fun getFCMRepository(): FCMRepository{
        return FCMRepository()
    }

    private fun getAccountRepository(context: Context): AccountRepository{
        return AccountRepository(AppDatabase.database(context).accountDao)
    }

    fun provideBusinessViewModelFactory(context: Context): BusinessViewModelFactory{
        return BusinessViewModelFactory(context, getBusinessRepository(context))
    }

    fun provideAddBusinessViewModelFactory(context: Context): AddBusinessViewModelFactory{
        return AddBusinessViewModelFactory(context, getBusinessRepository(context), getAccountRepository(context), getFCMRepository())
    }

}