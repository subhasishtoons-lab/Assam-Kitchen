package com.example.ui.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.data.ChefSecret
import com.example.data.CulinaryPillar
import com.example.data.PantryItem
import com.example.data.RecipeRepository
import com.example.ui.theme.ForestOnPrimaryContainer
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
fun StoryPantryScreen(
    onSuggestRecipeClick: () -> Unit,
    onFeedbackClick: () -> Unit,
    onDownloadCookbookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pantryItems = RecipeRepository.pantryItems
    val pillars = RecipeRepository.culinaryPillars
    val secrets = RecipeRepository.chefSecrets

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(RicePaperBackground),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Chef Profile Hero Card
        item {
            ChefProfileHero()
        }

        // Essential Spice & Herb Pantry Title
        item {
            PantrySectionHeader()
        }

        // Pantry Items Carousel
        item {
            PantryItemsCarousel(items = pantryItems)
        }

        // Culinary Pillars & Philosophy
        item {
            CulinaryPillarsSection(pillars = pillars)
        }

        // Subhasish's Secrets & Market Tips
        item {
            ChefSecretsSection(secrets = secrets)
        }

        // Action Buttons: Offline Cookbook, Suggest Recipe, Feedback
        item {
            StoryActionButtons(
                onSuggestRecipeClick = onSuggestRecipeClick,
                onFeedbackClick = onFeedbackClick,
                onDownloadCookbookClick = onDownloadCookbookClick
            )
        }
    }
}

@Composable
private fun ChefProfileHero() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        color = SurfaceContainerLowest,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .border(2.dp, MustardSecondary, CircleShape)
                        .shadow(2.dp, CircleShape)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(RecipeRepository.CHEF_AVATAR_URL)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Chef Subhasish Phukan",
                        modifier = Modifier.size(72.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Subhasish Phukan",
                            style = MaterialTheme.typography.titleLarge,
                            fontFamily = FontFamily.Serif,
                            color = ForestPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = "Verified",
                            tint = ForestPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Text(
                        text = "Culinary Chronicler & Custodian of Assamese Heritage Cuisine",
                        style = MaterialTheme.typography.labelSmall,
                        color = MustardSecondary,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Born along the Brahmaputra banks, Subhasish has spent over a decade documenting vanishing culinary traditions, foraged riverside xaak, and ancestral open-fire smoking rituals across Upper and Lower Assam.",
                style = MaterialTheme.typography.bodySmall,
                color = OnSurfaceCharcoal,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Key Statistics Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerLow, RoundedCornerShape(12.dp))
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatColumn(number = "114", label = "Heritage Recipes")
                VerticalDivider()
                StatColumn(number = "4.9★", label = "Average Rating")
                VerticalDivider()
                StatColumn(number = "24.8k", label = "Home Cooks")
                VerticalDivider()
                StatColumn(number = "18", label = "Staples Documented")
            }
        }
    }
}

@Composable
private fun StatColumn(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = ForestPrimary
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = OnSurfaceVariantMuted
        )
    }
}

@Composable
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(24.dp)
            .width(1.dp)
            .background(ForestPrimary.copy(alpha = 0.15f))
    )
}

@Composable
private fun PantrySectionHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
    ) {
        Text(
            text = "Essential Spice & Herb Pantry",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            color = ForestPrimary
        )
        Text(
            text = "Five ancestral building blocks defining Brahmaputra Valley flavors",
            style = MaterialTheme.typography.bodySmall,
            color = OnSurfaceVariantMuted,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun PantryItemsCarousel(items: List<PantryItem>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .width(220.dp)
                    .testTag("pantry_item_${item.id}"),
                shape = RoundedCornerShape(14.dp),
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
                            model = item.imageUrl,
                            contentDescription = item.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Surface(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(8.dp),
                            shape = RoundedCornerShape(4.dp),
                            color = ForestPrimary.copy(alpha = 0.85f)
                        ) {
                            Text(
                                text = item.tag,
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleSmall,
                                fontFamily = FontFamily.Serif,
                                color = ForestPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = item.assameseName,
                                fontSize = 10.sp,
                                color = MustardSecondary
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceVariantMuted,
                            fontSize = 11.sp,
                            lineHeight = 16.sp,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CulinaryPillarsSection(pillars: List<CulinaryPillar>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Culinary Pillars & Philosophy",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            color = ForestPrimary
        )

        Spacer(modifier = Modifier.height(10.dp))

        pillars.forEach { pillar ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MustardSecondary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        val icon = when (pillar.iconName) {
                            "recycling" -> Icons.Default.Recycling
                            "water_drop" -> Icons.Default.Opacity
                            else -> Icons.Default.Eco
                        }
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MustardSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = pillar.title,
                            style = MaterialTheme.typography.titleSmall,
                            color = ForestPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = pillar.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceCharcoal,
                            fontSize = 11.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChefSecretsSection(secrets: List<ChefSecret>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Subhasish's Secrets & Market Tips",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            color = ForestPrimary
        )

        Spacer(modifier = Modifier.height(10.dp))

        secrets.forEach { secret ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                color = SurfaceContainerLow,
                border = androidx.compose.foundation.BorderStroke(1.dp, ForestPrimary.copy(alpha = 0.12f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(ForestPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "0${secret.number}",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = secret.title,
                            style = MaterialTheme.typography.titleSmall,
                            color = ForestPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = secret.detail,
                            style = MaterialTheme.typography.bodySmall,
                            color = OnSurfaceCharcoal,
                            fontSize = 11.sp,
                            lineHeight = 17.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StoryActionButtons(
    onSuggestRecipeClick: () -> Unit,
    onFeedbackClick: () -> Unit,
    onDownloadCookbookClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = onDownloadCookbookClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("download_cookbook_btn"),
            colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Download Offline Cookbook PDF (100 Recipes)", fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = onSuggestRecipeClick,
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp)
                    .testTag("suggest_recipe_btn"),
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, ForestPrimary)
            ) {
                Icon(
                    imageVector = Icons.Default.Upload,
                    contentDescription = null,
                    tint = ForestPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Suggest Recipe", color = ForestPrimary, fontSize = 12.sp)
            }

            OutlinedButton(
                onClick = onFeedbackClick,
                modifier = Modifier
                    .weight(1f)
                    .height(46.dp)
                    .testTag("share_feedback_btn"),
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, MustardSecondary)
            ) {
                Icon(
                    imageVector = Icons.Default.ChatBubbleOutline,
                    contentDescription = null,
                    tint = MustardSecondary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Share Feedback", color = MustardSecondary, fontSize = 12.sp)
            }
        }
    }
}
