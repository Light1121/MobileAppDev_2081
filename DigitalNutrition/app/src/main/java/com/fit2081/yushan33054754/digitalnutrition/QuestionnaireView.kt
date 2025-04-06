package com.fit2081.yushan33054754.digitalnutrition

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.DigitalNutritionTheme
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.myBlue


val personalList = listOf(
    Personal(name = "Health Devotee", imageRes = R.drawable.persona_1, description = R.string.health_devotee_description),
    Personal(name = "Mindful Eater", imageRes = R.drawable.persona_2, description = R.string.mindful_eater_description),
    Personal(name = "Wellness Striver", imageRes = R.drawable.persona_3, description = R.string.wellness_striver_description),
    Personal(name = "Balance Seeker", imageRes = R.drawable.persona_4, description = R.string.balance_seeker_description),
    Personal(name = "Health Procrastinator", imageRes = R.drawable.persona_5, description = R.string.health_procrastinator_description),
    Personal(name = "Food Carefree", imageRes = R.drawable.persona_6, description = R.string.food_carefree_description)
)

class QuestionnaireView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalNutritionTheme {
                Questionnaire(navigateBack = { finish() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Questionnaire(navigateBack: ()->Unit) {
    val context = LocalContext.current
    val userId = currentUser?.userId?.toString() ?: ""
    val sharedPreferences = context.getSharedPreferences("user_${userId}_prefs", Context.MODE_PRIVATE)

    // Food categories state
    val foodCategories = remember {
        mutableStateMapOf(
            "Poultry" to sharedPreferences.getBoolean("food_Poultry", false),
            "Seafood" to sharedPreferences.getBoolean("food_Seafood", false),
            "Eggs" to sharedPreferences.getBoolean("food_Eggs", false),
            "Vegetables" to sharedPreferences.getBoolean("food_Vegetables", false),
            "Nuts/Seeds" to sharedPreferences.getBoolean("food_NutsSeeds", false),
            "Red Meat" to sharedPreferences.getBoolean("food_RedMeat", false),
            "Fruits" to sharedPreferences.getBoolean("food_Fruits", false),
            "Fish" to sharedPreferences.getBoolean("food_Fish", false),
            "Grains" to sharedPreferences.getBoolean("food_Grains", false)
        )
    }

    var selectedPersonaName by remember {
        mutableStateOf(sharedPreferences.getString("selected_persona", "") ?: "") }

    var timeMeal by remember {
        mutableStateOf(sharedPreferences.getString("time_meal", "00:00") ?: "00:00") }
    var timeSleep by remember {
        mutableStateOf(sharedPreferences.getString("time_sleep", "00:00") ?: "00:00") }
    var timeWake by remember {
        mutableStateOf(sharedPreferences.getString("time_wake", "00:00") ?: "00:00") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "Food Intake Questionnaire"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_page)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            FoodCategories(foodCategories)
            Spacer(modifier = Modifier.height(4.dp))

            PersonasText()
            Spacer(modifier = Modifier.height(4.dp))

            Personas(selectedPersonaName) { personaName ->
                selectedPersonaName = personaName
            }
            Spacer(modifier = Modifier.height(4.dp))

            Timing(
                timeMeal = timeMeal,
                timeSleep = timeSleep,
                timeWake = timeWake,
                onTimeMealChange = { timeMeal = it },
                onTimeSleepChange = { timeSleep = it },
                onTimeWakeChange = { timeWake = it }
            )
            Spacer(modifier = Modifier.height(4.dp))

            SaveButton(
                foodCategories = foodCategories,
                selectedPersona = selectedPersonaName,
                onSaveClick = {
                    saveUserPreferences(
                        context,
                        userId,
                        foodCategories,
                        selectedPersonaName,
                        timeMeal,
                        timeSleep,
                        timeWake
                    )
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun FoodCategories(foodCategories: MutableMap<String, Boolean>) {
    Text(
        text = stringResource(R.string.food_cat_heading),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
    Column(
        modifier = Modifier.padding(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            FoodItem("Poultry", foodCategories)
            FoodItem("Seafood", foodCategories)
            FoodItem("Eggs", foodCategories)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            FoodItem("Vegetables", foodCategories)
            FoodItem("Nuts/Seeds", foodCategories)
            FoodItem("Red Meat", foodCategories)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            FoodItem("Fruits", foodCategories)
            FoodItem("Fish", foodCategories)
            FoodItem("Grains", foodCategories)
        }
    }
}

@Composable
fun FoodItem(name: String, foodCategories: MutableMap<String, Boolean>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = foodCategories[name] ?: false,
            onCheckedChange = { isChecked ->
                foodCategories[name] = isChecked
            }
        )
        Text(
            text = name,
            fontSize = 12.5.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun PersonasText() {
    Column {
        Text(
            text = stringResource(R.string.your_persona),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.persona_explain),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun Personas(selectedPersonaName: String, onPersonaSelected: (String) -> Unit) {
    var selectedPersona by remember { mutableStateOf<Personal?>(null) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PersonaButton(
                text = stringResource(R.string.health_devotee),
                modifier = Modifier.weight(1f),
                onClick = { selectedPersona = personalList[0] }
            )
            Spacer(modifier = Modifier.width(8.dp))
            PersonaButton(
                text = stringResource(R.string.mindful_eater),
                modifier = Modifier.weight(1f),
                onClick = { selectedPersona = personalList[1] }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PersonaButton(
                text = stringResource(R.string.wellness_striver),
                modifier = Modifier.weight(1f),
                onClick = { selectedPersona = personalList[2] }
            )
            Spacer(modifier = Modifier.width(8.dp))
            PersonaButton(
                text = stringResource(R.string.balance_seeker),
                modifier = Modifier.weight(1f),
                onClick = { selectedPersona = personalList[3] }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PersonaButton(
                text = stringResource(R.string.health_procrastinator),
                modifier = Modifier.weight(1f),
                onClick = { selectedPersona = personalList[4] }
            )
            Spacer(modifier = Modifier.width(8.dp))
            PersonaButton(
                text = stringResource(R.string.food_carefree),
                modifier = Modifier.weight(1f),
                onClick = { selectedPersona = personalList[5] }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        PersonaDropdown(
            selectedPersona = selectedPersonaName,
            onPersonaSelected = { persona ->
                onPersonaSelected(persona)
            }
        )
    }
    if (selectedPersona != null) {
        PersonaDialog(selectedPersona = selectedPersona) {
            selectedPersona = null
        }
    }
}

@Composable
fun PersonaButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PersonaDialog(selectedPersona: Personal?, onDismiss: () -> Unit) {
    if (selectedPersona == null) return

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                // Header image
                Image(
                    painter = painterResource(selectedPersona.imageRes),
                    contentDescription = selectedPersona.name,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(4.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Persona name
                Text(
                    text = selectedPersona.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                Text(
                    text = stringResource(selectedPersona.description),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dismiss button
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(48.dp)
                ) {
                    Text(
                        text = stringResource(R.string.dismiss),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonaDropdown(
    selectedPersona: String,
    onPersonaSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val personas = listOf(
        "Health Devotee", "Mindful Eater", "Wellness Striver",
        "Balance Seeker", "Health Procrastinator", "Food Carefree"
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selectedPersona,
            onValueChange = {}, // No direct input
            label = { Text("Which persona best fits you?") },
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
            Column(
                modifier = Modifier.height(200.dp).verticalScroll(rememberScrollState())
            ) {
                personas.forEach { persona ->
                    DropdownMenuItem(
                        text = { Text(persona) },
                        onClick = {
                            onPersonaSelected(persona)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}



@Composable
fun Timing(
    timeMeal: String,
    timeSleep: String,
    timeWake: String,
    onTimeMealChange: (String) -> Unit,
    onTimeSleepChange: (String) -> Unit,
    onTimeWakeChange: (String) -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.timing),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        TimeInput(
            label = stringResource(R.string.time_meal),
            time = timeMeal,
            onTimeChange = onTimeMealChange
        )
        Spacer(modifier = Modifier.height(8.dp))

        TimeInput(
            label = stringResource(R.string.time_sleep),
            time = timeSleep,
            onTimeChange = onTimeSleepChange
        )
        Spacer(modifier = Modifier.height(8.dp))

        TimeInput(
            label = stringResource(R.string.time_wake),
            time = timeWake,
            onTimeChange = onTimeWakeChange
        )
    }
}

@Composable
fun TimeInput(
    label: String,
    time: String,
    onTimeChange: (String) -> Unit
) {
    val context = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
    val mMinute = mCalendar.get(Calendar.MINUTE)

    val mTimePickerDialog = TimePickerDialog(
        context,
        { _, hour, minute ->
            onTimeChange(String.format("%02d:%02d", hour, minute))
        },
        mHour,
        mMinute,
        true
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { mTimePickerDialog.show() },
            modifier = Modifier.width(120.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Time picker"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = time)
            }
        }
    }
}

@Composable
fun SaveButton(
    foodCategories: Map<String, Boolean>,
    selectedPersona: String,
    onSaveClick: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                try {
                    // check food category any select
                    val anyFoodSelected = foodCategories.any { it.value }

                    // not null for persona
                    val isPersonaSelected = selectedPersona.isNotEmpty()

                    if (!anyFoodSelected || !isPersonaSelected) { // if any incomplete
                        toast(context, context.getString(R.string.incomplete_answer))
                    } else {
                        onSaveClick() //done and save
                        toast(context, context.getString(R.string.answer_saved))
                        //back to home
                        val intent = Intent(context, HomeView::class.java)
                        context.startActivity(intent)
                    }
                } catch (e: Exception) {
                    toast(context, context.getString(R.string.general_unknown_error_message))
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = myBlue,
                contentColor = Color.White
            ),
            modifier = Modifier.width(128.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Save",
                    color = Color.White
                )
            }
        }
    }
}

fun saveUserPreferences(
    context: Context,
    userId: String,
    foodCategories: Map<String, Boolean>,
    selectedPersona: String,
    timeMeal: String,
    timeSleep: String,
    timeWake: String
) {
    val sharedPreferences = context.getSharedPreferences("user_${userId}_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    for ((category, isSelected) in foodCategories) {
        editor.putBoolean("food_${category.replace("/", "").replace(" ", "")}", isSelected)
    }

    editor.putString("selected_persona", selectedPersona)

    editor.putString("time_meal", timeMeal)
    editor.putString("time_sleep", timeSleep)
    editor.putString("time_wake", timeWake)

    editor.putBoolean("hasCompletedQuestionnaire", true)

    editor.apply()
}