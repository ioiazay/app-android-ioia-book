package com.ioia.book.business

import android.os.AsyncTask

class BusinessRepository(
    private val businessDao: BusinessDao
) {

    fun getAllBusiness(callback: (isSuccess: Boolean, business: List<Business>?) -> Unit){
        GetAllBusinessAsync(businessDao, callback).execute(null)
    }

    fun insert(business: Business, callback: (isSuccess: Boolean, id: Long?) -> Unit){
        InsertAsync(businessDao, business, callback).execute(null)
    }

    private class GetAllBusinessAsync(
        private val businessDao: BusinessDao,
        private val callback: (isSuccess: Boolean, business: List<Business>?) -> Unit
    ): AsyncTask<Void, Void, List<Business>>(){

        override fun onPostExecute(result: List<Business>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Business> {
            var business = listOf<Business>()
            try{
                business = businessDao.getAllBusiness()
            }catch (e: Exception){
                callback(false, null)
            }

            return business
        }
    }

    private class InsertAsync(
        private val businessDao: BusinessDao,
        private val business: Business,
        private val callback: (isSuccess: Boolean, id: Long?) -> Unit
    ): AsyncTask<Void, Void, Long?>(){

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): Long? {
            var id: Long? = null

            try {
                id = businessDao.insert(business)
            }catch (e: Exception){
                callback(false, null)
            }

            return id
        }
    }

}