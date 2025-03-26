package com.fit2081.yushan33054754.digitalnutrition

import android.content.Intent
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.DigitalNutritionTheme
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.myBlue



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalNutritionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WelcomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // show Name and Icon
            AppNameWithIcon()
            Spacer(modifier = Modifier.height(8.dp))

            // Disclaimer Texts
            DisclaimerText()
            Spacer(modifier = Modifier.height(32.dp))

            // Login Button
            ToLoginButton()
            Spacer(modifier = Modifier.height(32.dp))

            // Student Info
            StudentInfoText()
        }
    }
}

@Composable
fun AppNameWithIcon(){
    // App Name
    val appName = "NutriTrack"
    Text(
        text = appName,
        fontSize = 64.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Logo
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "NutriTrack logo",
        modifier = Modifier.size(256.dp)
    )
}

@Composable
fun DisclaimerText() {
    val disclaimerTextP1:String =
        "This app provides general health and nutrition information " +
                "for educational purposes only. " +
                "It is not intended as medical advice, diagnosis, or treatment. " +
                "Always consult a qualified healthcare professional before making any changes to your" +
                " diet, exercise, or health regimen. \n Use this app at your own risk."
    val disclaimerTextP2:String =
        "If you’d like to an Accredited Practicing Dietitian (APD), " +
                "\n visit the Monash Nutrition/Dietetics Clinic " +
                "\n (discounted rates for students):"
    val disclaimerTextURL = "https://www.monash.edu/medicine/scs/nutrition/research/facilities"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //UPPER HALF text
        Text(
            text = disclaimerTextP1,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall.copy(
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        //LOWER HALF
        Text(
            text = disclaimerTextP2,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall.copy(
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
        //URL
        Text(
            text = disclaimerTextURL,
            fontWeight = FontWeight.Bold,
            color = myBlue,
            textDecoration = TextDecoration.Underline,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ToLoginButton() {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(context, LoginView::class.java)
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = myBlue,
            contentColor = Color.White
        ),
        modifier = Modifier
            .width(256.dp)
            .height(48.dp)
    ) {
        Text("Login")
    }
}

@Composable
fun StudentInfoText() {
    //show student information
    val studentName = "YuShan Lin"
    val studentID = 33054854

    Text(
        text = "Design with <3 \n Student: $studentName \n Student ID: $studentID",
        style = MaterialTheme.typography.bodySmall.copy(
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
}