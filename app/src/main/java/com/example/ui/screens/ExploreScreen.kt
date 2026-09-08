package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.Recipe
import com.example.data.RecipeRepository
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.ForestPrimaryContainer
import com.example.ui.theme.MustardSecondary
import com.example.ui.theme.OnSurfaceCharcoal
import com.example.ui.theme.OnSurfaceVariantMuted
import com.example.ui.theme.RicePaperBackground
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.TerracottaTertiary

@Composable
fun ExploreScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    selectedCategory: String,
    onSelectCategory: (String) -> Unit,
    selectedDynamicFilter: String?,
    onToggleDynamicFilter: (String) -> Unit,
    onResetFilters: () -> Unit,
    onRecipeClick: (Recipe) -> Unit,
    isBookmarked: (String) -> Boolean,
    onToggleBookmark: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val allRecipes = remember { RecipeRepository.getAllRecipes() }

    val filteredRecipes by remember(searchQuery, selectedCategory, selectedDynamicFilter) {
        derivedStateOf {
            allRecipes.filter { recipe ->
                // Category match
                val matchCategory = when (selectedCategory) {
                    "all" -> true
                    else -> recipe.category.equals(selectedCategory, ignoreCase = true)
                }

                // Query match
                val matchQuery = if (searchQuery.isBlank()) true else {
                    recipe.title.contains(searchQuery, ignoreCase = true) ||
                    recipe.assameseTitle.contains(searchQuery, ignoreCase = true) ||
                    recipe.subtitle.contains(searchQuery, ignoreCase = true) ||
                    recipe.description.contains(searchQuery, ignoreCase = true) ||
                    recipe.tags.any { it.contains(searchQuery, ignoreCase = true) } ||
                    recipe.ingredients.any { it.name.contains(searchQuery, ignoreCase = true) }
                }

                // Dynamic filter match
                val matchDynamic = when (selectedDynamicFilter) {
                    "quick" -> (recipe.prepTimeMins + recipe.cookTimeMins) <= 25
                    "mild" -> recipe.tasteProfile.contains("Mild", ignoreCase = true)
                    "spicy" -> recipe.tasteProfile.contains("Heat", ignoreCase = true) || recipe.tasteProfile.contains("Spicy", ignoreCase = true)
                    "steamed" -> recipe.tags.any { it.contains("Steam", ignoreCase = true) || it.contains("Leaf", ignoreCase = true) }
                    "charred" -> recipe.tags.any { it.contains("Charred", ignoreCase = true) || it.contains("Mash", ignoreCase = true) }
                    else -> true
                }

                matchCategory && matchQuery && matchDynamic
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(RicePaperBackground),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Living Archive Header
        item {
            LivingArchiveHeader()
        }

        // Search Field
        item {
            SearchInputSection(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange
            )
        }

        // Count & Reset
        item {
            ResultsCountAndReset(
                count = filteredRecipes.size,
                hasFilters = searchQuery.isNotBlank() || selectedCategory != "all" || selectedDynamicFilter != null,
                onReset = onResetFilters
            )
        }

        // Primary Category Pills
        item {
            PrimaryCategoryPills(
                selectedCategory = selectedCategory,
                onSelectCategory = onSelectCategory
            )
        }

        // Cooking Dynamics Chips
        item {
            CookingDynamicsChips(
                selectedFilter = selectedDynamicFilter,
                onToggleFilter = onToggleDynamicFilter
            )
        }

        // Chef Subhasish Note
        item {
            ArchiveChefNote()
        }

        // Filtered Recipes Catalog
        items(filteredRecipes) { recipe ->
            CatalogRecipeCard(
                recipe = recipe,
                isBookmarked = isBookmarked(recipe.id),
                onToggleBookmark = { onToggleBookmark(recipe.id) },
                onCookClick = { onRecipeClick(recipe) }
            )
        }

        // Floating Chef Dinner Pick Recommendation
        item {
            ChefDinnerPickBanner(
                onCookClick = {
                    val chitol = allRecipes.find { it.id == "chitol-machor-jhol" } ?: allRecipes.first()
                    onRecipeClick(chitol)
                }
            )
        }
    }
}

@Composable
private fun LivingArchiveHeader() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        color = ForestPrimary,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "THE LIVING ARCHIVE",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFFFD9B3),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "100+ Heritage Dishes",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.Serif,
                    color = Color.White
                )
                Text(
                    text = "Curated with Chef Subhasish Phukan",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            AsyncImage(
                model = RecipeRepository.LOGO_URL,
                contentDescription = null,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
private fun SearchInputSection(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("explore_search_input"),
        placeholder = {
            Text(
                text = "Search by ingredient, herb, or Assamese name...",
                fontSize = 13.sp,
                color = OnSurfaceVariantMuted
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = ForestPrimary
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Clear Search")
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = SurfaceContainerLowest,
            unfocusedContainerColor = SurfaceContainerLowest,
            focusedBorderColor = ForestPrimary,
            unfocusedBorderColor = ForestPrimary.copy(alpha = 0.25f)
        ),
        singleLine = true
    )
}

@Composable
private fun ResultsCountAndReset(
    count: Int,
    hasFilters: Boolean,
    onReset: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$count Recipes • Brahmaputra Valley & Hills",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceCharcoal
        )

        if (hasFilters) {
            Row(
                modifier = Modifier
                    .clickable(onClick = onReset)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.RestartAlt,
                    contentDescription = "Reset",
                    tint = MustardSecondary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Reset",
                    style = MaterialTheme.typography.labelSmall,
                    color = MustardSecondary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun PrimaryCategoryPills(
    selectedCategory: String,
    onSelectCategory: (String) -> Unit
) {
    val categories = listOf(
        "all" to "All (114)",
        "khaar" to "Khaar (14)",
        "tenga" to "Tenga (Sour) (19)",
        "pitika" to "Pitika (Mash) (18)",
        "fish" to "Fish (Maas) (26)",
        "poultry" to "Poultry & Game (16)",
        "greens" to "Wild Greens (Xaak) (12)",
        "sweets" to "Pitha & Sweets (9)"
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { (key, label) ->
            val isSelected = selectedCategory == key
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = if (isSelected) ForestPrimary else SurfaceContainerLowest,
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isSelected) ForestPrimary else ForestPrimary.copy(alpha = 0.2f)
                ),
                modifier = Modifier
                    .clickable { onSelectCategory(key) }
                    .testTag("category_pill_$key")
            ) {
                Text(
                    text = label,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isSelected) Color.White else ForestPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun CookingDynamicsChips(
    selectedFilter: String?,
    onToggleFilter: (String) -> Unit
) {
    val filters = listOf(
        "quick" to "< 25 min",
        "mild" to "Mild Comfort",
        "spicy" to "Bhut Jolokia Heat",
        "steamed" to "Patot Diya (Leaf Steam)",
        "charred" to "Pura (Ember-Charred)"
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { (key, label) ->
            val isSelected = selectedFilter == key
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (isSelected) MustardSecondary else SurfaceContainerLow,
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isSelected) MustardSecondary else MustardSecondary.copy(alpha = 0.25f)
                ),
                modifier = Modifier
                    .clickable { onToggleFilter(key) }
                    .testTag("dynamic_filter_$key")
            ) {
                Text(
                    text = label,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    fontSize = 11.sp,
                    color = if (isSelected) Color.White else MustardSecondary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun ArchiveChefNote() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(12.dp),
        color = SurfaceContainerLow,
        border = androidx.compose.foundation.BorderStroke(1.dp, ForestPrimary.copy(alpha = 0.12f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "Chef Subhasish's Note",
                style = MaterialTheme.typography.labelSmall,
                color = ForestPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Every traditional recipe in this archive has been tested over open fire and modern induction. Proportions preserve authentic souring agents and raw mustard oil pungency without compromise.",
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceCharcoal,
                fontSize = 11.sp,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun CatalogRecipeCard(
    recipe: Recipe,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onCookClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onCookClick)
            .testTag("catalog_card_${recipe.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            ) {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Bookmark toggle
                IconButton(
                    onClick = onToggleBookmark,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(36.dp)
                        .background(Color.White.copy(alpha = 0.85f), CircleShape)
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Save",
                        tint = if (isBookmarked) MustardSecondary else OnSurfaceCharcoal,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Region / Category Tag Top Left
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp),
                    shape = RoundedCornerShape(6.dp),
                    color = ForestPrimary.copy(alpha = 0.85f)
                ) {
                    Text(
                        text = recipe.categoryLabel,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = recipe.assameseTitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = MustardSecondary,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Serif,
                    color = ForestPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariantMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Tags flow
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    recipe.tags.forEach { tag ->
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = SurfaceContainerLow
                        ) {
                            Text(
                                text = tag,
                                fontSize = 10.sp,
                                color = OnSurfaceCharcoal,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = ForestPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${recipe.cookTimeMins + recipe.prepTimeMins}m",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurfaceCharcoal
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "•", fontSize = 10.sp, color = OnSurfaceVariantMuted)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = recipe.level,
                            fontSize = 12.sp,
                            color = ForestPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Button(
                        onClick = onCookClick,
                        colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text("Cook Recipe", fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChefDinnerPickBanner(onCookClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(14.dp),
        color = MustardSecondary.copy(alpha = 0.15f),
        border = androidx.compose.foundation.BorderStroke(1.dp, MustardSecondary.copy(alpha = 0.35f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "CHEF'S DINNER PICK",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MustardSecondary
                )
                Text(
                    text = "Chitol Machor Jhol (Knifefish Broth)",
                    style = MaterialTheme.typography.titleSmall,
                    color = ForestPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "40 mins • Upper Assam Heritage",
                    fontSize = 11.sp,
                    color = OnSurfaceVariantMuted
                )
            }
            Button(
                onClick = onCookClick,
                colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cook Now", fontSize = 12.sp)
            }
        }
    }
}
