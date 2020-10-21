package com.ioia.book.account

import android.os.AsyncTask
import java.lang.Exception

class AccountRepository(
    private val accountDao: AccountDao
) {

    fun insert(account: Account, callback: (isSuccess: Boolean) -> Unit){
        InsertAsync(accountDao, account, callback).execute(null)
    }

    fun updateActiveBusiness(phone: String, businessId: String, callback: (isSuccess: Boolean) -> Unit){
        UpdateActiveBusinnesAsync(accountDao, phone, businessId, callback).execute(null)
    }

    fun getAccount(phone: String, callback: (isSuccess: Boolean, account: Account?) -> Unit){
        GetAccountAsync(accountDao, phone, callback).execute(null)
    }

    private class InsertAsync(
        private val accountDao: AccountDao,
        private val account: Account,
        private val callback: (isSuccess: Boolean) -> Unit
    ): AsyncTask<Void, Void, Void?>(){

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                accountDao.insert(account)
            }catch (e: Exception){
                callback(false)
            }

            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            callback(true)
        }
    }

    private class UpdateActiveBusinnesAsync(
        private val accountDao: AccountDao,
        private val phone: String,
        private val businessId: String,
        private val callback: (isSuccess: Boolean) -> Unit
    ): AsyncTask<Void, Void, Void?>(){
        override fun doInBackground(vararg params: Void?): Void? {
            try {
                accountDao.updateActiveBusiness(phone, businessId)
            }catch (e: Exception){
                callback(false)
            }

            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            callback(true)
        }
    }

    private class GetAccountAsync(
        private val accountDao: AccountDao,
        private val phone: String,
        private val callback: (isSuccess: Boolean, account: Account?) -> Unit
    ): AsyncTask<Void, Void, Account>(){

        override fun doInBackground(vararg params: Void?): Account? {
            var account: Account? = null

            try {
                account = accountDao.getAccount(phone)
            }catch (e: Exception){
                callback(false, null)
            }

            return account
        }

        override fun onPostExecute(result: Account?) {
            super.onPostExecute(result)
            callback(true, result)
        }
    }

}