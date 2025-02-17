package com.example.jetpackcomposeproject.model


import com.google.gson.annotations.SerializedName

data class TVResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvShowsList>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)