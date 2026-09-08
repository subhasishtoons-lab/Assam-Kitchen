package com.example.ui.components

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.data.ActiveTimer
import com.example.data.RecipeRepository
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.ForestPrimaryFixed
import com.example.ui.theme.MustardSecondary
import com.example.ui.theme.MustardSecondaryFixed
import com.example.ui.theme.OnSurfaceCharcoal
import com.example.ui.theme.OnSurfaceVariantMuted
import com.example.ui.theme.RicePaperSurface
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.TerracottaTertiary

@Composable
fun AppHeader(
    title: String = "Subhasish's Kitchen",
    subtitle: String = "Home",
    onChefClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = RicePaperSurface.copy(alpha = 0.95f),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(RecipeRepository.LOGO_URL)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Assam Kitchen Logo",
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = ForestPrimary,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = OnSurfaceVariantMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Chef Profile Button
            IconButton(
                onClick = onChefClick,
                modifier = Modifier
                    .size(44.dp)
                    .testTag("chef_profile_avatar")
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .shadow(2.dp, CircleShape)
                        .clip(CircleShape)
                        .border(1.5.dp, ForestPrimary.copy(alpha = 0.2f), CircleShape)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(RecipeRepository.CHEF_AVATAR_URL)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Chef Subhasish Phukan Profile",
                        modifier = Modifier.size(36.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun AppBottomNav(
    currentTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = RicePaperSurface.copy(alpha = 0.96f),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                label = "Home",
                icon = Icons.Default.Restaurant,
                selected = currentTab == "home",
                onClick = { onTabSelected("home") },
                testTag = "nav_home"
            )
            NavItem(
                label = "Explore 100+",
                icon = Icons.Default.Explore,
                selected = currentTab == "explore",
                onClick = { onTabSelected("explore") },
                testTag = "nav_explore"
            )
            NavItem(
                label = "Cook",
                icon = Icons.Default.LocalFireDepartment,
                selected = currentTab == "cook",
                onClick = { onTabSelected("cook") },
                testTag = "nav_cook"
            )
            NavItem(
                label = "Story & Pantry",
                icon = Icons.Default.MenuBook,
                selected = currentTab == "story",
                onClick = { onTabSelected("story") },
                testTag = "nav_story"
            )
        }
    }
}

@Composable
private fun NavItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    testTag: String
) {
    val tint = if (selected) ForestPrimary else OnSurfaceVariantMuted
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = tint,
            fontWeight = fontWeight,
            maxLines = 1
        )
    }
}

@Composable
fun ChefProfileDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = null,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .shadow(4.dp, CircleShape)
                        .clip(CircleShape)
                ) {
                    AsyncImage(
                        model = RecipeRepository.CHEF_AVATAR_URL,
                        contentDescription = "Chef Subhasish Phukan",
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Chef Subhasish Phukan",
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily.Serif,
                        color = ForestPrimary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified",
                        tint = ForestPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Text(
                    text = "Heritage Custodian & Culinary Chronicler",
                    style = MaterialTheme.typography.labelMedium,
                    color = MustardSecondary,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SurfaceContainerLow,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "“Assamese food is an intimate relationship with nature—the gentle sweetness of river currents, the healing chemistry of banana ash, and the aromatic vigor of wild foraged botanicals. We cook to nourish, not overpower.”",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnSurfaceCharcoal,
                        modifier = Modifier.padding(14.dp),
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("114+", fontWeight = FontWeight.Bold, color = ForestPrimary, fontSize = 16.sp)
                        Text("Recipes", color = OnSurfaceVariantMuted, fontSize = 11.sp)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("4.9★", fontWeight = FontWeight.Bold, color = MustardSecondary, fontSize = 16.sp)
                        Text("Rating", color = OnSurfaceVariantMuted, fontSize = 11.sp)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("24.8k", fontWeight = FontWeight.Bold, color = TerracottaTertiary, fontSize = 16.sp)
                        Text("Home Cooks", color = OnSurfaceVariantMuted, fontSize = 11.sp)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Close Profile")
            }
        },
        containerColor = SurfaceContainerLowest,
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun SuggestRecipeDialog(
    onDismiss: () -> Unit,
    onSubmit: (String, String) -> Unit
) {
    var dishName by remember { mutableStateOf("") }
    var familyStory by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Suggest Ancestral Recipe",
                style = MaterialTheme.typography.headlineSmall,
                color = ForestPrimary
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Contribute a cherished heirloom recipe from your mother's or grandmother's kitchen in the Brahmaputra valley.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurfaceVariantMuted
                )
                OutlinedTextField(
                    value = dishName,
                    onValueChange = { dishName = it },
                    label = { Text("Dish Name (e.g., Ou Tenga Diya Maas)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
                OutlinedTextField(
                    value = familyStory,
                    onValueChange = { familyStory = it },
                    label = { Text("Family Story & Heirloom Technique") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (dishName.isNotBlank()) {
                        onSubmit(dishName, familyStory)
                    }
                },
                enabled = dishName.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Submit Heirloom")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = OnSurfaceVariantMuted)
            }
        },
        containerColor = SurfaceContainerLowest,
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun FeedbackDialog(
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    var feedback by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Note to Chef Subhasish",
                style = MaterialTheme.typography.headlineSmall,
                color = ForestPrimary
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Share how your cooking experience was or suggest an ingredient substitution.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurfaceVariantMuted
                )
                OutlinedTextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    label = { Text("Your Message...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (feedback.isNotBlank()) {
                        onSubmit(feedback)
                    }
                },
                enabled = feedback.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = ForestPrimary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Send Note")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = OnSurfaceVariantMuted)
            }
        },
        containerColor = SurfaceContainerLowest,
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun TimerSheetDialog(
    activeTimer: ActiveTimer?,
    onPauseResume: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = MustardSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Kitchen Timer",
                        style = MaterialTheme.typography.titleLarge,
                        color = ForestPrimary
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            if (activeTimer == null) {
                Text(
                    text = "No timer currently running. Set a timer from any ritual cooking step in the recipe detail!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurfaceVariantMuted
                )
            } else {
                val mins = activeTimer.remainingSeconds / 60
                val secs = activeTimer.remainingSeconds % 60
                val timeString = String.format("%02d:%02d", mins, secs)

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = activeTimer.label,
                        style = MaterialTheme.typography.titleMedium,
                        color = OnSurfaceCharcoal,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = activeTimer.recipeTitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = OnSurfaceVariantMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = timeString,
                        style = MaterialTheme.typography.displayLarge,
                        fontSize = 48.sp,
                        color = if (activeTimer.remainingSeconds <= 30) TerracottaTertiary else MustardSecondary,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = onPauseResume,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (activeTimer.isRunning) MustardSecondary else ForestPrimary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = if (activeTimer.isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (activeTimer.isRunning) "Pause" else "Resume")
                        }

                        OutlinedButton(
                            onClick = onCancel,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Stop,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Cancel")
                        }
                    }
                }
            }
        },
        confirmButton = {},
        containerColor = SurfaceContainerLowest,
        shape = RoundedCornerShape(16.dp)
    )
}
