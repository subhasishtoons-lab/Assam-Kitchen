package com.example.data

data class Ingredient(
    val id: String,
    val name: String,
    val assameseName: String? = null,
    val baseQuantity: Double,
    val unit: String
)

data class CookingStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val timerSeconds: Int? = null,
    val timerLabel: String? = null
)

data class Recipe(
    val id: String,
    val title: String,
    val assameseTitle: String,
    val subtitle: String,
    val description: String,
    val imageUrl: String,
    val category: String, // "tenga", "khaar", "pitika", "fish", "poultry", "greens", "sweets"
    val categoryLabel: String,
    val tags: List<String>,
    val prepTimeMins: Int,
    val cookTimeMins: Int,
    val energyKcal: Int,
    val tasteProfile: String,
    val level: String,
    val rating: Double,
    val reviewCount: Int,
    val region: String,
    val culturalNote: String,
    val chefNote: String? = null,
    val baseServings: Int = 4,
    val ingredients: List<Ingredient>,
    val steps: List<CookingStep>,
    val pairingTip: String,
    val isFeatured: Boolean = false
)

data class PantryItem(
    val id: String,
    val name: String,
    val assameseName: String,
    val tag: String,
    val tagType: String,
    val description: String,
    val imageUrl: String
)

data class CuratedCollection(
    val id: String,
    val title: String,
    val category: String,
    val recipeCount: Int,
    val description: String,
    val badge: String,
    val imageUrl: String
)

data class CulinaryPillar(
    val title: String,
    val description: String,
    val iconName: String
)

data class ChefSecret(
    val number: Int,
    val title: String,
    val detail: String
)

data class ActiveTimer(
    val id: String,
    val recipeId: String,
    val recipeTitle: String,
    val label: String,
    val totalSeconds: Int,
    val remainingSeconds: Int,
    val isRunning: Boolean
)
