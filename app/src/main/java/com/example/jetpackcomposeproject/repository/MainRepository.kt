package com.example.jetpackcomposeproject.repository

import com.example.jetpackcomposeproject.BuildConfig.API_KEY
import com.example.jetpackcomposeproject.model.LoginRequest
import com.example.jetpackcomposeproject.model.LoginResponse
import com.example.jetpackcomposeproject.model.MoviesList
import com.example.jetpackcomposeproject.model.MoviesResponse
import com.example.jetpackcomposeproject.model.PersonList
import com.example.jetpackcomposeproject.model.RegistrationRequest
import com.example.jetpackcomposeproject.model.RegistrationResponse
import com.example.jetpackcomposeproject.model.TvShowsList
import com.example.jetpackcomposeproject.retrofit.ApiClient.apiInterface
import com.example.jetpackcomposeproject.retrofit.SwaggerApiClient.apiInterfaces
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object MainRepository {

    fun getPopularMovies(callback: (result: List<MoviesList>) -> Unit) {
        CoroutineScope(Dispatchers.IO ).launch {
            val response = apiInterface.getMovies(API_KEY)
            withContext(Dispatchers.Main) {
                response.body()?.results?.let(callback)
            }
        }
    }

    fun getPopularTvShows(callback: (result: List<TvShowsList>) -> Unit) {
        CoroutineScope(Dispatchers.IO ).launch {
            val response = apiInterface.getTVShows(API_KEY)
            withContext(Dispatchers.Main) {
                response.body()?.results?.let(callback)
            }
        }
    }



    fun getPopularPersons(callback: (result: List<PersonList>) -> Unit) {
        CoroutineScope(Dispatchers.IO ).launch {
            val response = apiInterface.getPerson(API_KEY)
            withContext(Dispatchers.Main) {
                response.body()?.results?.let(callback)
            }
        }
    }



    fun getLoginRequest(loginRequest: LoginRequest, callback: (result: LoginResponse) -> Unit){
        CoroutineScope(Dispatchers.IO ).launch {
            val response = apiInterfaces.getLogin(loginRequest)

            withContext(Dispatchers.Main) {
                response.body()?.let(callback)

            }
        }
    }
    fun getRegistrationRequest(registrationRequest: RegistrationRequest, callback: (result: RegistrationResponse) -> Unit){
        CoroutineScope(Dispatchers.IO ).launch {
            val response = apiInterfaces.getRegistration(registrationRequest)

            withContext(Dispatchers.Main) {
                response.body()?.let(callback)

            }
        }
    }
}