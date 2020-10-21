package com.ioia.book.account

import androidx.room.Dao
import androidx.room.Query
import com.ioia.book.BaseDao

@Dao
interface AccountDao: BaseDao<Account> {

    @Query("UPDATE table_account SET activeBusinessId = :businessId WHERE phone = :phone")
    fun updateActiveBusiness(phone: String, businessId: String)

    @Query("SELECT * FROM table_account WHERE phone = :phone")
    fun getAccount(phone: String): Account
}