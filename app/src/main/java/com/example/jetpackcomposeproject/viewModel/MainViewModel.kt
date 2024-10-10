package com.example.jetpackcomposeproject.viewModel

import android.text.TextUtils
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposeproject.MyApplication
import com.example.jetpackcomposeproject.model.LoginRequest
import com.example.jetpackcomposeproject.model.LoginResponse
import com.example.jetpackcomposeproject.model.MoviesList
import com.example.jetpackcomposeproject.model.PersonList
import com.example.jetpackcomposeproject.model.RegistrationRequest
import com.example.jetpackcomposeproject.model.RegistrationResponse
import com.example.jetpackcomposeproject.model.TvShowsList
import com.example.jetpackcomposeproject.repository.MainRepository
import com.example.jetpackcomposeproject.utils.AppUtils.isNetworkAvailable
import com.example.jetpackcomposeproject.utils.NetworkResult
import com.google.gson.Gson

class MainViewModel : ViewModel() {


    val userName:MutableState<String> =  mutableStateOf("")
    val middleName:MutableState<String> =   mutableStateOf("")
    val fatherLastName:MutableState<String> =   mutableStateOf("")
    val motherLastName:MutableState<String> =   mutableStateOf("")
    val emailAddress:MutableState<String> =   mutableStateOf("")
    val mobileNumber:MutableState<String> =   mutableStateOf("")
    val userPassword :MutableState<String> =   mutableStateOf("")
    val userConfirmPassword :MutableState<String> =   mutableStateOf("")
    var tvUserNameError:MutableState<String> = mutableStateOf("")
    
    var tvUserMiddleNameError:MutableState<String> = mutableStateOf("")
    var tvFatherLastNameError:MutableState<String> = mutableStateOf("")
    var tvMotherLastNameError:MutableState<String> = mutableStateOf("")
    var dtvEmailAddressError:MutableState<String> = mutableStateOf("")
    var tvMobileNumberError:MutableState<String> = mutableStateOf("")
    var tvUserPasswordError:MutableState<String> = mutableStateOf("")
    var tvUserConfirmPasswordError:MutableState<String> = mutableStateOf("")

    var PopularMovieResponse: MutableLiveData<NetworkResult<List<MoviesList>>> =
        MutableLiveData()
    var PopularTVShowsResponse: MutableLiveData<NetworkResult<List<TvShowsList>>> =
        MutableLiveData()
    var PopularPersonsResponse: MutableLiveData<NetworkResult<List<PersonList>>> =
        MutableLiveData()

    var LoginRequestResponse: MutableLiveData<NetworkResult<LoginResponse>> =
        MutableLiveData()
    var RegistrationRequestResponse :MutableLiveData<NetworkResult<RegistrationResponse>> =
        MutableLiveData()

// PopularMovie method
    fun getPopularMovies() {
        if (isNetworkAvailable(MyApplication.instance)) {
            PopularMovieResponse.value = NetworkResult.Loading()
            try {
                MainRepository.getPopularMovies { apiResult ->
                    if (apiResult.isNotEmpty()) {
                        PopularMovieResponse.value = NetworkResult.Success(apiResult)
                    } else {
                        PopularMovieResponse.value = NetworkResult.Error("Error in API call")
                    }
                }
            } catch (e: Exception) {
                PopularMovieResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            PopularMovieResponse.value = NetworkResult.Error("No Internet connection !")
        }
    }


    // PopularTVShows method
    fun getPopularTVShows() {
        if (isNetworkAvailable(MyApplication.instance)) {
            PopularTVShowsResponse.value = NetworkResult.Loading()
            try {
                MainRepository.getPopularTvShows { apiResult ->
                    if (apiResult.isNotEmpty()) {
                        PopularTVShowsResponse.value = NetworkResult.Success(apiResult)
                    } else {
                        PopularTVShowsResponse.value = NetworkResult.Error("Error in API call")
                    }
                }
            } catch (e: Exception) {
                PopularTVShowsResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            PopularTVShowsResponse.value = NetworkResult.Error("No Internet connection !")
        }
    }


    // Popular Person method
    fun getPopularPersons() {
        if (isNetworkAvailable(MyApplication.instance)) {
            PopularPersonsResponse.value = NetworkResult.Loading()
            try {
                MainRepository.getPopularPersons { apiResult ->
                    if (apiResult.isNotEmpty()) {
                        PopularPersonsResponse.value = NetworkResult.Success(apiResult)
                    } else {
                        PopularPersonsResponse.value = NetworkResult.Error("Error in API call")
                    }
                }
            } catch (e: Exception) {
                PopularPersonsResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            PopularPersonsResponse.value = NetworkResult.Error("No Internet connection !")
        }
    }


    fun getLoginRequest(){
        if (isNetworkAvailable(MyApplication.instance)) {
            LoginRequestResponse.value = NetworkResult.Loading()
            try {
                MainRepository.getLoginRequest(LoginRequest(emailAddress.value,userPassword.value)) { apiResult ->
                    Log.d("api", Gson().toJson(apiResult))
                    when (apiResult.statusCode) {
                        200 -> {
                            LoginRequestResponse.value = NetworkResult.Success(apiResult)
                        }
                        else -> {
                            LoginRequestResponse.value = NetworkResult.Error("Error in API call")
                        }
                    }
                }
            } catch (e: Exception) {
                LoginRequestResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            LoginRequestResponse.value = NetworkResult.Error("No Internet connection !")
        }
    }


    fun getRegistrationRequest(){
        if (isNetworkAvailable(MyApplication.instance)) {
            RegistrationRequestResponse.value = NetworkResult.Loading()
            try {
                MainRepository.getRegistrationRequest(RegistrationRequest(emailAddress.value, fatherLastName.value,userName.value,middleName.value,mobileNumber.value,motherLastName.value,userPassword.value)) { apiResult ->
                    when (apiResult.statusCode) {
                        200 -> {
                            RegistrationRequestResponse.value = NetworkResult.Success(apiResult)
                        }
                        else -> {
                            RegistrationRequestResponse.value = NetworkResult.Error("Error in API call")
                        }
                    }
                }
            } catch (e: Exception) {
                RegistrationRequestResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            RegistrationRequestResponse.value = NetworkResult.Error("No Internet connection !")
        }
    }






     fun inputsValidationForSignUp(): Boolean {
        if (TextUtils.isEmpty(userName.value)) {
            tvUserNameError.value= "Enter name"
            return false
        }
        if (TextUtils.isEmpty(middleName.value)) {
            tvUserMiddleNameError.value= "Enter middle name"
            return false
        }
        if (TextUtils.isEmpty(fatherLastName.value)) {
            tvFatherLastNameError.value = "Enter father last name"
            return false
        }
        if (TextUtils.isEmpty(motherLastName.value)) {
            tvMotherLastNameError.value = "Enter mother last name"
            return false
        }
        if (TextUtils.isEmpty(emailAddress.value)) {
            dtvEmailAddressError.value= "Enter email address"
            return false
        }

        if (TextUtils.isEmpty(mobileNumber.value)) {
            tvMobileNumberError.value = "Enter mobile number"
            return false
        }

        if (TextUtils.isEmpty(userPassword.value)) {
            tvUserPasswordError.value = "Enter password"
            return false
        }
        if (userPassword.value.length < 6) {
            tvUserPasswordError.value = "Password must be at least 6 characters long"
            return false
        }
        if (TextUtils.isEmpty(userConfirmPassword.value)) {
            tvUserConfirmPasswordError.value = "Enter confirm password"
            return false
        }
        if (userConfirmPassword.value.length < 6) {
            tvUserConfirmPasswordError.value = "Password must be at least 6 characters long"
            return false
        }
        return true

    }

    fun inputsValidationForLogin():Boolean{
        if (TextUtils.isEmpty(emailAddress.value)) {
            dtvEmailAddressError.value= "Enter email address"
            return false
        }

        if (TextUtils.isEmpty(userPassword.value)) {
            tvUserPasswordError.value = "Enter password"
            return false
        }
        if (userPassword.value.length < 6) {
            tvUserPasswordError.value = "Password must be at least 6 characters long"
            return false
        }
        return true
    }

//    fun login(){
//        if (isValidEmail(emailAddress.value) && userPassword.value.length >= 6) {
//            _loginStatus.value = "Login successful"
//            // Handle successful login
//        } else {
//            _loginStatus.value = "Invalid email or password"
//        }
//    }
//
//     fun isValidEmail(emailAddress.value): Boolean {
//
//    }


}