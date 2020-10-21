package com.ioia.book.auth.login

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.JsonObject
import com.ioia.book.account.Account
import com.ioia.book.account.AccountRepository
import com.ioia.book.dashboard.DashboardActivity
import com.ioia.book.fcm.FCMRepository
import com.ioia.book.fcm.MyFirebaseMessagingService
import com.ioia.book.utils.support.SessionManager
//import com.onesignal.OSPermissionSubscriptionState
//import com.onesignal.OneSignal
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class LoginViewModel(
    private val context: Context,
    private val accountRepository: AccountRepository,
    private val fcmRepository: FCMRepository
): ViewModel() {
    val phone = MutableLiveData("")
    val isOnSaveData = MutableLiveData(false)

    /**
     * Selalu ERROR SETIAP KALI POST MESSAGE ke URL FIREBASE
     * Dan tidak ada informasi erronya kenapa
     * Tapi ketika dicoba menggunakan postman berhasil
     */
//    fun sendMessage(view: View){
//        viewModelScope.launch {
//            fcmRepository.sendNotification(context, "Signup", "Selamat Anda Telah Berhasil Signup!"){isSuccess, msg ->
//
//            }
//        }
//    }

//    private fun sendMessage(v: View){
//        val status = OneSignal.getPermissionSubscriptionState()
//        val userId = status.subscriptionStatus.userId
//
//        try {
//            OneSignal.postNotification(
//                JSONObject(
//                    "" +
//                            "{'contents': {'en':'Selamat Anda Telah Berhasil Signup!'}, " +
//                            "'include_player_ids': ['" + userId + "'], " +
//                            "'headings': {'en': 'Signup'}, " +
//                            "'data': {'openURL': 'https://imgur.com'}}"
//                ), object : OneSignal.PostNotificationResponseHandler{
//                    override fun onSuccess(response: JSONObject?) {
//
//                    }
//
//                    override fun onFailure(response: JSONObject?) {
//
//                    }
//
//                })
//
////            OneSignal.postNotification(JSONObject(
////                "" +
////                "{'contents': {'en':'Tag substitution value for key1 = {{key1}}'}, " +
////                "'include_player_ids': ['" + userId + "'], " +
////                "'headings': {'en': 'Tag sub Title HI {{user_name}}'}, " +
////                "'data': {'openURL': 'https://imgur.com'}," +
////                "'buttons':[{'id': 'cart', 'text': 'Go to cart page'}]}"
////            ), object : OneSignal.PostNotificationResponseHandler{
////                override fun onSuccess(response: JSONObject?) {
////                    Log.i("OneSignalExample", "postNotification Success: " + response);
////                }
////
////                override fun onFailure(response: JSONObject?) {
////                    Log.e("OneSignalExample", "postNotification Failure: " + response);
////                }
////
////            })
//        }catch (e: JSONException){
//            e.printStackTrace()
//        }
//
//    }

    // TODO : Untuk sementara One Signal Dimatikan
    fun addAccount(view: View){
        isOnSaveData.value = true

        val timer = object : CountDownTimer(4000L, 1000L){
            override fun onFinish() {

                val account = Account(phone.value!!)
                accountRepository.insert(account){
                    if(it){
                        FirebaseInstanceId.getInstance().instanceId
                            .addOnCompleteListener(OnCompleteListener { task ->
                                if (!task.isSuccessful) {
                                    return@OnCompleteListener
                                }

                                val token = task.result?.token
                                val session = SessionManager(context)

                                session.createLoginSession(phone.value!!, token!!)
//                                sendMessage(view)
                            })

                        DashboardActivity.startActivity(context)
                        (context as Activity).finish()
                    }
                }
            }

            override fun onTick(millisUntilFinished: Long) {}
        }
        timer.start()
    }

}