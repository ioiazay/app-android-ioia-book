package com.ioia.book.fcm

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.ioia.book.network.WebClient2
import com.ioia.book.network.response.FCMResponse
import com.ioia.book.utils.support.SessionManager
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FCMRepository {

    suspend fun sendNotificationAsRequestBody(
        context: Context,
        title: String,
        body: String,
        callback: (isSuccess: Boolean, msg: String) -> Unit
    ){
        val session = SessionManager(context)
        val token = session.getToken()

        val reqBody = createPayloadRequestBody(token!!, title, body, "0")
        WebClient2.instance.getService2().sendMessage(reqBody).enqueue(object : Callback<FCMResponse>{
            override fun onFailure(call: Call<FCMResponse>, t: Throwable) {
                Log.d("TAG", "Msg 1 = ${t.message.toString()}")
                callback(false, t.message.toString())
            }

            override fun onResponse(call: Call<FCMResponse>, response: Response<FCMResponse>) {
                if(response.isSuccessful && response != null && response.body() != null){
                    Log.d("TAG", "Msg 2 = ${response.message()}")
                    callback(true, response.message())
                }else{
                    Log.d("TAG", "Msg 3 = ${response.message()}")
                    callback(false, response.message())
//                    val error = response.body()?.results?.get(0)
//                    Log.d("TAG", "Error = $error")
                }
            }
        })

    }

    suspend fun sendNotificationAsJsonObject(
        context: Context,
        title: String,
        body: String,
        callback: (isSuccess: Boolean, msg: String) -> Unit
    ){
        val session = SessionManager(context)
        val token = session.getToken()
        val payload = createPayloadJsonObject(token!!, title, body, "0")

        WebClient2.instance.getService2().sendMessage(payload).enqueue(object : Callback<FCMResponse>{
            override fun onFailure(call: Call<FCMResponse>, t: Throwable) {
                Log.d("TAG", "Msg 1 = ${t.message.toString()}")
                callback(false, t.message.toString())
            }

            override fun onResponse(call: Call<FCMResponse>, response: Response<FCMResponse>) {
                if(response.isSuccessful && response != null && response.body() != null){
                    Log.d("TAG", "Msg 2 = ${response.message()}")
                    callback(true, response.message())
                }else{
                    Log.d("TAG", "Msg 3 = ${response.message()}")
                    callback(false, response.message())
//                    val error = response.body()?.results?.get(0)
//                    Log.d("TAG", "Error = $error")
                }
            }
        })
    }

    private fun createPayloadJsonObject(token: String, title: String, body: String, id: String): JsonObject{
        val payload = JsonObject()
        payload.addProperty("to", token)

        val notification = JsonObject()
        notification.addProperty("title", title)
        notification.addProperty("body", body)

        val data = JsonObject()
        data.addProperty("id", id)

        payload.add("notification", notification)
        payload.add("data", data)

        return payload
    }

    private fun createPayloadRequestBody(token: String, title: String, body: String, id: String): RequestBody{
        val notification = FCMData.Notification(title, body)
        val data = FCMData.Data(id)
        val fcmData = FCMData(token, notification, data)
        val fcmDataString = GsonBuilder().disableHtmlEscaping().serializeNulls().create().toJson(fcmData)

        return RequestBody.create(MediaType.parse("application/json"), fcmDataString)
    }

}