package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.ActiveTimer
import com.example.data.CookingStep
import com.example.data.Ingredient
import com.example.data.Recipe
import com.example.data.RecipeRepository
import com.example.ui.theme.ForestOnPrimaryContainer
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.ForestPrimaryContainer
import com.example.ui.theme.MustardSecondary
import com.example.ui.theme.MustardSecondaryContainer
import com.example.ui.theme.OnSurfaceCharcoal
import com.example.ui.theme.OnSurfaceVariantMuted
import com.example.ui.theme.RicePaperBackground
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.TerracottaTertiary

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    servings: Int,
    onIncrementServings: () -> Unit,
    onDecrementServings: () -> Unit,
    isIngredientChecked: (String) -> Boolean,
    onToggleIngredientChecked: (String) -> Unit,
    activeTimer: ActiveTimer?,
    onStartTimer: (String, Int) -> Unit,
    onOpenTimerSheet: () -> Unit,
    isGuidedCookingActive: Boolean,
    currentGuidedStep: Int,
    onToggleGuidedCooking: () -> Unit,
    onSetGuidedStep: (Int) -> Unit,
    onBackClick: () -> Unit,
    onChefClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize().background(RicePaperBackground)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            // Header Bar
            item {
                DetailTopBar(
                    region = recipe.region,
                    isBookmarked = isBookmarked,
                    onToggleBookmark = onToggleBookmark,
                    onBackClick = onBackClick,
                    onChefClick = onChefClick,
                    onShareClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, recipe.title)
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "${recipe.title}\n${recipe.assameseTitle}\n\n${recipe.description}\n\nCurated by Chef Subhasish Phukan on Assam Kitchen."
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share Recipe"))
                    }
                )
            }

            // Hero Visual Card
            item {
                DetailHeroSection(recipe = recipe)
            }

            // Curated Byline
            item {
                ChefByline(onChefClick = onChefClick)
            }

            // Key Metrics Bento Strip
            item {
                KeyMetricsStrip(recipe = recipe)
            }

            // Cultural Note Callout
            item {
                CulturalNoteSection(culturalNote = recipe.culturalNote)
            }

            // Bazaar Checklist (Ingredients)
            item {
                IngredientsChecklistSection(
                    recipe = recipe,
                    servings = servings,
                    onIncrement = onIncrementServings,
                    onDecrement = onDecrementServings,
                    isIngredientChecked = isIngredientChecked,
                    onToggleChecked = onToggleIngredientChecked
                )
            }

            // Method of Ancestors (Steps)
            item {
                CookingStepsSection(
                    steps = recipe.steps,
                    recipeTitle = recipe.title,
                    isGuidedCooking = isGuidedCookingActive,
                    currentStep = currentGuidedStep,
                    onSetStep = onSetGuidedStep,
                    activeTimer = activeTimer,
                    onStartTimer = onStartTimer
                )
            }

            // Heritage Pairing Tip
            item {
                PairingTipCard(tip = recipe.pairingTip)
            }
        }

        // Sticky Bottom Controls (Kitchen Timer & Guided Mode)
        StickyDetailBottomBar(
            activeTimer = activeTimer,
            onOpenTimerSheet = onOpenTimerSheet,
            isGuidedCookingActive = isGuidedCookingActive,
            onToggleGuidedCooking = onToggleGuidedCooking,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun DetailTopBar(
    region: String,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onBackClick: () -> Unit,
    onChefClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = RicePaperBackground.copy(alpha = 0.96f),
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("detail_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = ForestPrimary
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = ForestPrimary.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = region,
                        color = ForestPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onShareClick,
                    modifier = Modifier.testTag("detail_share_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = OnSurfaceCharcoal
                    )
                }

                IconButton(
                    onClick = onToggleBookmark,
                    modifier = Modifier.testTag("detail_bookmark_btn")
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Save",
                        tint = if (isBookmarked) MustardSecondary else OnSurfaceCharcoal
                    )
                }

                IconButton(
                    onClick = onChefClick,
                    modifier = Modifier.size(36.dp)
                ) {
                    AsyncImage(
                        model = RecipeRepository.CHEF_AVATAR_URL,
                        contentDescription = "Chef Subhasish Phukan",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(1.dp, ForestPrimary, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailHeroSection(recipe: Recipe) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(260.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(4.dp, RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = recipe.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.75f)
                        ),
                        startY = 100f
                    )
                )
        )

        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp),
            shape = RoundedCornerShape(6.dp),
            color = ForestPrimary.copy(alpha = 0.9f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Verified Authentic Brahmaputra Cuisine",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = recipe.assameseTitle,
                style = MaterialTheme.typography.labelMedium,
                color = Color(0xFFFFD9B3),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = recipe.subtitle,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )
        }
    }
}

@Composable
private fun ChefByline(onChefClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable(onClick = onChefClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = RecipeRepository.CHEF_AVATAR_URL,
                contentDescription = null,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MustardSecondary, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Subhasish Phukan",
                    style = MaterialTheme.typography.titleSmall,
                    color = ForestPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Archivist & Culinary Lead",
                    style = MaterialTheme.typography.labelSmall,
                    color = OnSurfaceVariantMuted
                )
            }
        }

        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MustardSecondary.copy(alpha = 0.12f),
            border = androidx.compose.foundation.BorderStroke(1.dp, MustardSecondary.copy(alpha = 0.3f))
        ) {
            Text(
                text = "Heirloom Craft",
                color = MustardSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun KeyMetricsStrip(recipe: Recipe) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .background(SurfaceContainerLowest, RoundedCornerShape(12.dp))
            .border(1.dp, ForestPrimary.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MetricItem(label = "PREP", value = "${recipe.prepTimeMins}m")
        MetricDivider()
        MetricItem(label = "COOK", value = "${recipe.cookTimeMins}m")
        MetricDivider()
        MetricItem(label = "ENERGY", value = "${recipe.energyKcal} kcal")
        MetricDivider()
        MetricItem(label = "TASTE", value = recipe.tasteProfile)
    }
}

@Composable
private fun MetricItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = MustardSecondary,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = ForestPrimary
        )
    }
}

@Composable
private fun MetricDivider() {
    Box(
        modifier = Modifier
            .height(26.dp)
            .width(1.dp)
            .background(ForestPrimary.copy(alpha = 0.15f))
    )
}

@Composable
private fun CulturalNoteSection(culturalNote: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        color = SurfaceContainerLow
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Terracotta Left Accent Line
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(64.dp)
                    .background(TerracottaTertiary, RoundedCornerShape(2.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = culturalNote,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                color = OnSurfaceCharcoal,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
private fun IngredientsChecklistSection(
    recipe: Recipe,
    servings: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    isIngredientChecked: (String) -> Boolean,
    onToggleChecked: (String) -> Unit
) {
    val scaleFactor = servings.toDouble() / recipe.baseServings.toDouble()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bazaar Checklist",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Serif,
                color = ForestPrimary
            )

            // Scaler controls
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = SurfaceContainerLowest,
                border = androidx.compose.foundation.BorderStroke(1.dp, ForestPrimary.copy(alpha = 0.2f))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    IconButton(
                        onClick = onDecrement,
                        modifier = Modifier.size(28.dp),
                        enabled = servings > 2
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Decrease Servings",
                            tint = if (servings > 2) ForestPrimary else OnSurfaceVariantMuted,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Text(
                        text = "$servings guests",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = ForestPrimary,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )

                    IconButton(
                        onClick = onIncrement,
                        modifier = Modifier.size(28.dp),
                        enabled = servings < 12
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase Servings",
                            tint = if (servings < 12) ForestPrimary else OnSurfaceVariantMuted,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                recipe.ingredients.forEach { item ->
                    val checked = isIngredientChecked(item.id)
                    val scaledQty = item.baseQuantity * scaleFactor
                    val formattedQty = if (scaledQty % 1.0 == 0.0) {
                        scaledQty.toInt().toString()
                    } else {
                        String.format("%.1f", scaledQty)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onToggleChecked(item.id) }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { onToggleChecked(item.id) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = ForestPrimary,
                                uncheckedColor = ForestPrimary.copy(alpha = 0.4f)
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (checked) OnSurfaceVariantMuted else OnSurfaceCharcoal,
                                textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None
                            )
                            if (item.assameseName != null) {
                                Text(
                                    text = item.assameseName,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MustardSecondary
                                )
                            }
                        }
                        Text(
                            text = "$formattedQty ${item.unit}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (checked) OnSurfaceVariantMuted else ForestPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CookingStepsSection(
    steps: List<CookingStep>,
    recipeTitle: String,
    isGuidedCooking: Boolean,
    currentStep: Int,
    onSetStep: (Int) -> Unit,
    activeTimer: ActiveTimer?,
    onStartTimer: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Method of Ancestors",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Serif,
                color = ForestPrimary
            )
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = ForestPrimary.copy(alpha = 0.1f)
            ) {
                Text(
                    text = "${steps.size} Ritual Steps",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                    fontSize = 11.sp,
                    color = ForestPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        steps.forEach { step ->
            val isCurrentActive = isGuidedCooking && currentStep == step.stepNumber

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onSetStep(step.stepNumber) }
                    .then(
                        if (isCurrentActive) {
                            Modifier.border(2.dp, MustardSecondary, RoundedCornerShape(14.dp))
                        } else Modifier
                    ),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isCurrentActive) MustardSecondary.copy(alpha = 0.08f) else SurfaceContainerLowest
                ),
                elevation = CardDefaults.cardElevation(if (isCurrentActive) 3.dp else 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(if (isCurrentActive) MustardSecondary else ForestPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${step.stepNumber}",
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = step.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.Serif,
                                color = ForestPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (step.timerSeconds != null) {
                            OutlinedButton(
                                onClick = {
                                    onStartTimer(step.timerLabel ?: step.title, step.timerSeconds)
                                },
                                shape = RoundedCornerShape(20.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, MustardSecondary),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Timer,
                                    contentDescription = null,
                                    tint = MustardSecondary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${step.timerSeconds / 60}m Timer",
                                    fontSize = 11.sp,
                                    color = MustardSecondary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = step.instruction,
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurfaceCharcoal,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun PairingTipCard(tip: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        color = SurfaceContainerLow,
        border = androidx.compose.foundation.BorderStroke(1.dp, MustardSecondary.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "🍚", fontSize = 22.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Heritage Pairing Tip",
                    style = MaterialTheme.typography.labelSmall,
                    color = MustardSecondary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = tip,
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceCharcoal,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )
            }
        }
    }
}

@Composable
private fun StickyDetailBottomBar(
    activeTimer: ActiveTimer?,
    onOpenTimerSheet: () -> Unit,
    isGuidedCookingActive: Boolean,
    onToggleGuidedCooking: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = RicePaperBackground.copy(alpha = 0.96f),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Kitchen Timer button
            OutlinedButton(
                onClick = onOpenTimerSheet,
                modifier = Modifier
                    .weight(0.9f)
                    .height(48.dp)
                    .testTag("open_timer_sheet_btn"),
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.5.dp,
                    if (activeTimer?.isRunning == true) MustardSecondary else ForestPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = null,
                    tint = if (activeTimer?.isRunning == true) MustardSecondary else ForestPrimary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                if (activeTimer != null) {
                    val mins = activeTimer.remainingSeconds / 60
                    val secs = activeTimer.remainingSeconds % 60
                    Text(
                        text = String.format("%02d:%02d", mins, secs),
                        color = MustardSecondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                } else {
                    Text(
                        text = "Kitchen Timer",
                        color = ForestPrimary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                }
            }

            // Start Guided Cook Mode button
            Button(
                onClick = onToggleGuidedCooking,
                modifier = Modifier
                    .weight(1.3f)
                    .height(48.dp)
                    .testTag("guided_cooking_toggle_btn"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isGuidedCookingActive) MustardSecondary else ForestPrimary
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocalFireDepartment,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isGuidedCookingActive) "Exit Guided Mode" else "Guided Cook Mode",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}
