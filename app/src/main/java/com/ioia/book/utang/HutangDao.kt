package com.ioia.book.utang

import androidx.room.Dao
import androidx.room.Query
import com.ioia.book.BaseDao

@Dao
interface HutangDao: BaseDao<Hutang> {

    @Query("SELECT * FROM table_hutang WHERE businessId = :businessId")
    fun getHutangListByBusiness(businessId: Long): List<Hutang>

    @Query("SELECT * FROM table_hutang WHERE id = :hutangId")
    fun getHutangById(hutangId: Long): Hutang

    @Query("SELECT * FROM table_hutang WHERE businessId = :businessId ORDER BY hutang DESC")
    fun getHutangListByBusinessMax(businessId: Long): List<Hutang>

    @Query("SELECT * FROM table_hutang WHERE businessId = :businessId ORDER BY hutang ASC")
    fun getHutangListByBusinessMin(businessId: Long): List<Hutang>

    @Query("SELECT * FROM table_hutang WHERE businessId = :businessId AND status = 0")
    fun getHutangListByBusinessCustomer(businessId: Long): List<Hutang>

    @Query("SELECT * FROM table_hutang WHERE businessId = :businessId AND status = 1")
    fun getHutangListByBusinessSelf(businessId: Long): List<Hutang>

    @Query("SELECT * FROM table_hutang WHERE businessId = :businessId AND lunas = 1")
    fun getHutangListByBusinessLunas(businessId: Long): List<Hutang>

    @Query("SELECT * FROM table_hutang WHERE businessId = :businessId AND customerName LIKE :search OR customerPhone LIKE :search")
    fun getHutangListByBusinessSearch(businessId: Long, search: String): List<Hutang>

    @Query("SELECT * FROM table_hutang WHERE businessId = :businessId AND (date_created BETWEEN :dateStart AND :dateEnd)")
    fun getHutangListByBusinessDateRange(businessId: Long, dateStart: String, dateEnd: String): List<Hutang>
}