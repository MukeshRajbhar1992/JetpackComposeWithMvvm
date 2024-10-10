package com.example.jetpackcomposeproject.screen

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposeproject.R
import com.example.jetpackcomposeproject.Screen
import com.example.jetpackcomposeproject.utils.NetworkResult
import com.example.jetpackcomposeproject.viewModel.MainViewModel
import com.google.gson.Gson

@Composable
fun LoginScreenField(
    navController: NavController,
    viewModel: MainViewModel
) {
    val scrollState = rememberScrollState()
    val loginResponse by viewModel.LoginRequestResponse.observeAsState()
    val isLoading = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = loginResponse) {

        loginResponse?.let { apiResult ->
            when (apiResult) {
                is NetworkResult.Success -> {
                    isLoading.value = false
                    if (apiResult.networkData?.statusCode == 200) {
                        navController.navigate(Screen.DashBoardScreen.route)
//                        Log.d("popular_movies_data", Gson().toJson(moviesList))
                    }
                }

                is NetworkResult.Error -> {
                    isLoading.value = false
                }

                is NetworkResult.Loading -> {
                    isLoading.value = true
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        // edit text for username
        Text(
            text = "Hello,\nWelcome to the login page",
            fontSize = 25.sp, color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 0.dp),

            )

        com.example.jetpackcomposeproject.component.AppTextField(
            appValue = viewModel.emailAddress,
            appErrorMSG = viewModel.dtvEmailAddressError,
            labelValue = "email",
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
            }
        ) {
            viewModel.emailAddress.value = it
        }


//        AppTextField(appValue = viewModel.userName, appErrorMSG = viewModel.userName, labelValue = "Name", leadingIcon = {
//            Icon(Icons.Default.Person, contentDescription = "person")
//        }) {
//
//        }
        com.example.jetpackcomposeproject.component.AppTextField(
            appValue = viewModel.userPassword,
            appErrorMSG = viewModel.tvUserPasswordError,
            labelValue = "Password",
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "person")
            }

        ) {
            viewModel.userPassword.value = it
        }
//        AppTextField2(title = viewModel.userPassword)
        Text(
            text = "Forget Password?",
            fontSize = 16.sp, color = Color.Black,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp, 0.dp, 0.dp),

            )
        // Login button
        OutlinedButton(
            onClick = {
                if (viewModel.inputsValidationForLogin()) {
                    viewModel.getLoginRequest()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 0.dp),
            shape = RoundedCornerShape(5)
        ) {
            Text(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }

        Text(
            text = "Don't hava an account? Sign Up",
            fontSize = 16.sp, color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 0.dp)
                .clickable {
                    navController.navigate(Screen.SignUpScreen.route)
                },

            )
        Spacer(modifier = Modifier.height(40.dp))
        DividerTextComponent()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Image(painter = painterResource(
                id = R.drawable.google
            ),
                contentDescription = "google",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {

                    }
            )
            Image(painter = painterResource(
                id = R.drawable.facebook
            ),
                contentDescription = "facebook",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {

                    }
            )
            Image(painter = painterResource(id = R.drawable.twitter),
                contentDescription = "twitter",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {

                    }
            )
            Image(painter = painterResource(
                id = R.drawable.instagram
            ),
                contentDescription = "instagram",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {

                    }
            )

            Image(painter = painterResource(
                id = R.drawable.github
            ),
                contentDescription = "github",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                    })
        }


    }
}


@Composable
fun AppTextField2(
    title: MutableState<String>
) {
    // edit text for password
    OutlinedTextField(
        value = title.value,
        onValueChange = { title.value = it },
        singleLine = true,

        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        leadingIcon = {
            Icon(Icons.Default.Lock, contentDescription = "person")
        },
        label = {
            Text(text = "password")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 0.dp)
    )
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = "or",
            fontSize = 18.sp,
            color = Color.Black
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
    }

}

//private fun inputsValidation(userName: String, password: String): Boolean {
//    if (TextUtils.isEmpty(userName)) {
//        tvUserNameError.text = "Enter name"
//        return false
//    }
//    if (TextUtils.isEmpty(password)) {
//        tvPasswordError.text = "Enter the password"
//        return false
//    }
//    if (password.length < 6) {
//        tvPasswordError.text = "Password must be at least 6 characters long"
//        return false
//    }
//    return true
//
//}

//private fun performRegistration(name: String, password: String) {
//    if (inputsValidation(name, password)) {
//
//        viewModel.mostLoginResponse.observe(this) { networkResult ->
////                Log.d("data", "massgae" + networkResult)
//            Log.d("data", Gson().toJson(networkResult))
//            networkResult?.let {
//                when (it) {
//                    is NetworkResult.Success -> {
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
//
//                    is NetworkResult.Error -> {
//                        Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show()
//                    }
//
//                    is NetworkResult.Loading -> {
//                        Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//        }
//    } else {
//        Toast.makeText(this, "missMatch", Toast.LENGTH_SHORT).show()
//    }
//
//
//}