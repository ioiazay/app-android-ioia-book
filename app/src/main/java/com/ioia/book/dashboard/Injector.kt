package com.ioia.book.dashboard

import android.content.Context
import com.ioia.book.account.AccountRepository
import com.ioia.book.business.BusinessRepository
import com.ioia.book.db.AppDatabase

object Injector {

    private fun getAccountRepository(context: Context): AccountRepository{
        return AccountRepository(AppDatabase.database(context).accountDao)
    }

    private fun getBusinessRepository(context: Context): BusinessRepository{
        return BusinessRepository(AppDatabase.database(context).businessDao)
    }

    fun providerDashboardViewModelFactory(context: Context): DashboardViewModelFactory{
        return DashboardViewModelFactory(context, getAccountRepository(context), getBusinessRepository(context))
    }

}