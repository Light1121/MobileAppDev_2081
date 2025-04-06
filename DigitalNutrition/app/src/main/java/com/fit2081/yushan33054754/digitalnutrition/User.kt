package com.fit2081.yushan33054754.digitalnutrition

data class User( //ChatGPT were used to build write the attribute of user
// This is done by prompting AI to read the csv file then write the class in given format
    val phoneNumber: String,
    val userId: Int,
    val sex: String,
    val HEIFAtotalscoreMale: Double,
    val HEIFAtotalscoreFemale: Double,
    val discretionaryHEIFAscoreMale: Double,
    val discretionaryHEIFAscoreFemale: Double,
    val discretionaryServeSize: Double,
    val vegetablesHEIFAscoreMale: Double,
    val vegetablesHEIFAscoreFemale: Double,
    val vegetablesWithLegumesAllocatedServeSize: Double,
    val legumesAllocatedVegetables: Double,
    val vegetablesVariationsScore: Double,
    val vegetablesCruciferous: Double,
    val vegetablesTuberAndBulb: Double,
    val vegetablesOther: Double,
    val legumes: Double,
    val vegetablesGreen: Double,
    val vegetablesRedAndOrange: Double,
    val fruitHEIFAscoreMale: Double,
    val fruitHEIFAscoreFemale: Double,
    val fruitServeSize: Double,
    val fruitVariationsScore: Double,
    val fruitPome: Double,
    val fruitTropicalAndSubtropical: Double,
    val fruitBerry: Double,
    val fruitStone: Double,
    val fruitCitrus: Double,
    val fruitOther: Double,
    val grainsAndCerealsHEIFAscoreMale: Double,
    val grainsAndCerealsHEIFAscoreFemale: Double,
    val grainsAndCerealsServeSize: Double,
    val grainsAndCerealsNonWholeGrains: Double,
    val wholeGrainsHEIFAscoreMale: Double,
    val wholeGrainsHEIFAscoreFemale: Double,
    val wholeGrainsServeSize: Double,
    val meatAndAlternativesHEIFAscoreMale: Double,
    val meatAndAlternativesHEIFAscoreFemale: Double,
    val meatAndAlternativesWithLegumesAllocatedServeSize: Double,
    val legumesAllocatedMeatAndAlternatives: Double,
    val dairyAndAlternativesHEIFAscoreMale: Double,
    val dairyAndAlternativesHEIFAscoreFemale: Double,
    val dairyAndAlternativesServeSize: Double,
    val sodiumHEIFAscoreMale: Double,
    val sodiumHEIFAscoreFemale: Double,
    val sodiumMgMilligrams: Double,
    val alcoholHEIFAscoreMale: Double,
    val alcoholHEIFAscoreFemale: Double,
    val alcoholStandardDrinks: Double,
    val waterHEIFAscoreMale: Double,
    val waterHEIFAscoreFemale: Double,
    val water: Double,
    val waterTotalmL: Double,
    val beverageTotalmL: Double,
    val sugarHEIFAscoreMale: Double,
    val sugarHEIFAscoreFemale: Double,
    val sugar: Double,
    val saturatedFatHEIFAscoreMale: Double,
    val saturatedFatHEIFAscoreFemale: Double,
    val saturatedFat: Double,
    val unsaturatedFatHEIFAscoreMale: Double,
    val unsaturatedFatHEIFAscoreFemale: Double,
    val unsaturatedFatServeSize: Double,
)

fun buildUser(csvLine: String): User {
    //ChatGPT were used to build write the attribute of user
// This is done by prompting AI to read the csv file then write the class in given format
    val fields = csvLine.split(",").map { it.trim() }

    return User(
        phoneNumber = fields[0].toString(),
        userId = fields[1].toInt(),
        sex = fields[2].toString(),
        HEIFAtotalscoreMale = fields[3].toDouble(),
        HEIFAtotalscoreFemale = fields[4].toDouble(),
        discretionaryHEIFAscoreMale = fields[5].toDouble(),
        discretionaryHEIFAscoreFemale = fields[6].toDouble(),
        discretionaryServeSize = fields[7].toDouble(),
        vegetablesHEIFAscoreMale = fields[8].toDouble(),
        vegetablesHEIFAscoreFemale = fields[9].toDouble(),
        vegetablesWithLegumesAllocatedServeSize = fields[10].toDouble(),
        legumesAllocatedVegetables = fields[11].toDouble(),
        vegetablesVariationsScore = fields[12].toDouble(),
        vegetablesCruciferous = fields[13].toDouble(),
        vegetablesTuberAndBulb = fields[14].toDouble(),
        vegetablesOther = fields[15].toDouble(),
        legumes = fields[16].toDouble(),
        vegetablesGreen = fields[17].toDouble(),
        vegetablesRedAndOrange = fields[18].toDouble(),
        fruitHEIFAscoreMale = fields[19].toDouble(),
        fruitHEIFAscoreFemale = fields[20].toDouble(),
        fruitServeSize = fields[21].toDouble(),
        fruitVariationsScore = fields[22].toDouble(),
        fruitPome = fields[23].toDouble(),
        fruitTropicalAndSubtropical = fields[24].toDouble(),
        fruitBerry = fields[25].toDouble(),
        fruitStone = fields[26].toDouble(),
        fruitCitrus = fields[27].toDouble(),
        fruitOther = fields[28].toDouble(),
        grainsAndCerealsHEIFAscoreMale = fields[29].toDouble(),
        grainsAndCerealsHEIFAscoreFemale = fields[30].toDouble(),
        grainsAndCerealsServeSize = fields[31].toDouble(),
        grainsAndCerealsNonWholeGrains = fields[32].toDouble(),
        wholeGrainsHEIFAscoreMale = fields[33].toDouble(),
        wholeGrainsHEIFAscoreFemale = fields[34].toDouble(),
        wholeGrainsServeSize = fields[35].toDouble(),
        meatAndAlternativesHEIFAscoreMale = fields[36].toDouble(),
        meatAndAlternativesHEIFAscoreFemale = fields[37].toDouble(),
        meatAndAlternativesWithLegumesAllocatedServeSize = fields[38].toDouble(),
        legumesAllocatedMeatAndAlternatives = fields[39].toDouble(),
        dairyAndAlternativesHEIFAscoreMale = fields[40].toDouble(),
        dairyAndAlternativesHEIFAscoreFemale = fields[41].toDouble(),
        dairyAndAlternativesServeSize = fields[42].toDouble(),
        sodiumHEIFAscoreMale = fields[43].toDouble(),
        sodiumHEIFAscoreFemale = fields[44].toDouble(),
        sodiumMgMilligrams = fields[45].toDouble(),
        alcoholHEIFAscoreMale = fields[46].toDouble(),
        alcoholHEIFAscoreFemale = fields[47].toDouble(),
        alcoholStandardDrinks = fields[48].toDouble(),
        waterHEIFAscoreMale = fields[49].toDouble(),
        waterHEIFAscoreFemale = fields[50].toDouble(),
        water = fields[51].toDouble(),
        waterTotalmL = fields[52].toDouble(),
        beverageTotalmL = fields[53].toDouble(),
        sugarHEIFAscoreMale = fields[54].toDouble(),
        sugarHEIFAscoreFemale = fields[55].toDouble(),
        sugar = fields[56].toDouble(),
        saturatedFatHEIFAscoreMale = fields[57].toDouble(),
        saturatedFatHEIFAscoreFemale = fields[58].toDouble(),
        saturatedFat = fields[59].toDouble(),
        unsaturatedFatHEIFAscoreMale = fields[60].toDouble(),
        unsaturatedFatHEIFAscoreFemale = fields[61].toDouble(),
        unsaturatedFatServeSize = fields[62].toDouble(),

    )
}


fun getScore(user: User): List<Double> {
    // DONE BY HAND :(
    // but the comment is done by CHATGPT to make sure its right
    return when (user.sex) {
        "Male" -> listOf(
            user.HEIFAtotalscoreMale,                 // total
            user.discretionaryHEIFAscoreMale,         // discretionary (10)
            user.vegetablesHEIFAscoreMale,            // vegetables (5)
            user.vegetablesVariationsScore,           // vegetables variation (5)
            user.fruitHEIFAscoreMale,                 // fruit (5)
            user.fruitVariationsScore,                // fruit variation (5)
            user.grainsAndCerealsHEIFAscoreMale,      // grains and cereals (5)
            user.wholeGrainsHEIFAscoreMale,           // whole grains (5)
            user.meatAndAlternativesHEIFAscoreMale,   // meat (10)
            user.dairyAndAlternativesHEIFAscoreMale,  // dairy (10)
            user.waterHEIFAscoreMale,                 // water (5)
            user.unsaturatedFatHEIFAscoreMale,        // unsaturated fat (5)
            user.saturatedFatHEIFAscoreMale,          // saturated fat (5)
            user.sodiumHEIFAscoreMale,                // sodium (10)
            user.sugarHEIFAscoreMale,                 // sugar (10)
            user.alcoholHEIFAscoreMale                // alcohol (5)
        )
        "Female" -> listOf(
            user.HEIFAtotalscoreFemale,               // total
            user.discretionaryHEIFAscoreFemale,       // discretionary (10)
            user.vegetablesHEIFAscoreFemale,          // vegetables (5)
            user.vegetablesVariationsScore,           // vegetables variation (5)
            user.fruitHEIFAscoreFemale,               // fruit (5)
            user.fruitVariationsScore,                // fruit variation (5)
            user.grainsAndCerealsHEIFAscoreFemale,    // grains and cereals (5)
            user.wholeGrainsHEIFAscoreFemale,         // whole grains (5)
            user.meatAndAlternativesHEIFAscoreFemale, // meat (10)
            user.dairyAndAlternativesHEIFAscoreFemale,// dairy (10)
            user.waterHEIFAscoreFemale,               // water (5)
            user.unsaturatedFatHEIFAscoreFemale,      // unsaturated fat (5)
            user.saturatedFatHEIFAscoreFemale,        // saturated fat (5)
            user.sodiumHEIFAscoreFemale,              // sodium (10)
            user.sugarHEIFAscoreFemale,               // sugar (10)
            user.alcoholHEIFAscoreFemale              // alcohol (5)
        )
        else -> emptyList()
    }
}

fun calculateScore(user: User): Double {
    return when (user.sex.lowercase()) {
        "male" -> calculateMaleScore(user)
        "female" -> calculateFemaleScore(user)
        else -> 0.0
    }
}

fun calculateMaleScore(user: User): Double {
    //DONE BY HAND :(
    return (user.discretionaryHEIFAscoreMale +
            user.vegetablesHEIFAscoreMale +
            user.vegetablesVariationsScore +
            user.fruitHEIFAscoreMale +
            user.fruitVariationsScore +
            user.grainsAndCerealsHEIFAscoreMale +
            user.wholeGrainsHEIFAscoreMale +
            user.meatAndAlternativesHEIFAscoreMale +
            user.dairyAndAlternativesHEIFAscoreMale +
            user.sodiumHEIFAscoreMale +
            user.alcoholHEIFAscoreMale +
            user.waterHEIFAscoreMale +
            user.sugarHEIFAscoreMale +
            user.saturatedFatHEIFAscoreMale +
            user.unsaturatedFatHEIFAscoreMale
            )
}
fun calculateFemaleScore(user: User): Double {
    //DONE BY HAND :(
    return (user.discretionaryHEIFAscoreFemale +
            user.vegetablesHEIFAscoreFemale +
            user.vegetablesVariationsScore +
            user.fruitHEIFAscoreFemale +
            user.fruitVariationsScore +
            user.grainsAndCerealsHEIFAscoreFemale +
            user.wholeGrainsHEIFAscoreFemale +
            user.meatAndAlternativesHEIFAscoreFemale +
            user.dairyAndAlternativesHEIFAscoreFemale +
            user.sodiumHEIFAscoreFemale +
            user.alcoholHEIFAscoreFemale +
            user.waterHEIFAscoreFemale +
            user.sugarHEIFAscoreFemale +
            user.saturatedFatHEIFAscoreFemale +
            user.unsaturatedFatHEIFAscoreFemale
            )
}

