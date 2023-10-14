package com.app.retrofitwithcoroutines.retrofit

import androidx.lifecycle.LiveData
import com.app.retrofitwithcoroutines.models.LoginResponse
import com.app.daggrhiltdemo.models.UserDetailResponse
import com.app.retrofitwithcoroutines.request_models.LoginRequest
import com.google.gson.JsonObject
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest) : LoginResponse

    @GET("profile")
    suspend fun getProfileDetails() : UserDetailResponse
}