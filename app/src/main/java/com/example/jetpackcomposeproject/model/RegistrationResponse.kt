package com.example.jetpackcomposeproject.model


import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isProfileUpdated")
    val isProfileUpdated: Boolean,
    @SerializedName("isTerms")
    val isTerms: Boolean,
    @SerializedName("langId")
    val langId: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("messages")
    val messages: String,
    @SerializedName("refershToken")
    val refershToken: String,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("termsConditions")
    val termsConditions: String,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("userType")
    val userType: Int
)