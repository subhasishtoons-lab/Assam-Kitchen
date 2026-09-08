package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.data.RecipeRepository
import com.example.ui.AssamViewModel
import com.example.ui.components.AppBottomNav
import com.example.ui.components.AppHeader
import com.example.ui.components.ChefProfileDialog
import com.example.ui.components.FeedbackDialog
import com.example.ui.components.SuggestRecipeDialog
import com.example.ui.components.TimerSheetDialog
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.RecipeDetailScreen
import com.example.ui.screens.StoryPantryScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

  private val viewModel: AssamViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        AssamKitchenApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun AssamKitchenApp(viewModel: AssamViewModel) {
  val uiState by viewModel.uiState.collectAsState()
  val snackbarHostState = remember { SnackbarHostState() }

  // Handle incoming snackbar messages
  LaunchedEffect(uiState.snackbarMessage) {
    uiState.snackbarMessage?.let { msg ->
      snackbarHostState.showSnackbar(msg)
      viewModel.clearSnackbar()
    }
  }

  // Back handling when inside a recipe detail
  BackHandler(enabled = uiState.selectedRecipe != null) {
    viewModel.closeRecipeDetail()
  }

  val isDetailOpen = uiState.selectedRecipe != null

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      if (!isDetailOpen) {
        val subtitle = when (uiState.currentTab) {
          "explore" -> "The Living Archive • 100+ Heritage Dishes"
          "story" -> "Heritage Chronicler & Pantry"
          else -> "Authentic Brahmaputra Valley Cuisine"
        }
        AppHeader(
          title = "Subhasish's Kitchen",
          subtitle = subtitle,
          onChefClick = { viewModel.setShowChefProfileDialog(true) }
        )
      }
    },
    bottomBar = {
      if (!isDetailOpen) {
        AppBottomNav(
          currentTab = uiState.currentTab,
          onTabSelected = { tab -> viewModel.selectTab(tab) }
        )
      }
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(
          top = if (!isDetailOpen) innerPadding.calculateTopPadding() else androidx.compose.ui.unit.Dp(0f),
          bottom = if (!isDetailOpen) innerPadding.calculateBottomPadding() else androidx.compose.ui.unit.Dp(0f)
        )
    ) {
      if (uiState.selectedRecipe != null) {
        val recipe = uiState.selectedRecipe!!
        RecipeDetailScreen(
          recipe = recipe,
          isBookmarked = viewModel.isBookmarked(recipe.id),
          onToggleBookmark = { viewModel.toggleBookmark(recipe.id) },
          servings = viewModel.getServings(recipe.id, recipe.baseServings),
          onIncrementServings = { viewModel.incrementServings(recipe.id) },
          onDecrementServings = { viewModel.decrementServings(recipe.id) },
          isIngredientChecked = { id -> viewModel.isIngredientChecked(recipe.id, id) },
          onToggleIngredientChecked = { id -> viewModel.toggleIngredientChecked(recipe.id, id) },
          activeTimer = uiState.activeTimer,
          onStartTimer = { label, seconds ->
            viewModel.startTimer(recipe.id, recipe.title, label, seconds)
          },
          onOpenTimerSheet = { viewModel.setShowTimerSheet(true) },
          isGuidedCookingActive = uiState.isGuidedCookingActive,
          currentGuidedStep = uiState.currentGuidedStep,
          onToggleGuidedCooking = { viewModel.toggleGuidedCooking() },
          onSetGuidedStep = { step -> viewModel.setGuidedStep(step) },
          onBackClick = { viewModel.closeRecipeDetail() },
          onChefClick = { viewModel.setShowChefProfileDialog(true) }
        )
      } else {
        when (uiState.currentTab) {
          "home" -> {
            HomeScreen(
              onRecipeClick = { recipe -> viewModel.openRecipeDetail(recipe) },
              onExploreClick = { viewModel.selectTab("explore") },
              onChefClick = { viewModel.setShowChefProfileDialog(true) },
              isBookmarked = { id -> viewModel.isBookmarked(id) },
              onToggleBookmark = { id -> viewModel.toggleBookmark(id) }
            )
          }
          "explore" -> {
            ExploreScreen(
              searchQuery = uiState.searchQuery,
              onSearchQueryChange = { q -> viewModel.updateSearchQuery(q) },
              selectedCategory = uiState.selectedCategory,
              onSelectCategory = { cat -> viewModel.selectCategory(cat) },
              selectedDynamicFilter = uiState.selectedDynamicFilter,
              onToggleDynamicFilter = { filter -> viewModel.toggleDynamicFilter(filter) },
              onResetFilters = { viewModel.resetExploreFilters() },
              onRecipeClick = { recipe -> viewModel.openRecipeDetail(recipe) },
              isBookmarked = { id -> viewModel.isBookmarked(id) },
              onToggleBookmark = { id -> viewModel.toggleBookmark(id) }
            )
          }
          "cook" -> {
            // Cook tab defaults to the signature Maasor Tenga or current selection
            val defaultCookRecipe = uiState.selectedRecipe ?: RecipeRepository.featuredMaasorTenga
            RecipeDetailScreen(
              recipe = defaultCookRecipe,
              isBookmarked = viewModel.isBookmarked(defaultCookRecipe.id),
              onToggleBookmark = { viewModel.toggleBookmark(defaultCookRecipe.id) },
              servings = viewModel.getServings(defaultCookRecipe.id, defaultCookRecipe.baseServings),
              onIncrementServings = { viewModel.incrementServings(defaultCookRecipe.id) },
              onDecrementServings = { viewModel.decrementServings(defaultCookRecipe.id) },
              isIngredientChecked = { id -> viewModel.isIngredientChecked(defaultCookRecipe.id, id) },
              onToggleIngredientChecked = { id -> viewModel.toggleIngredientChecked(defaultCookRecipe.id, id) },
              activeTimer = uiState.activeTimer,
              onStartTimer = { label, seconds ->
                viewModel.startTimer(defaultCookRecipe.id, defaultCookRecipe.title, label, seconds)
              },
              onOpenTimerSheet = { viewModel.setShowTimerSheet(true) },
              isGuidedCookingActive = uiState.isGuidedCookingActive,
              currentGuidedStep = uiState.currentGuidedStep,
              onToggleGuidedCooking = { viewModel.toggleGuidedCooking() },
              onSetGuidedStep = { step -> viewModel.setGuidedStep(step) },
              onBackClick = { viewModel.selectTab("home") },
              onChefClick = { viewModel.setShowChefProfileDialog(true) }
            )
          }
          "story" -> {
            StoryPantryScreen(
              onSuggestRecipeClick = { viewModel.setShowSuggestRecipeDialog(true) },
              onFeedbackClick = { viewModel.setShowFeedbackDialog(true) },
              onDownloadCookbookClick = {
                viewModel.showToast("Cookbook PDF downloaded: 100 Ancestral Assamese Recipes (Offline Access)")
              }
            )
          }
        }
      }
    }
  }

  // Dialogs
  if (uiState.showChefProfileDialog) {
    ChefProfileDialog(onDismiss = { viewModel.setShowChefProfileDialog(false) })
  }

  if (uiState.showSuggestRecipeDialog) {
    SuggestRecipeDialog(
      onDismiss = { viewModel.setShowSuggestRecipeDialog(false) },
      onSubmit = { name, story ->
        viewModel.setShowSuggestRecipeDialog(false)
        viewModel.showToast("Thank you! \"$name\" submitted to Chef Subhasish's review archive.")
      }
    )
  }

  if (uiState.showFeedbackDialog) {
    FeedbackDialog(
      onDismiss = { viewModel.setShowFeedbackDialog(false) },
      onSubmit = { note ->
        viewModel.setShowFeedbackDialog(false)
        viewModel.showToast("Your note was shared with Chef Subhasish Phukan.")
      }
    )
  }

  if (uiState.showTimerSheet) {
    TimerSheetDialog(
      activeTimer = uiState.activeTimer,
      onPauseResume = { viewModel.pauseResumeTimer() },
      onCancel = { viewModel.cancelTimer() },
      onDismiss = { viewModel.setShowTimerSheet(false) }
    )
  }
}

