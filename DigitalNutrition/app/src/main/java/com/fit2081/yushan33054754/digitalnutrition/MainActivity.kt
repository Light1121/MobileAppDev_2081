package com.fit2081.yushan33054754.digitalnutrition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

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
import androidx.compose.ui.res.stringResource
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

    Text(
        text = stringResource(R.string.app_name),
        fontSize = 64.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Logo
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = stringResource(R.string.logo_description),
        modifier = Modifier.size(256.dp)
    )
}

@Composable
fun DisclaimerText() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //UPPER HALF text
        Text(
            text = stringResource(R.string.disclaimer_text_contentP1),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall.copy(
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        //LOWER HALF
        Text(
            text = stringResource(R.string.disclaimer_text_contentP2),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall.copy(
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
        // no spacer needed
        //URL
        Text(
            text = stringResource(R.string.disclaimer_text_URL),
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
    // for navigation and toast
    val context = LocalContext.current

    Button(
        onClick = {
            // to login
            try {
                val intent = Intent(context, LoginView::class.java)
                context.startActivity(intent)
            } catch (e: Exception) {
                toast(context,context.getString(R.string.general_unknown_error_message))
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = myBlue,
            contentColor = Color.White
        ),
        modifier = Modifier
            .width(256.dp)
            .height(48.dp)
    ) {
        Text(stringResource(R.string.login))
    }
}

@Composable
fun StudentInfoText() {
    Text(
        text = stringResource(R.string.student_text),
        style = MaterialTheme.typography.bodySmall.copy(
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

fun toast(context: Context, displayText: String) {
    Toast.makeText(context, displayText, Toast.LENGTH_SHORT).show()
}