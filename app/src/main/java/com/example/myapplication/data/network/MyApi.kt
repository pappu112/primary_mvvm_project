package com.example.myapplication.data.network

import com.example.myapplication.data.network.response.Authresponse
import com.example.myapplication.data.network.response.QuotesResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {
    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): Response<Authresponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password: String
    ):Response<Authresponse>

    @GET
    suspend fun getQuotes(): Response<QuotesResponse>

    companion object{
        operator fun invoke(
            netWorkConnectionIntercetper: NetWorkConnectionIntercetper
        ): MyApi{

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(netWorkConnectionIntercetper)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)

        }
    }
}