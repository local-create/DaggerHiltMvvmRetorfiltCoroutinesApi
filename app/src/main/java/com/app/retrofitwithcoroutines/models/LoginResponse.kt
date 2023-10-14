package com.app.retrofitwithcoroutines.models

data class LoginResponse(
    val access_token: String,
    val refresh_token: String
)