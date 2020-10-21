package com.ioia.book.customer

import androidx.room.Dao
import androidx.room.Query
import com.ioia.book.BaseDao

@Dao
interface CustomerDao: BaseDao<Customer> {

    @Query("SELECT * FROM table_customer WHERE id = :customerId")
    fun getCustomerById(customerId: Long): Customer

    @Query("SELECT * FROM table_customer")
    fun getAllCustomer(): List<Customer>

    @Query("SELECT * FROM table_customer WHERE name LIKE :search")
    fun getAllCustomerBySearch(search: String): List<Customer>

}