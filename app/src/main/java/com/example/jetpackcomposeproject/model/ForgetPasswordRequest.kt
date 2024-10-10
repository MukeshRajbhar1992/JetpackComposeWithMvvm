package com.example.jetpackcomposeproject.model


import com.google.gson.annotations.SerializedName

data class ForgetPasswordRequest(
    @SerializedName("emailAddress")
    val emailAddress: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("newPassword")
    val newPassword: String,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("resendOtp")
    val resendOtp: String
)