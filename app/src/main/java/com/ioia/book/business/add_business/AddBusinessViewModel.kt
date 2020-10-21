package com.ioia.book.business.add_business

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioia.book.account.AccountRepository
import com.ioia.book.business.Business
import com.ioia.book.business.BusinessRepository
import com.ioia.book.fcm.FCMRepository
import com.ioia.book.utils.support.SessionManager
//import com.onesignal.OneSignal
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class AddBusinessViewModel(
    private val context: Context,
    private val repository: BusinessRepository,
    private val accountRepository: AccountRepository,
    private val fcmRepository: FCMRepository
) : ViewModel(){
    val isBusinessTypeOpen = MutableLiveData(false)

    val name = MutableLiveData("")
    val type = MutableLiveData("")

    fun addBusiness(v: View){
        if(name.value != null && name.value != "" && type.value != null && type.value != ""){
            val business = Business(null, name.value, type.value)
            repository.insert(business){isSuccess, id ->
                if(isSuccess){
                    val session = SessionManager(context)
                    val phone = session.getPhone()

                    accountRepository.updateActiveBusiness(phone!!, id.toString()){
                        (context as Activity).finish()
                    }
                }
            }
        }else{
            Toast.makeText(context, "Kotak isian tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
    }

    fun openBusinessTypePage(view: View){
        isBusinessTypeOpen.value = !isBusinessTypeOpen.value!!
    }

}