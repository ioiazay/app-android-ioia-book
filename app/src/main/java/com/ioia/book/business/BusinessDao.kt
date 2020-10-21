package com.ioia.book.business

import androidx.room.Dao
import androidx.room.Query
import com.ioia.book.BaseDao

@Dao
interface BusinessDao: BaseDao<Business>{

    @Query("SELECT * FROM table_business")
    fun getAllBusiness(): List<Business>

}