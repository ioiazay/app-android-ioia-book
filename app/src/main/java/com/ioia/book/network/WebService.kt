package com.ioia.book.network

import com.google.gson.JsonObject
import com.ioia.book.fcm.FCMData
import com.ioia.book.network.response.FCMResponse
import com.ioia.book.utils.Constants
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    @FormUrlEncoded
    @POST("/fcm/send")
    @Headers(
        "Authorization: key=${Constants.Firebase.SERVER_KEY}",
        "Content-Type: ${Constants.Firebase.CONTENT_TYPE}"
    )
    suspend fun sendMessage(@Body data: RequestBody): Call<FCMResponse>


    @FormUrlEncoded
    @POST("/fcm/send")
    @Headers(
        "Authorization: key=${Constants.Firebase.SERVER_KEY}",
        "Content-Type: ${Constants.Firebase.CONTENT_TYPE}"
    )
    suspend fun sendMessage(@Body data: JsonObject): Call<FCMResponse>

}