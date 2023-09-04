package com.denicks21.darkmode.ui.composables

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.denicks21.darkmode.R
import com.denicks21.darkmode.ui.theme.DarkPrimary
import com.denicks21.darkmode.ui.theme.DarkText
import com.denicks21.darkmode.ui.theme.LightPrimary
import com.denicks21.darkmode.ui.theme.LightText

@Composable
fun Switcher(
    darkTheme: Boolean = false,
    size: Dp = 150.dp,
    iconSize: Dp = size / 3,
    padding: Dp = 10.dp,
    borderWidth: Dp = 1.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 200),
    onClick: () -> Unit,
) {
    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec
    )
    var showInfoDialog by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = if (darkTheme) DarkText else LightText
                    )
                },
                actions = {
                    IconButton(
                        onClick = { showInfoDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info icon",
                            tint = if (darkTheme) DarkText else LightText
                        )
                    }
                },
                backgroundColor = if (darkTheme) LightPrimary else DarkPrimary
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SWITCH THEME",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    modifier = Modifier
                        .width(size * 2)
                        .height(size)
                        .clip(shape = parentShape)
                        .clickable { onClick() }
                        .background(MaterialTheme.colors.primary)
                ) {
                    Box(
                        modifier = Modifier
                            .size(size)
                            .offset(x = offset)
                            .padding(all = padding)
                            .clip(shape = toggleShape)
                            .background(MaterialTheme.colors.secondary)
                    )
                    Row(
                        modifier = Modifier
                            .border(
                                border = BorderStroke(
                                    width = borderWidth,
                                    color = MaterialTheme.colors.secondary
                                ),
                                shape = parentShape
                            )
                    ) {
                        Box(
                            modifier = Modifier.size(size),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(iconSize),
                                imageVector = Icons.Default.Nightlight,
                                contentDescription = "Theme icon",
                                tint = if (darkTheme) MaterialTheme.colors.primary
                                else MaterialTheme.colors.secondary
                            )
                        }
                        Box(
                            modifier = Modifier.size(size),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(iconSize),
                                imageVector = Icons.Default.LightMode,
                                contentDescription = "Theme icon",
                                tint = if (darkTheme) MaterialTheme.colors.secondary
                                else MaterialTheme.colors.primary
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = if (darkTheme)
                        "Dark theme selected"
                    else
                        "Light theme selected",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    if (showInfoDialog) {
        val uriHandler = LocalUriHandler.current

        Dialog(
            onDismissRequest = { showInfoDialog = false }
        ) {
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .height(470.dp)
                    .width(450.dp),
                shape = RoundedCornerShape(size = 8.dp),
                backgroundColor = MaterialTheme.colors.background
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = { showInfoDialog = false },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close dialog",
                            tint = if (darkTheme) DarkText else LightText
                        )
                    }
                    Card(
                        modifier = Modifier
                            .height(400.dp)
                            .width(450.dp)
                            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = MaterialTheme.colors.onBackground,
                        elevation = 10.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.app_name),
                                color = if (darkTheme) LightText else DarkText,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = if (darkTheme) LightText else DarkText,
                                        shape = CircleShape
                                    )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(
                                color = if (darkTheme) LightText else DarkText,
                                thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = (
                                        "Android application built with Kotlin and Jetpack Compose " +
                                                "that shows how to switch theme between light and dark mode."
                                        ),
                                color = if (darkTheme) LightText else DarkText,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Divider(
                                color = if (darkTheme) LightText else DarkText,
                                thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "My GitHub",
                                color = if (darkTheme) LightText else DarkText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            IconButton(
                                onClick = { uriHandler.openUri("https://github.com/ndenicolais") },
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.github_logo),
                                    contentDescription = "Github image",
                                    colorFilter = ColorFilter.tint(if (darkTheme) LightText else DarkText)
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Developed by DeNicks21",
                        color = if (darkTheme) DarkText else LightText,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}