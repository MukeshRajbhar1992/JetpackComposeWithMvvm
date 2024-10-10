package com.example.jetpackcomposeproject.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    appValue: MutableState<String>,
    appErrorMSG: MutableState<String>,
    labelValue: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    ) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(

            value = appValue.value,
            onValueChange = {
                onValueChange(it)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = true,
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            leadingIcon = {
                if (leadingIcon != null) {
                    leadingIcon()
                }
            },
            label = {
                Text(text = labelValue)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        )
        Text(
            text = appErrorMSG.value,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 5.dp, 0.dp, 0.dp)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun AppTextFieldPreview() {
    val name: MutableState<String> = remember { mutableStateOf("Mukesh Rajbhar") }
    val error: MutableState<String> = remember { mutableStateOf("Please enter valid name") }
    AppTextField(appValue = name, appErrorMSG = error, labelValue = "Name", leadingIcon = {
        Icon(Icons.Default.Person, contentDescription = "person")
    }) {

    }
}