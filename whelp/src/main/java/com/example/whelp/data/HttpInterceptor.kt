package com.example.whelp.data

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HttpInterceptor @Inject constructor(var preferences: SharedPreferences) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()


//            request.addHeader(
//                "Authorization",
//                "Bearer " + preferences.getString("Constants.ID_TOKEN", "").toString()
//            )


        val response = chain.proceed(request.build())



        return response
    }
}