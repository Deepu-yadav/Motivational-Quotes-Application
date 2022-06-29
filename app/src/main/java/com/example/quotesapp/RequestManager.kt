package com.example.quotesapp

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RequestManager(mContext:Context) {
    var retrofit=Retrofit.Builder().baseUrl("https://type.fit/").addConverterFactory(GsonConverterFactory.create()).build()

    fun GetAllQuotes(listner: QuotesResponseListner){
        val call=retrofit.create(CallQuotets::class.java).callQuotes()
        call.enqueue(object : Callback<List<QuotesResponse>>{
            override fun onResponse(
                call: Call<List<QuotesResponse>>,
                response: Response<List<QuotesResponse>>
            ) {
                if(!response.isSuccessful){
                    listner.didError(response.message())
                    return
                }
                response.body()?.let { listner.didFetch(it,response.message()) }
            }

            override fun onFailure(call: Call<List<QuotesResponse>>, t: Throwable) {
                t.message?.let { listner.didError(it) }
            }

        })
    }

    private interface CallQuotets{
        @GET("api/quotes")
        fun callQuotes(): Call<List<QuotesResponse>>

    }

}


