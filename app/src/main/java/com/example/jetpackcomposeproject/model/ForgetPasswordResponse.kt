package com.example.jetpackcomposeproject.model


import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("transactionId")
    val transactionId: String
)