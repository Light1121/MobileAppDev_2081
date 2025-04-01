package com.fit2081.yushan33054754.digitalnutrition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.DigitalNutritionTheme
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.myBlue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import java.io.BufferedReader
import java.io.InputStreamReader


class LoginView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalNutritionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(
                        modifier = Modifier.padding(innerPadding),
                        onDismiss = { finish() }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(modifier: Modifier = Modifier, onDismiss: () -> Unit) {
    //make sure it is fully expanded for the bottom sheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    //globe for id validate
    var selectedId by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var loginError = remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = myBlue
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            // hidden, but in case user close the bottom sheet
            HiddenLoginBackGround()

            // main login page
            ModalBottomSheet(
                containerColor = Color.White,
                sheetState = sheetState,
                onDismissRequest = {
                    onDismiss()
                },
            ) {
                Column(
                    modifier = Modifier
                        .height(1024.dp)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    // Heading text says login
                    LoginHeading()
                    Spacer(modifier = Modifier.height(32.dp))

                    //ID input dropdown bar
                    DropDownIDInput(selectedId) { selectedId = it }
                    Spacer(modifier = Modifier.height(16.dp))

                    // phone input
                    PhoneNumberInput(phoneNumber, loginError) { phoneNumber = it }
                    Spacer(modifier = Modifier.height(16.dp))

                    //helping text
                    LoginHelpingText()
                    Spacer(modifier = Modifier.height(16.dp))

                    //login button
                    LoginButton(selectedId,phoneNumber, loginError)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }





        }
    }
}

@Composable
fun HiddenLoginBackGround(){
    // function from MainActivity
    AppNameWithIcon()
    Spacer(modifier = Modifier.height(128.dp))

    //Text says loading to welcoming page
    //user will only see this when scroll BottomSheet down
    //which means back to home page
    Text(
        text = stringResource(R.string.background_loading_to_welcome),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    )
}

@Composable
fun LoginHeading() {
    Text(
        text = stringResource(R.string.login),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownIDInput(
    selectedId: String,
    onIdSelected: (String) -> Unit
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    val userIds = remember(context) {
        readIDsFromCSV(context, "user_data.csv")
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedId,
            onValueChange = {}, //no input just select
            label = { Text(stringResource(R.string.select_ID)) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column(  // column can be scroll
                modifier = Modifier.height(300.dp).verticalScroll(rememberScrollState())
            ) {
                userIds.forEach { id ->
                    DropdownMenuItem(
                        text = { Text(id) },
                        onClick = {
                            onIdSelected(id)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PhoneNumberInput(
    phoneNumber: String,
    loginError: MutableState<Boolean>,
    onPhoneNumberChange: (String) -> Unit
) {
    var nonDigitError by remember { mutableStateOf(false) }
    var nonPhoneError by remember { mutableStateOf(false) }
    var lengthError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = phoneNumber,
        onValueChange = { input ->
            loginError.value = false //clean error for re-input
            onPhoneNumberChange(input)
            nonDigitError = input.any { !it.isDigit() }
            nonPhoneError = !input.startsWith("614")
            lengthError = input.length > 11
        },
        label = { Text(stringResource(R.string.phone_number)) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = nonDigitError || loginError.value,
        supportingText = {
            when {
                nonDigitError -> Text(
                    text = stringResource(R.string.input_only_number),
                    color = Color.Red
                )
                loginError.value -> Text(
                    text = stringResource(R.string.login_wrong_ID_password),
                    color = Color.Red
                )
                nonPhoneError -> Text(
                    text = stringResource(R.string.input_au_phone),
                    color = Color.Red
                )
                lengthError -> Text(
                    text = stringResource(R.string.input_correct_length),
                    color = Color.Red
                )

            }
        }
    )
}

@Composable
fun LoginHelpingText(){
    Text(
        text = stringResource(R.string.login_helping),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun LoginButton(selectedId:String,phoneNumber: String, loginError: MutableState<Boolean>){
    //for toast and navigate page
    val context = LocalContext.current

    Button(
        onClick = {
            try {
                val isValid = loginValidation(context, selectedId, phoneNumber)

                if (isValid){ //Login Success
                    toast(context, context.getString(R.string.login_success))
                    //to Homepage
                    loginError.value = false
                    val intent = Intent(context, HomeView::class.java)
                    context.startActivity(intent)
                } else { //Login Fail
                    toast(context, context.getString(R.string.login_failed))
                    loginError.value = true
                }
            } catch (e: Exception) {
                toast(context, context.getString(R.string.general_unknown_error_message))
            }


        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(myBlue)
    ) {
        Text(stringResource(R.string.continue_button), fontSize = 18.sp)
    }
}


fun readIDsFromCSV(context: Context, fileName: String): List<String> {

    var userIDList = mutableListOf<String>()

    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))

        userIDList = reader.useLines { lines ->
            lines.drop(1)
                .map {  it.split(",").getOrNull(1)?.trim() ?: ""  }
                .filter { it.isNotEmpty() }
                .toList()
        }.toMutableList()
    } catch (e: Exception) {
        toast(context, context.getString(R.string.read_rile_error))
    }
    return userIDList
}

fun loginValidation(context: Context, selectedId: String, phoneNumber: String,
                    fileName: String = "user_data.csv"): Boolean {

    if (selectedId.isEmpty() || phoneNumber.isEmpty()) {
        return false
    }

    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var isValid = false
        reader.useLines { lines ->
            lines.forEach { line ->
                val columns = line.split(",")
                val phone = columns[0].trim()
                val userId = columns[1].trim()
                if (userId == selectedId && phone == phoneNumber) {
                    return true
                }
            }
        }
        return isValid
    } catch (e: Exception) {
        toast(context, context.getString(R.string.read_rile_error))
        return false
    }
}


