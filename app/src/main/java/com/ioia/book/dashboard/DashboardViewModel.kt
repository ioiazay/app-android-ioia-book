package com.ioia.book.dashboard

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioia.book.account.Account
import com.ioia.book.account.AccountDao
import com.ioia.book.account.AccountRepository
import com.ioia.book.account.AccountViewModel
import com.ioia.book.business.Business
import com.ioia.book.business.BusinessRepository
import com.ioia.book.business.BusinessViewModel
import com.ioia.book.business.add_business.AddBusinessActivity
import com.ioia.book.utils.support.SessionManager
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val context: Context,
    private val accountRepository: AccountRepository,
    private val businessRepository: BusinessRepository
): ViewModel() {
    val account = MutableLiveData(Account())
    val business = MutableLiveData<List<Business>>(listOf())

    fun getAllBusiness(){
        businessRepository.getAllBusiness{isSuccess, bus ->
            if(isSuccess){
                if(bus?.size!! <= 0){
                    AddBusinessActivity.startActivity(context)
                }else{
                    business.value = bus
                }
            }
        }
    }

    fun updateActiveBusiness(businessId: String){
        val session = SessionManager(context)
        val phone = session.getPhone()
        accountRepository.updateActiveBusiness(phone!!, businessId){
            if(it){
                getAccount()
            }
        }
    }

    fun getAccount(){
        val session = SessionManager(context)
        val phone = session.getPhone()
        accountRepository.getAccount(phone!!){isSuccess, acc ->

            if(isSuccess && acc != null){
                account.value = acc
            }

        }
    }

}