package com.fit2081.yushan33054754.digitalnutrition

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.DigitalNutritionTheme
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.myBlue

class HomeView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalNutritionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomePage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomePage(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Hello user text
        UserNameGreeting()
        Spacer(modifier = Modifier.height(8.dp))

        //entry of questionnaire section
        QuestionnaireSection()
        Spacer(modifier = Modifier.height(8.dp))

        //Meal info image
        MealImage()
        Spacer(modifier = Modifier.height(8.dp))

        // User's FoodQuality score section
        //MealScoreSection
        Spacer(modifier = Modifier.height(16.dp))

        //Food Quality explanation text
        FoodQualityText()
        Spacer(modifier = Modifier.height(32.dp))

        //Bottom Navigation Bar
        //BotNavigationBar()
    }
}

@Composable
fun UserNameGreeting(){
    Text(
        text = "Hi, \n {User.userId}",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun QuestionnaireSection(){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //explanation text for questionnaire
        //TODO --> see if user have done questionnaire by bool, check exist file
        Text(
            //text = stringResource(R.string.home_questionnaire_unfinished),
            text = stringResource(R.string.home_questionnaire_unfinished),
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        val context = LocalContext.current
        Button(onClick = {
            try {
                val intent = Intent(context, LoginView::class.java)
                context.startActivity(intent)
            } catch (e: Exception) {
                toast(context, context.getString(R.string.general_unknown_error_message))
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = myBlue,
                contentColor = Color.White
            ),
            modifier = Modifier.width(90.dp).height(36.dp)
        ) {
            Text("Edit")
        }
    }
}

@Composable
fun MealImage() {
    Image(
        painter = painterResource(R.drawable.main_page_dish),
        contentDescription = stringResource(R.string.logo_description),
        modifier = Modifier.size(256.dp)
    )

}

@Composable
fun FoodQualityText(){
    Text(
        text = stringResource(R.string.FoodQualityScoreExplainHeading),
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(8.dp))
    Text(
        text = stringResource(R.string.FoodQualityScoreExplainContentPt1),
        style = MaterialTheme.typography.bodySmall.copy(
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.FoodQualityScoreExplainContentPt2),
        style = MaterialTheme.typography.bodySmall.copy(
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
}