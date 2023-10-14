package com.app.retrofitwithcoroutines.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.app.daggrhiltdemo.annotations.HeaderRestApi
import com.app.daggrhiltdemo.annotations.SimpleRestApi
import com.app.retrofitwithcoroutines.models.LoginResponse
import com.app.daggrhiltdemo.models.UserDetailResponse
import com.app.retrofitwithcoroutines.request_models.LoginRequest
import com.app.retrofitwithcoroutines.retrofit.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserRepository @Inject constructor() {

    @Inject
    @SimpleRestApi
    lateinit var api: RestApi

    @Inject
    @HeaderRestApi
    lateinit var headerApi: RestApi


    private var _loginState = MutableLiveData<LoginState>()
    var loginState: LiveData<LoginState> = _loginState

    private var _userDetailState = MutableLiveData<UserDetailState>()
    var userDetailState : LiveData<UserDetailState> = _userDetailState




    fun login(loginRequest: LoginRequest) = CoroutineScope(Dispatchers.Main).launch {
        try{
            val response = api.login(loginRequest)
            _loginState.postValue(LoginSuccess(response))
        } catch (e:Exception){
            _loginState.postValue(LoginError(e.message!!))
        }
    }

    fun userDetails() = CoroutineScope(Dispatchers.Main).launch {
        try{
            val response = headerApi.getProfileDetails()
            _userDetailState.postValue(UserDetailSuccess(response))
        } catch (e: Exception){
            _userDetailState.postValue(UserDetailError(e.message!!))
        }
    }



    sealed class LoginState
    class LoginSuccess(var response: LoginResponse) : LoginState()
    class LoginError(var error: String) : LoginState()

    sealed class UserDetailState
    class UserDetailSuccess(var response: UserDetailResponse) : UserDetailState()
    class UserDetailError(var error: String) : UserDetailState()

}