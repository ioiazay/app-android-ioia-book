package com.ioia.book.utang

import android.content.Context
import com.ioia.book.customer.CustomerRepository
import com.ioia.book.db.AppDatabase
import com.ioia.book.utang.add.AddViewModelFactory
import com.ioia.book.utang.add.contact.ContactViewModelFactory
import com.ioia.book.utang.laporan.LaporanViewModelFactory

object Injector {

    private fun getHutangRepository(context: Context): HutangRepository{
        return HutangRepository(AppDatabase.database(context).hutangDao)
    }

    private fun getCustomerRepository(context: Context): CustomerRepository{
        return CustomerRepository(AppDatabase.database(context).customerDao)
    }

    fun provideHutangViewModelFactory(context: Context, businessId: Long): HutangViewModelFactory{
        return HutangViewModelFactory(context, getHutangRepository(context), getCustomerRepository(context), businessId)
    }

    fun providerLaporanViewModelFactory(context: Context): LaporanViewModelFactory{
        return LaporanViewModelFactory(context, getHutangRepository(context))
    }

    fun providerAddViewModelFactory(context: Context, businessId: Long): AddViewModelFactory{
        return AddViewModelFactory(context, getCustomerRepository(context), businessId)
    }

    fun providerContactViewModelFactory(context: Context, businessId: Long): ContactViewModelFactory{
        return ContactViewModelFactory(context, businessId, getHutangRepository(context), getCustomerRepository(context))
    }

    fun providerAddHutangViewModelFactory(context: Context): com.ioia.book.utang.add.hutang.HutangViewModelFactory{
        return com.ioia.book.utang.add.hutang.HutangViewModelFactory(context, getHutangRepository(context), getCustomerRepository(context))
    }

}