package com.fit2081.yushan33054754.digitalnutrition

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.DigitalNutritionTheme
import com.fit2081.yushan33054754.digitalnutrition.ui.theme.myBlue

class QuestionnaireView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalNutritionTheme {
                Questionnaire()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Questionnaire() {
    var selectedPersona by remember { mutableStateOf("") }

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
                    IconButton(onClick = { /* No functionality */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
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
            FoodCategories()
            Spacer(modifier = Modifier.height(16.dp))

            PersonasText()
            Spacer(modifier = Modifier.height(16.dp))

            Personas()
            Spacer(modifier = Modifier.height(16.dp))

            Timing()
            Spacer(modifier = Modifier.height(24.dp))

            SaveButton()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun FoodCategories() {
    Text(
        text = "Tick all food categories you can eat",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FoodCategoryCheckbox(label = "Poultry")
            FoodCategoryCheckbox(label = "Seafood")
            FoodCategoryCheckbox(label = "Eggs")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FoodCategoryCheckbox(label = "Vegetables")
            FoodCategoryCheckbox(label = "Nuts/Seeds")
            FoodCategoryCheckbox(label = "Red Meat")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FoodCategoryCheckbox(label = "Fruits")
            FoodCategoryCheckbox(label = "Fish")
            FoodCategoryCheckbox(label = "Grains")
        }
    }
}


@Composable
fun FoodCategoryCheckbox(label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = { /* No functionality */ },
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun PersonasText() {
    Column {
        Text(
            text = "Your Persona",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "People can be broadly classified into 6 different types based on their eating preferences. Click on each button below to find out the different types, and select the type that best fits you!",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun Personas() {
    var selectedPersona by remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PersonaButton(text = "Health Devotee", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            PersonaButton(text = "Mindful Eater", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PersonaButton(text = "Wellness Striver", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            PersonaButton(text = "Balance Seeker", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PersonaButton(text = "Health Procrastinator", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            PersonaButton(text = "Food Carefree", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        PersonaDropdown(
            selectedPersona = selectedPersona,
            onPersonaSelected = { persona -> selectedPersona = persona }
        )
    }
}

@Composable
fun PersonaButton(text: String, modifier: Modifier = Modifier) {
    val primaryColor = Color(0xFF3F5C9E)

    Button(
        onClick = { /* No functionality */ },
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
fun Timing() {
    Column {
        Text(
            text = "Timing",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        TimeInput(
            label = "What time of day approx. do you normally eat your biggest meal?"
        )
        Spacer(modifier = Modifier.height(12.dp))

        TimeInput(
            label = "What time of day approx. do you go to sleep at night?"
        )
        Spacer(modifier = Modifier.height(12.dp))

        TimeInput(
            label = "What time of day approx. do you wake up in the morning?"
        )
    }
}

@Composable
fun TimeInput(label: String) {
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
        OutlinedTextField(
            value = "00:00",
            onValueChange = { /* No functionality */ },
            modifier = Modifier.width(120.dp),
            readOnly = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Time picker"
                )
            },
            singleLine = true
        )
    }
}

@Composable
fun SaveButton() {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                try {
                    val intent = Intent(context, HomeView::class.java)
                    context.startActivity(intent)
                } catch (e: Exception) {
                    toast(context, context.getString(R.string.general_unknown_error_message))
                }
            },
            colors= ButtonDefaults.buttonColors(
                containerColor = myBlue,
                contentColor = Color.White),
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