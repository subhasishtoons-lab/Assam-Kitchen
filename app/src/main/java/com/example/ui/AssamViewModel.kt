package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ActiveTimer
import com.example.data.Recipe
import com.example.data.RecipeRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val currentTab: String = "home", // "home", "explore", "cook", "story"
    val selectedRecipe: Recipe? = null,
    val bookmarkedRecipeIds: Set<String> = setOf("maasor-tenga"),
    val servingsMap: Map<String, Int> = emptyMap(),
    val checkedIngredients: Set<String> = emptySet(), // "recipeId_ingredientId"
    val searchQuery: String = "",
    val selectedCategory: String = "all", // "all", "khaar", "tenga", "pitika", "fish", "poultry", "greens", "sweets"
    val selectedDynamicFilter: String? = null, // "quick", "mild", "spicy", "leaf", "roasted"
    val activeTimer: ActiveTimer? = null,
    val isGuidedCookingActive: Boolean = false,
    val currentGuidedStep: Int = 1,
    val showChefProfileDialog: Boolean = false,
    val showSuggestRecipeDialog: Boolean = false,
    val showFeedbackDialog: Boolean = false,
    val showTimerSheet: Boolean = false,
    val snackbarMessage: String? = null
)

class AssamViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    fun selectTab(tab: String) {
        _uiState.update {
            if (tab == "cook") {
                // If switching to cook tab, open featured recipe or current selected recipe
                val recipe = it.selectedRecipe ?: RecipeRepository.featuredMaasorTenga
                it.copy(currentTab = "cook", selectedRecipe = recipe)
            } else {
                it.copy(currentTab = tab, selectedRecipe = null)
            }
        }
    }

    fun openRecipeDetail(recipe: Recipe) {
        _uiState.update {
            it.copy(selectedRecipe = recipe)
        }
    }

    fun closeRecipeDetail() {
        _uiState.update {
            it.copy(selectedRecipe = null, isGuidedCookingActive = false)
        }
    }

    fun toggleBookmark(recipeId: String) {
        _uiState.update { current ->
            val isCurrentlyBookmarked = current.bookmarkedRecipeIds.contains(recipeId)
            val updatedBookmarks = if (isCurrentlyBookmarked) {
                current.bookmarkedRecipeIds - recipeId
            } else {
                current.bookmarkedRecipeIds + recipeId
            }
            val msg = if (isCurrentlyBookmarked) "Removed from Saved Recipes" else "Added to Saved Heritage Recipes"
            current.copy(
                bookmarkedRecipeIds = updatedBookmarks,
                snackbarMessage = msg
            )
        }
    }

    fun isBookmarked(recipeId: String): Boolean {
        return _uiState.value.bookmarkedRecipeIds.contains(recipeId)
    }

    fun getServings(recipeId: String, defaultServings: Int = 4): Int {
        return _uiState.value.servingsMap[recipeId] ?: defaultServings
    }

    fun incrementServings(recipeId: String) {
        _uiState.update { current ->
            val existing = current.servingsMap[recipeId] ?: 4
            if (existing < 12) {
                val next = existing + 2
                current.copy(
                    servingsMap = current.servingsMap + (recipeId to next),
                    snackbarMessage = "Adjusted ingredients for $next guests"
                )
            } else current
        }
    }

    fun decrementServings(recipeId: String) {
        _uiState.update { current ->
            val existing = current.servingsMap[recipeId] ?: 4
            if (existing > 2) {
                val next = existing - 2
                current.copy(
                    servingsMap = current.servingsMap + (recipeId to next),
                    snackbarMessage = "Adjusted ingredients for $next guests"
                )
            } else current
        }
    }

    fun toggleIngredientChecked(recipeId: String, ingredientId: String) {
        val key = "${recipeId}_${ingredientId}"
        _uiState.update { current ->
            val isChecked = current.checkedIngredients.contains(key)
            val updated = if (isChecked) current.checkedIngredients - key else current.checkedIngredients + key
            val msg = if (!isChecked) "Ingredient measured & prepared" else null
            current.copy(
                checkedIngredients = updated,
                snackbarMessage = msg ?: current.snackbarMessage
            )
        }
    }

    fun isIngredientChecked(recipeId: String, ingredientId: String): Boolean {
        return _uiState.value.checkedIngredients.contains("${recipeId}_${ingredientId}")
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun toggleDynamicFilter(filter: String) {
        _uiState.update { current ->
            val nextFilter = if (current.selectedDynamicFilter == filter) null else filter
            current.copy(selectedDynamicFilter = nextFilter)
        }
    }

    fun resetExploreFilters() {
        _uiState.update {
            it.copy(
                searchQuery = "",
                selectedCategory = "all",
                selectedDynamicFilter = null
            )
        }
    }

    fun startTimer(recipeId: String, recipeTitle: String, label: String, seconds: Int) {
        timerJob?.cancel()
        val timer = ActiveTimer(
            id = System.currentTimeMillis().toString(),
            recipeId = recipeId,
            recipeTitle = recipeTitle,
            label = label,
            totalSeconds = seconds,
            remainingSeconds = seconds,
            isRunning = true
        )
        _uiState.update { it.copy(activeTimer = timer, snackbarMessage = "Started: $label (${seconds / 60}m)") }

        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                val current = _uiState.value.activeTimer ?: break
                if (!current.isRunning) continue

                val nextRemaining = current.remainingSeconds - 1
                if (nextRemaining <= 0) {
                    _uiState.update {
                        it.copy(
                            activeTimer = current.copy(remainingSeconds = 0, isRunning = false),
                            snackbarMessage = "⏰ ${current.label} Complete!"
                        )
                    }
                    break
                } else {
                    _uiState.update {
                        it.copy(activeTimer = current.copy(remainingSeconds = nextRemaining))
                    }
                }
            }
        }
    }

    fun pauseResumeTimer() {
        _uiState.update { current ->
            val timer = current.activeTimer ?: return@update current
            current.copy(activeTimer = timer.copy(isRunning = !timer.isRunning))
        }
    }

    fun cancelTimer() {
        timerJob?.cancel()
        _uiState.update { it.copy(activeTimer = null, showTimerSheet = false) }
    }

    fun toggleGuidedCooking() {
        _uiState.update { current ->
            val next = !current.isGuidedCookingActive
            val msg = if (next) "Kitchen Guide Active - Screen Stays Awake" else "Exited Guided Cooking"
            current.copy(isGuidedCookingActive = next, currentGuidedStep = 1, snackbarMessage = msg)
        }
    }

    fun setGuidedStep(step: Int) {
        _uiState.update { it.copy(currentGuidedStep = step) }
    }

    fun setShowChefProfileDialog(show: Boolean) {
        _uiState.update { it.copy(showChefProfileDialog = show) }
    }

    fun setShowSuggestRecipeDialog(show: Boolean) {
        _uiState.update { it.copy(showSuggestRecipeDialog = show) }
    }

    fun setShowFeedbackDialog(show: Boolean) {
        _uiState.update { it.copy(showFeedbackDialog = show) }
    }

    fun setShowTimerSheet(show: Boolean) {
        _uiState.update { it.copy(showTimerSheet = show) }
    }

    fun clearSnackbar() {
        _uiState.update { it.copy(snackbarMessage = null) }
    }

    fun showToast(message: String) {
        _uiState.update { it.copy(snackbarMessage = message) }
    }
}
