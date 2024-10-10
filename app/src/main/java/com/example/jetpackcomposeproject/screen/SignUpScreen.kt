package com.example.jetpackcomposeproject.screen

import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposeproject.Screen
import com.example.jetpackcomposeproject.utils.NetworkResult
import com.example.jetpackcomposeproject.viewModel.MainViewModel
import com.google.gson.Gson

@Composable
fun SignUpScreenField(
    navController: NavController,viewModel: MainViewModel
){
    val scrollState = rememberScrollState()
    val registrationResponse by viewModel.RegistrationRequestResponse.observeAsState()
    val isLoading = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = registrationResponse) {
        registrationResponse?.let { apiResult ->
            when (apiResult) {
                is NetworkResult.Success -> {
                    isLoading.value = false
                    if (apiResult.networkData?.statusCode==200) {
                        navController.navigate(Screen.LoginScreen.route)
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
            .padding(10.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello,\nWelcome to the Register page",
            fontSize = 25.sp, color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 0.dp),
            )

        Row(
            modifier = Modifier
                .fillMaxWidth().wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment =Alignment.CenterVertically
        ) {
            Column (
                modifier = Modifier.padding(0.dp,0.dp,5.dp,0.dp).weight(1f)
            ){
                OutlinedTextField(
                    value = viewModel.userName.value,
                    onValueChange = { viewModel.userName.value = it },
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = "person")
                    },
                    label = {
                        Text(text = "first")
                    },
                    )
                Text(text = viewModel.tvUserNameError.value)
            }
            Column (
                modifier = Modifier.padding(5.dp,0.dp,0.dp,0.dp).weight(1f)
            ){
                OutlinedTextField(
                    value = viewModel.middleName.value,
                    onValueChange = { viewModel.middleName.value = it },
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrectEnabled = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = "person")
                    },
                    label = {
                        Text(text = "name")
                    }
                    )
                Text(text = viewModel.tvUserMiddleNameError.value)
            }
        }

//        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.fatherLastName.value,
            onValueChange = { viewModel.fatherLastName.value = it },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "person")
            },
            label = {
                Text(text = "fatherlastname")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = viewModel.tvFatherLastNameError.value,
            textAlign = TextAlign.Unspecified
        )
        OutlinedTextField(
            value = viewModel.motherLastName.value,
            onValueChange = { viewModel.motherLastName.value = it },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "person")
            },
            label = {
                Text(text = "motherlastname")
            },
            modifier = Modifier
                .fillMaxWidth()

        )
        Text(text = viewModel.tvMotherLastNameError.value)

        OutlinedTextField(
            value = viewModel.emailAddress.value,
            onValueChange = { viewModel.emailAddress.value = it },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
            },
            label = {
                Text(text = "emailaddress")
            },
            modifier = Modifier
                .fillMaxWidth()

        )
        Text(text = viewModel.dtvEmailAddressError.value)

//        Spacer(modifier = Modifier.height(8.dp))//
        OutlinedTextField(
            value = viewModel.mobileNumber.value,
            onValueChange = { viewModel.mobileNumber.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            leadingIcon = {
                Icon(Icons.Default.Phone, contentDescription = "phone")
            },
            label = {
                Text(text = "mobilenumber")
            },
            modifier = Modifier
                .fillMaxWidth()

        )
        Text(text = viewModel.tvMobileNumberError.value)

        // edit text for password
        OutlinedTextField(
            value = viewModel.userPassword.value,
            onValueChange = { viewModel.userPassword.value = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "lock")
            },
            label = {
                Text(text = "password")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(text = viewModel.tvUserPasswordError.value)
        OutlinedTextField(
            value = viewModel.userConfirmPassword.value,
            onValueChange = { viewModel.userConfirmPassword.value = it },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            singleLine = true,

            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "lock")
            },
            label = {
                Text(text = "confirmpassword")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(text = viewModel.tvUserConfirmPasswordError.value)


        // Login button
        OutlinedButton(
            onClick = {
                if (viewModel.inputsValidationForSignUp()){
                    viewModel.getRegistrationRequest()
                }
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 40.dp, 0.dp, 0.dp)
                ,
            shape = RoundedCornerShape(5)
        ) {
            Text(
                text = "Register",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
//        Spacer(modifier = Modifier.height(5.dp))//
        Text(
            text = "Already have account?",
            fontSize = 16.sp, color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 50.dp)
                .clickable {
                    navController.navigate(Screen.LoginScreen.route)
                },
            )
    }
}


