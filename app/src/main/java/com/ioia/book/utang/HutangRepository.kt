package com.ioia.book.utang

import android.os.AsyncTask

class HutangRepository(
    private val hutangDao: HutangDao
) {

    fun insert(hutang: Hutang, callback: (isSuccess: Boolean, id: Long?) -> Unit){
        InsertAsync(hutangDao, hutang, callback).execute(null)
    }

    fun getHutangListByBusiness(businessId: Long, callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit){
        GetHutangListByBusinessAsync(hutangDao, businessId, callback).execute(null)
    }

    fun getHutangListByBusinessMax(businessId: Long, callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit){
        GetHutangListByBusinessMaxAsync(hutangDao, businessId, callback).execute(null)
    }

    fun getHutangListByBusinessMin(businessId: Long, callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit){
        GetHutangListByBusinessMinAsync(hutangDao, businessId, callback).execute(null)
    }

    fun getHutangListByBusinessCustomer(businessId: Long, callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit){
        GetHutangListByBusinessCustomerAsync(hutangDao, businessId, callback).execute(null)
    }

    fun getHutangListByBusinessself(businessId: Long, callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit){
        GetHutangListByBusinessSelfAsync(hutangDao, businessId, callback).execute(null)
    }

    fun getHutangListByBusinessLunas(businessId: Long, callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit){
        GetHutangListByBusinessLunasAsync(hutangDao, businessId, callback).execute(null)
    }

    fun getHutangListByBusinessSearch(businessId: Long, search: String, callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit){
        GetHutangListByBusinessSearchAsync(hutangDao, businessId, search, callback).execute(null)
    }

    fun getHutangListByBusinessDateRange(businessId: Long, dateStart: String, dateEnd: String, callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit){
        GetHutangListByBusinessDateRangeAsync(hutangDao, businessId, dateStart, dateEnd, callback).execute(null)
    }

    fun getHutangById(hutangId: Long, callback: (isSuccess: Boolean, hutang: Hutang?) -> Unit){
        GetHutangByIdAsync(hutangDao, hutangId, callback).execute(null)
    }

    private class InsertAsync(
        private val hutangDao: HutangDao,
        private val hutang: Hutang,
        private val callback: (isSuccess: Boolean, id: Long?) -> Unit
    ): AsyncTask<Void, Void, Long?>(){

        override fun onPostExecute(result: Long?) {
            super.onPostExecute(result)
            callback(true, result!!)
        }

        override fun doInBackground(vararg params: Void?): Long? {
            var id: Long = 0
            try {
                id = hutangDao.insert(hutang)
            }catch (e: Exception){
                callback(false, null)
            }

            return id
        }
    }

    private class GetHutangListByBusinessAsync(
        private val hutangDao: HutangDao,
        private val businessId: Long,
        private val callback: (isSuccess: Boolean, List<Hutang>?) -> Unit
    ): AsyncTask<Void, Void, List<Hutang>?>(){

        override fun onPostExecute(result: List<Hutang>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Hutang>? {
            var hutang = listOf<Hutang>()

            try {
                hutang = hutangDao.getHutangListByBusiness(businessId)
            }catch (e: Exception){
                callback(false, null)
            }

            return hutang
        }
    }

    private class GetHutangListByBusinessMaxAsync(
        private val hutangDao: HutangDao,
        private val businessId: Long,
        private val callback: (isSuccess: Boolean, List<Hutang>?) -> Unit
    ): AsyncTask<Void, Void, List<Hutang>?>(){

        override fun onPostExecute(result: List<Hutang>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Hutang>? {
            var hutang = listOf<Hutang>()

            try {
                hutang = hutangDao.getHutangListByBusinessMax(businessId)
            }catch (e: Exception){
                callback(false, null)
            }

            return hutang
        }
    }

    private class GetHutangListByBusinessMinAsync(
        private val hutangDao: HutangDao,
        private val businessId: Long,
        private val callback: (isSuccess: Boolean, List<Hutang>?) -> Unit
    ): AsyncTask<Void, Void, List<Hutang>?>(){

        override fun onPostExecute(result: List<Hutang>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Hutang>? {
            var hutang = listOf<Hutang>()

            try {
                hutang = hutangDao.getHutangListByBusinessMin(businessId)
            }catch (e: Exception){
                callback(false, null)
            }

            return hutang
        }
    }

    private class GetHutangListByBusinessCustomerAsync(
        private val hutangDao: HutangDao,
        private val businessId: Long,
        private val callback: (isSuccess: Boolean, List<Hutang>?) -> Unit
    ): AsyncTask<Void, Void, List<Hutang>?>(){

        override fun onPostExecute(result: List<Hutang>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Hutang>? {
            var hutang = listOf<Hutang>()

            try {
                hutang = hutangDao.getHutangListByBusinessCustomer(businessId)
            }catch (e: Exception){
                callback(false, null)
            }

            return hutang
        }
    }

    private class GetHutangListByBusinessSelfAsync(
        private val hutangDao: HutangDao,
        private val businessId: Long,
        private val callback: (isSuccess: Boolean, List<Hutang>?) -> Unit
    ): AsyncTask<Void, Void, List<Hutang>?>(){

        override fun onPostExecute(result: List<Hutang>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Hutang>? {
            var hutang = listOf<Hutang>()

            try {
                hutang = hutangDao.getHutangListByBusinessSelf(businessId)
            }catch (e: Exception){
                callback(false, null)
            }

            return hutang
        }
    }

    private class GetHutangListByBusinessLunasAsync(
        private val hutangDao: HutangDao,
        private val businessId: Long,
        private val callback: (isSuccess: Boolean, List<Hutang>?) -> Unit
    ): AsyncTask<Void, Void, List<Hutang>?>(){

        override fun onPostExecute(result: List<Hutang>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Hutang>? {
            var hutang = listOf<Hutang>()

            try {
                hutang = hutangDao.getHutangListByBusinessLunas(businessId)
            }catch (e: Exception){
                callback(false, null)
            }

            return hutang
        }
    }

    private class GetHutangByIdAsync(
        private val hutangDao: HutangDao,
        private val hutangId: Long,
        private val callback: (isSuccess: Boolean, hutang: Hutang?) -> Unit
    ): AsyncTask<Void, Void, Hutang?>(){

        override fun onPostExecute(result: Hutang?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): Hutang? {
            var hutang = Hutang()

            try {
                hutang = hutangDao.getHutangById(hutangId)
            }catch (e: java.lang.Exception){
                callback(false, null)
            }

            return hutang
        }
    }

    private class GetHutangListByBusinessSearchAsync(
        private val hutangDao: HutangDao,
        private val businessId: Long,
        private val search: String,
        private val callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit
    ): AsyncTask<Void, Void, List<Hutang>?>(){

        override fun onPostExecute(result: List<Hutang>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Hutang>? {
            var hutang = listOf<Hutang>()

            try {
                val searchSql = "%$search%"
                hutang = hutangDao.getHutangListByBusinessSearch(businessId, searchSql)
            }catch (e: Exception){
                callback(false, null)
            }

            return hutang
        }
    }


    private class GetHutangListByBusinessDateRangeAsync(
        private val hutangDao: HutangDao,
        private val businessId: Long,
        private val dateStart: String,
        private val dateEnd: String,
        private val callback: (isSuccess: Boolean, hutang: List<Hutang>?) -> Unit
    ): AsyncTask<Void, Void, List<Hutang>?>(){

        override fun onPostExecute(result: List<Hutang>?) {
            super.onPostExecute(result)
            callback(true, result)
        }

        override fun doInBackground(vararg params: Void?): List<Hutang>? {
            var hutang = listOf<Hutang>()

            try {
                hutang = hutangDao.getHutangListByBusinessDateRange(businessId, dateStart, dateEnd)
            }catch (e: Exception){
                callback(false, null)
            }

            return hutang
        }
    }
}