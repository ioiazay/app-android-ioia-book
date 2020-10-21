package com.ioia.book.customer

import android.os.AsyncTask
import java.lang.Exception

class CustomerRepository(
    private val customerDao: CustomerDao
) {

    fun insert(customer: Customer, callback: (isSuccess: Boolean, id: Long?) -> Unit){
        InsertAsync(customerDao, customer, callback).execute(null)
    }

    fun getCustomerById(customerId: Long, callback: (isSuccess: Boolean, customer: Customer?) -> Unit){
        GetCustomerByIdAsync(customerDao, customerId, callback).execute(null)
    }

    fun getAllCustomer(callback: (isSuccess: Boolean, customers: List<Customer>?) -> Unit){
        GetAllCustomerAsync(customerDao, callback).execute(null)
    }

    fun getAllCustomerBySearch(search: String, callback: (isSuccess: Boolean, customers: List<Customer>?) -> Unit){
        GetAllCustomerBySearchAsync(customerDao, search, callback).execute(null)
    }

    private class GetAllCustomerBySearchAsync(
        private val customerDao: CustomerDao,
        private val search: String,
        private val callback: (isSuccess: Boolean, customers: List<Customer>?) -> Unit
    ): AsyncTask<Void, Void, List<Customer>?>(){

        override fun onPostExecute(result: List<Customer>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Customer>? {
            var customers = listOf<Customer>()

            try {
                val searchSql = "%$search%"
                customers = customerDao.getAllCustomerBySearch(searchSql)
            }catch (e: Exception){
                callback(false, null)
            }

            return customers
        }
    }

    private class GetAllCustomerAsync(
        private val customerDao: CustomerDao,
        private val callback: (isSuccess: Boolean, customers: List<Customer>?) -> Unit
    ): AsyncTask<Void, Void, List<Customer>?>(){

        override fun onPostExecute(result: List<Customer>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Customer>? {
            var customers = listOf<Customer>()

            try {
                customers = customerDao.getAllCustomer()
            }catch (e: Exception){
                callback(false, null)
            }

            return customers
        }
    }

    private class InsertAsync(
        private val customerDao: CustomerDao,
        private val customer: Customer,
        private val callback: (isSuccess: Boolean, id: Long?) -> Unit
    ): AsyncTask<Void, Void, Long?>(){

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): Long? {
            var id = 0L

            try {
                id = customerDao.insert(customer)
            }catch (e: Exception){
                callback(false, null)
            }

            return id
        }
    }

    private class GetCustomerByIdAsync(
        private val customerDao: CustomerDao,
        private val customerId: Long,
        private val callback: (isSuccess: Boolean, customer: Customer?) -> Unit
    ): AsyncTask<Void, Void, Customer?>(){

        override fun onPostExecute(result: Customer?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): Customer? {
            var customer = Customer()

            try {
                customer = customerDao.getCustomerById(customerId)
            }catch (e: Exception){
                callback(false, null)
            }

            return customer
        }
    }

}