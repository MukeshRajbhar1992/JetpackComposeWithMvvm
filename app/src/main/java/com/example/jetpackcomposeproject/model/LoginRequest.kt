package com.example.jetpackcomposeproject.model


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("emailAddress")
    val emailAddress: String,
    @SerializedName("password")
    val password: String
)