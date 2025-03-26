package com.fit2081.yushan33054754.digitalnutrition

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


    Surface(
        modifier = modifier.fillMaxSize(),
        color = myBlue
    ){
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            // hidden, but in case user scroll down
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
                    DropDownIDInput()
                    Spacer(modifier = Modifier.height(16.dp))

                    // phone input
                    PhoneNumberInput()
                    Spacer(modifier = Modifier.height(16.dp))

                    //helping text
                    LoginHelpingText()
                    Spacer(modifier = Modifier.height(16.dp))

                    //login button
                    LoginButton()
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
        text = "Back to Welcome Page...\n \n Loading...",
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
        text = "Login",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownIDInput() {
    var expanded by remember { mutableStateOf(false) }
    var selectedId by remember { mutableStateOf("") }

    //dummy ID
    val userIds = listOf("ID123", "ID456", "ID789", "ID101")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedId,
            onValueChange = {},
            label = { Text("Select your ID") },
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
            userIds.forEach { id ->
                DropdownMenuItem(
                    text = { Text(id) },
                    onClick = {
                        selectedId = id
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun PhoneNumberInput(){
    var phoneNumber by remember { mutableStateOf("") }

    OutlinedTextField(
        value = phoneNumber,
        // TODO -> check non integer value and length
        // TODO -> make a boolean, if valid input then button color blue, else gray
        onValueChange = { phoneNumber = it },
        label = { Text("Phone number") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LoginHelpingText(){
    Text(
        text = "This app is only for pre-registered users. " +
                "\n Please have your ID and phone number handy before continuing.",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun LoginButton(){
    //for toast and navigate page
    val context = LocalContext.current

    Button(
        onClick = {
            if (false){ //Login Success
                Toast.makeText(context, " Login Success ✅ ", Toast.LENGTH_SHORT).show()
                //to Homepage
                val intent = Intent(context, HomeView::class.java)
                context.startActivity(intent)
            } else if (false) { //Login Fail
                Toast.makeText(context, " Login Failed ❌ ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, " Unknown Error ❌ \n Please restart app ", Toast.LENGTH_SHORT).show()
            }

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(myBlue)
    ) {
        Text("Continue", fontSize = 18.sp)
    }
}