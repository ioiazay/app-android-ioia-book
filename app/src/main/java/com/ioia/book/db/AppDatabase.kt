package com.ioia.book.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ioia.book.account.Account
import com.ioia.book.account.AccountDao
import com.ioia.book.address.Address
import com.ioia.book.address.AddressDao
import com.ioia.book.business.Business
import com.ioia.book.business.BusinessDao
import com.ioia.book.customer.Customer
import com.ioia.book.customer.CustomerDao
import com.ioia.book.utang.Hutang
import com.ioia.book.utang.HutangDao

@Database(
    entities = [Business::class, Account::class, Hutang::class, Customer::class, Address::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract val businessDao: BusinessDao
    abstract val accountDao: AccountDao
    abstract val hutangDao: HutangDao
    abstract val customerDao: CustomerDao
    abstract val AddressDao: AddressDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun database(context: Context): AppDatabase{
            var instanceTemp = instance
            if(instanceTemp != null) return instanceTemp

            synchronized(this){
                val newInstance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_db")
//                    .addMigrations(MigrationDatabase.instance.migrate3_4)
                    .build()

                instance = newInstance
                return newInstance
            }
        }
    }
}