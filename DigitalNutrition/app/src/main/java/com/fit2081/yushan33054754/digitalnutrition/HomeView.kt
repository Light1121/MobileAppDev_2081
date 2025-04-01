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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.DigitalNutritionTheme
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.myBlue

class HomeView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalNutritionTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen()
            }
            composable("insights") {
                InsightsScreen()
            }
            composable("nutricoach") {
                NutriCoachScreen()
            }
            composable("settings") {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    // Sample user data
    val FakeUser = remember { FakeUser("4", 80) }
    val hasCompletedQuestionnaire = true

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserGreeting(userName = FakeUser.name, hasCompletedQuestionnaire = hasCompletedQuestionnaire)
        Spacer(modifier = Modifier.height(16.dp))

        MealImage()
        Spacer(modifier = Modifier.height(16.dp))

        UserScoreSection(score = FakeUser.score)
        Spacer(modifier = Modifier.height(24.dp))

        FoodQualityExplanation()
    }
}

@Composable
fun UserGreeting(userName: String, hasCompletedQuestionnaire: Boolean) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Hello,",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = userName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = if (hasCompletedQuestionnaire)
                    stringResource(R.string.home_questionnaire_finished)
                else  stringResource(R.string.home_questionnaire_unfinished),
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }

        Button(onClick = {
            try {
                val intent = Intent(context, QuestionnaireView::class.java)
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
        painter = painterResource(id = R.drawable.main_page_dish),
        contentDescription = "Balanced meal plate",
        modifier = Modifier
            .size(240.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun UserScoreSection(score: Int) {
    val scoreColor = when {
        score >= 71 -> Color.Green
        score in 51..70 -> Color.Yellow
        else -> Color.Red
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Score",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Up Arrow",
                tint = Color.Gray
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Your Food Quality Score",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "$score/100",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = scoreColor
                )
            }
        }
    }
}

@Composable
fun FoodQualityExplanation() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.FoodQualityScoreExplainHeading),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.FoodQualityScoreExplainContentPt1),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.FoodQualityScoreExplainContentPt2),
            fontSize = 14.sp
        )
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", "home", Icons.Default.Home),
        BottomNavItem("Insights", "insights", Icons.Default.Home),
        BottomNavItem("NutriCoach", "nutricoach", Icons.Default.Home),
        BottomNavItem("Settings", "settings", Icons.Default.Settings)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

// Empty placeholder screens
@Composable fun InsightsScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Insights Screen")
    }
}

@Composable fun NutriCoachScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("NutriCoach Screen")
    }
}

@Composable fun SettingsScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings Screen")
    }
}

// Supporting data classes and utilities
data class FakeUser(val name: String, val score: Int)

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

//@Composable
//fun HomePage(modifier: Modifier = Modifier){
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        // Hello user text
//        UserNameGreeting()
//        Spacer(modifier = Modifier.height(8.dp))
//
//        //entry of questionnaire section
//        QuestionnaireSection()
//        Spacer(modifier = Modifier.height(8.dp))
//
//        //Meal info image
//        MealImage()
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // User's FoodQuality score section
//        //MealScoreSection
//        Spacer(modifier = Modifier.height(16.dp))
//
//        //Food Quality explanation text
//        FoodQualityText()
//        Spacer(modifier = Modifier.height(32.dp))
//
//        //Bottom Navigation Bar, having able to navigate both
//        //BotNavigationBar()
//    }
//}
//
//@Composable
//fun UserNameGreeting(){
//    Text(
//        text = "Hi, \n {User.userId}",
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Bold,
//        textAlign = TextAlign.Center,
//        modifier = Modifier.fillMaxWidth()
//    )
//}
//
//@Composable
//fun QuestionnaireSection(){
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        //explanation text for questionnaire
//        //TODO --> see if user have done questionnaire by bool, check exist file
//        Text(
//            //text = stringResource(R.string.home_questionnaire_unfinished),
//            text = stringResource(R.string.home_questionnaire_unfinished),
//            fontSize = 8.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//        )
//        val context = LocalContext.current
//        Button(onClick = {
//            try {
//                val intent = Intent(context, QuestionnaireView::class.java)
//                context.startActivity(intent)
//            } catch (e: Exception) {
//                toast(context, context.getString(R.string.general_unknown_error_message))
//            }
//        },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = myBlue,
//                contentColor = Color.White
//            ),
//            modifier = Modifier.width(90.dp).height(36.dp)
//        ) {
//            Text("Edit")
//        }
//    }
//}
//
//@Composable
//fun MealImage() {
//    Image(
//        painter = painterResource(R.drawable.main_page_dish),
//        contentDescription = stringResource(R.string.logo_description),
//        modifier = Modifier.size(256.dp)
//    )
//
//}
//
//@Composable
//fun FoodQualityText(){
//    Text(
//        text = stringResource(R.string.FoodQualityScoreExplainHeading),
//        fontWeight = FontWeight.Bold,
//        modifier = Modifier.fillMaxWidth()
//    )
//    Spacer(Modifier.height(8.dp))
//    Text(
//        text = stringResource(R.string.FoodQualityScoreExplainContentPt1),
//        style = MaterialTheme.typography.bodySmall.copy(
//            fontStyle = FontStyle.Normal,
//            textAlign = TextAlign.Center
//        ),
//        modifier = Modifier.fillMaxWidth()
//    )
//    Spacer(Modifier.height(16.dp))
//    Text(
//        text = stringResource(R.string.FoodQualityScoreExplainContentPt2),
//        style = MaterialTheme.typography.bodySmall.copy(
//            fontStyle = FontStyle.Normal,
//            textAlign = TextAlign.Center
//        ),
//        modifier = Modifier.fillMaxWidth()
//    )
//}