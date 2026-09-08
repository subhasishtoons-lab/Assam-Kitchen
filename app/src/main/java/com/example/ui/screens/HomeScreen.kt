package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.CuratedCollection
import com.example.data.Recipe
import com.example.data.RecipeRepository
import com.example.ui.theme.ForestOnPrimaryContainer
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.ForestPrimaryContainer
import com.example.ui.theme.MustardOnSecondaryContainer
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
fun HomeScreen(
    onRecipeClick: (Recipe) -> Unit,
    onExploreClick: () -> Unit,
    onChefClick: () -> Unit,
    isBookmarked: (String) -> Boolean,
    onToggleBookmark: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val featured = RecipeRepository.featuredMaasorTenga
    val trending = RecipeRepository.trendingRecipes
    val collections = RecipeRepository.curatedCollections

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(RicePaperBackground),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        // Hero Intro Header
        item {
            HeroIntroHeader(onChefClick = onChefClick)
        }

        // Bohag Bihu Notice Banner
        item {
            BihuNoticeBanner(onExploreClick = onExploreClick)
        }

        // Featured Dish of the Day
        item {
            DishOfTheDaySection(
                recipe = featured,
                isBookmarked = isBookmarked(featured.id),
                onToggleBookmark = { onToggleBookmark(featured.id) },
                onCookClick = { onRecipeClick(featured) }
            )
        }

        // Flavor Profiles Horizontal Pills
        item {
            CourseFlavorProfilesSection(onCategoryClick = { onExploreClick() })
        }

        // Curated Collections Carousel
        item {
            CuratedCollectionsSection(
                collections = collections,
                onCollectionClick = { onExploreClick() }
            )
        }

        // Kitchen Secret Callout
        item {
            KitchenSecretCallout()
        }

        // Trending Recipes Section
        item {
            Text(
                text = "Trending Ancestral Recipes",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.Serif,
                color = ForestPrimary,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

        items(trending) { recipe ->
            TrendingRecipeItem(
                recipe = recipe,
                isBookmarked = isBookmarked(recipe.id),
                onToggleBookmark = { onToggleBookmark(recipe.id) },
                onClick = { onRecipeClick(recipe) }
            )
        }

        // Footer Card: Preserving the Hearth
        item {
            PreservingHearthFooter(onExploreClick = onExploreClick)
        }
    }
}

@Composable
private fun HeroIntroHeader(onChefClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        color = SurfaceContainerLowest,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(ForestPrimaryContainer)
                    .border(2.dp, MustardSecondary, CircleShape)
                    .clickable(onClick = onChefClick)
                    .testTag("home_chef_avatar"),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = RecipeRepository.CHEF_AVATAR_URL,
                    contentDescription = "Chef Subhasish Phukan",
                    modifier = Modifier.size(54.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Curated Culinary Heritage",
                    style = MaterialTheme.typography.labelSmall,
                    color = MustardSecondary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "Chef Subhasish Phukan",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Serif,
                    color = ForestPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "“Preserving the ancient fire & river feasts of the Brahmaputra Valley.”",
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariantMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun BihuNoticeBanner(onExploreClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        color = MustardSecondary.copy(alpha = 0.12f),
        border = androidx.compose.foundation.BorderStroke(1.dp, MustardSecondary.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "🌿", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Bohag Bihu Special Feasts",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MustardSecondary
                    )
                    Text(
                        text = "101 wild xaak & heritage fish recipes ready to explore",
                        style = MaterialTheme.typography.bodySmall,
                        color = OnSurfaceCharcoal,
                        fontSize = 11.sp
                    )
                }
            }
            TextButton(
                onClick = onExploreClick,
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                Text(
                    text = "Explore",
                    color = ForestPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun DishOfTheDaySection(
    recipe: Recipe,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onCookClick: () -> Unit
) {
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
                text = "DISH OF THE DAY",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MustardSecondary,
                letterSpacing = 1.sp
            )
            Surface(
                color = ForestPrimary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = "Summer Heritage Thali",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = ForestPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .clickable(onClick = onCookClick)
                .testTag("featured_recipe_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(recipe.imageUrl)
                            .crossfade(true)
                            .build(),
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
                                        Color.Black.copy(alpha = 0.6f)
                                    ),
                                    startY = 80f
                                )
                            )
                    )

                    // Bookmark icon button top right
                    IconButton(
                        onClick = onToggleBookmark,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .size(38.dp)
                            .background(Color.White.copy(alpha = 0.85f), CircleShape)
                            .testTag("bookmark_featured_btn")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Save Recipe",
                            tint = if (isBookmarked) MustardSecondary else OnSurfaceCharcoal,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Assamese title bottom left
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(14.dp)
                    ) {
                        Text(
                            text = recipe.assameseTitle,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFFFD9B3),
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = recipe.subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                // Card Content Body
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = recipe.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily.Serif,
                        color = ForestPrimary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = recipe.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = OnSurfaceVariantMuted,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Metrics strip
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceContainerLow, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AccessTime,
                                contentDescription = null,
                                tint = ForestPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${recipe.prepTimeMins + recipe.cookTimeMins} mins",
                                style = MaterialTheme.typography.labelMedium,
                                color = OnSurfaceCharcoal
                            )
                        }

                        Text(
                            text = "Level: ${recipe.level}",
                            style = MaterialTheme.typography.labelMedium,
                            color = OnSurfaceCharcoal
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = MustardSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${recipe.rating} (${recipe.reviewCount})",
                                style = MaterialTheme.typography.labelMedium,
                                color = OnSurfaceCharcoal
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onCookClick,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("cook_step_by_step_btn"),
                            colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Cook Step-by-Step",
                                style = MaterialTheme.typography.labelLarge
                            )
                        }

                        OutlinedButton(
                            onClick = onCookClick,
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, ForestPrimary)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Checklist,
                                contentDescription = "Prep Checklist",
                                tint = ForestPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CourseFlavorProfilesSection(onCategoryClick: () -> Unit) {
    val categories = listOf(
        "All (112)",
        "Khaar (Alkaline)",
        "Tenga (Tangy)",
        "Pitika (Mashes)",
        "Bor & Fritters",
        "Duck & Pigeon",
        "Smoked & Steamed",
        "Bihu Pitha"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Course & Flavor Profiles",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            color = ForestPrimary,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (category.startsWith("All")) ForestPrimary else SurfaceContainerLowest,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (category.startsWith("All")) ForestPrimary else ForestPrimary.copy(alpha = 0.2f)
                    ),
                    modifier = Modifier.clickable { onCategoryClick() }
                ) {
                    Text(
                        text = category,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (category.startsWith("All")) Color.White else ForestPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun CuratedCollectionsSection(
    collections: List<CuratedCollection>,
    onCollectionClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Curated Collections",
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Serif,
                color = ForestPrimary
            )
            Text(
                text = "See All",
                style = MaterialTheme.typography.labelMedium,
                color = MustardSecondary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onCollectionClick() }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(collections) { collection ->
                Card(
                    modifier = Modifier
                        .width(260.dp)
                        .clickable { onCollectionClick() },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp)
                        ) {
                            AsyncImage(
                                model = collection.imageUrl,
                                contentDescription = collection.title,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Surface(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(8.dp),
                                shape = RoundedCornerShape(6.dp),
                                color = ForestPrimary.copy(alpha = 0.85f)
                            ) {
                                Text(
                                    text = collection.badge,
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = collection.category,
                                fontSize = 10.sp,
                                color = MustardSecondary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = collection.title,
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.Serif,
                                color = ForestPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = collection.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = OnSurfaceVariantMuted,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun KitchenSecretCallout() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(14.dp),
        color = SurfaceContainerLow,
        border = androidx.compose.foundation.BorderStroke(1.dp, ForestPrimary.copy(alpha = 0.15f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MustardSecondary.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = null,
                    tint = MustardSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "SUBHASISH'S KITCHEN SECRET",
                    style = MaterialTheme.typography.labelSmall,
                    color = MustardSecondary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "The First Fry: Taming Paanch Phoron",
                    style = MaterialTheme.typography.titleSmall,
                    color = ForestPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Always heat cold-pressed raw mustard oil until light wisps of smoke appear, then drop the flame. The five spices bloom within 12 seconds without turning bitter.",
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
private fun TrendingRecipeItem(
    recipe: Recipe,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable(onClick = onClick)
            .testTag("trending_item_${recipe.id}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recipe.categoryLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = MustardSecondary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = onToggleBookmark,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Save",
                            tint = if (isBookmarked) MustardSecondary else OnSurfaceVariantMuted,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily.Serif,
                    color = ForestPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariantMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${recipe.cookTimeMins + recipe.prepTimeMins}m",
                        fontSize = 11.sp,
                        color = OnSurfaceCharcoal,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "•", fontSize = 10.sp, color = OnSurfaceVariantMuted)
                    Text(
                        text = recipe.level,
                        fontSize = 11.sp,
                        color = ForestPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "•", fontSize = 10.sp, color = OnSurfaceVariantMuted)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = MustardSecondary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "${recipe.rating}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurfaceCharcoal
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PreservingHearthFooter(onExploreClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        color = ForestPrimaryContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Preserving the Hearth",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = FontFamily.Serif,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Explore 114 thoroughly documented Assamese recipes with exact water ratios, ancestral herbs, and step-by-step guidance.",
                style = MaterialTheme.typography.bodySmall,
                color = ForestOnPrimaryContainer,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontSize = 12.sp,
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(14.dp))
            Button(
                onClick = onExploreClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.testTag("footer_browse_all_btn")
            ) {
                Text(
                    text = "Browse All 112 Recipes",
                    color = ForestPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = ForestPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
