package com.example.jetpackcomposeproject.retrofit

import com.example.jetpackcomposeproject.model.LoginRequest
import com.example.jetpackcomposeproject.model.LoginResponse
import com.example.jetpackcomposeproject.model.MoviesList
import com.example.jetpackcomposeproject.model.MoviesResponse
import com.example.jetpackcomposeproject.model.PersonResponse
import com.example.jetpackcomposeproject.model.RegistrationRequest
import com.example.jetpackcomposeproject.model.RegistrationResponse
import com.example.jetpackcomposeproject.model.TVResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/popular?")
    suspend fun getMovies(@Query("api_key") key: String): Response<MoviesResponse>

    @GET("/3/tv/popular?")
    suspend fun getTVShows(@Query("api_key") key: String): Response<TVResponse>

    @GET("/3/person/popular?")
    suspend fun getPerson(@Query("api_key") key:String) : Response<PersonResponse>



    @POST("api/Login")
    suspend fun getLogin(
        @Body loginRequest: LoginRequest
    ):Response<LoginResponse>

    @POST("api/UserRegistration")
    suspend fun getRegistration(
        @Body registrationRequest : RegistrationRequest
    ):Response<RegistrationResponse>
}
