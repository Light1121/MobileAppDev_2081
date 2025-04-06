package com.fit2081.yushan33054754.digitalnutrition

import android.content.Context
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

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
            composable("home") { HomeScreen() }
            composable("insights") { InsightsScreen() }
            composable("nutricoach") { NutriCoachScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserGreeting(userName = currentUser?.userId.toString())
        Spacer(modifier = Modifier.height(4.dp))
        QuestionnaireSection(checkQuestionnaireStatus(context, currentUser?.userId.toString()))
        Spacer(modifier = Modifier.height(16.dp))
        MealImage()
        Spacer(modifier = Modifier.height(16.dp))

        UserScoreSection(score = currentUser?.let { calculateScore(it) } ?: 0.0)
        Spacer(modifier = Modifier.height(24.dp))

        FoodQualityExplanation()
    }
}

@Composable
fun UserGreeting(userName: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.hello),
            fontSize = 16.sp,
        )
        Text(
            text = "User: $userName",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun QuestionnaireSection(hasCompletedQuestionnaire: Boolean) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (hasCompletedQuestionnaire)
                    stringResource(R.string.home_questionnaire_finished)
                else  stringResource(R.string.home_questionnaire_unfinished),
                fontSize = 11.sp
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
            Text(stringResource(R.string.edit))
        }
    }
}

@Composable
fun MealImage() {
    Image(
        painter = painterResource(id = R.drawable.main_page_dish),
        contentDescription = stringResource(R.string.main_page_dish_des),
        modifier = Modifier
            .size(240.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun UserScoreSection(score: Double) {
    val scoreColor = when {
        score >= 50 -> Color.Green
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
            Text(
                text = "Your Food Quality Score",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(64.dp))
            Text(
                text = "$score/100",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = scoreColor
            )
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
        BottomNavItem("Insights", "insights", Icons.Default.Info),
        BottomNavItem("NutriCoach", "nutricoach", Icons.Default.AccountCircle),
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

@Composable
fun InsightsScreen() {
    val user = currentUser
    if (user == null) {
        return
    }

    val scores = getScore(user)
    val totalScore = scores[0]

    val scoreList = listOf(
        "Discretionary Foods" to Pair(scores[1], 10),
        "Vegetables" to Pair(scores[2], 5),
        "Vegetables Variation" to Pair(scores[3], 5),
        "Fruit" to Pair(scores[4], 5),
        "Fruit Variation" to Pair(scores[5], 5),
        "Grains & Cereals" to Pair(scores[6], 5),
        "Whole Grains" to Pair(scores[7], 5),
        "Meat & Alternatives" to Pair(scores[8], 10),
        "Dairy" to Pair(scores[9], 10),
        "Water" to Pair(scores[10], 5),
        "Unsaturated Fat" to Pair(scores[11], 5),
        "Saturated Fat" to Pair(scores[12], 5),
        "Sodium" to Pair(scores[13], 10),
        "Sugar" to Pair(scores[14], 10),
        "Alcohol" to Pair(scores[15], 5)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.total_score),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))

        scoreList.forEach { (name, scorePair) ->
            ScoreSlider(name, scorePair.first.toFloat(), scorePair.second)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Total Food Quality Score",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Slider(
                value = totalScore.toFloat(),
                onValueChange = {},
                modifier = Modifier.weight(1f),
                valueRange = 0f..100f,
                enabled = false,
                colors = SliderDefaults.colors(
                    disabledActiveTrackColor = myBlue,
                    disabledThumbColor = myBlue
                )

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                fontSize = 14.sp,
                text = "$totalScore/100"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            Button(
                onClick = {
                    val shareText = generateShareText(scoreList, totalScore)
                    shareInsights(context, shareText)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Filled.Share, contentDescription = "Share")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Share with someone",
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Filled.Edit, contentDescription = "Improve")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Improve my diet!",
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ScoreSlider(name: String, value: Float, totalScore: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(120.dp),
            fontSize = 14.sp,
            text = name
        )
        Slider(
            modifier = Modifier.weight(1f),
            value = value,
            onValueChange = {},
            valueRange = 0f..totalScore.toFloat(),
            enabled = false,
            colors = SliderDefaults.colors(
                disabledActiveTrackColor = myBlue,
                disabledThumbColor = myBlue
            )
        )
        Text(
            textAlign = TextAlign.End,
            modifier = Modifier.width(60.dp),
            fontSize = 14.sp,
            text = "$value/$totalScore"
        )
    }
}

fun generateShareText(
    scoreList: List<Pair<String, Pair<Double, Int>>>,
    totalScore: Double): String {
    var result = ""

    for ((category, scores) in scoreList) {
        result += "$category: ${scores.first}/${scores.second}\n"
    }
    result += "\nTotal: $totalScore/100"

    return result
}

fun shareInsights(context: Context, text: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }

    context.startActivity(
        Intent.createChooser(
            shareIntent,
            context.getString(R.string.share_insights_title)
        )
    )
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

