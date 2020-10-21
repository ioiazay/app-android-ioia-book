package com.ioia.book.network

import com.ioia.book.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WebClient2 {
    private var retrofit: Retrofit? = null
    private var webService: WebService? = null

    fun getService2(): WebService {
        if (webService == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            webService = retrofit?.create(WebService::class.java)
        }

        return webService!!
    }

    companion object {
        val instance = WebClient2()
    }
}