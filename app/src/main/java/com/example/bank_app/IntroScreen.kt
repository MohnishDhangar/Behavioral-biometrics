package com.example.bank_app

import androidx.compose.foundation.Image
import androidx.compose.animation.slideInVertically
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.rememberHazeState


@Composable
@Preview(showBackground = true)
fun IntroScreenPreview()
{
    val previewNavController = rememberNavController()

    IntroScreen(previewNavController)
}

@Composable
fun IntroScreen(navController: NavHostController) {

    val navigateToPassword = "password_screen"
    val navigateToRegister = "register_screen"

    val hazeState = rememberHazeState()

    val gradientColors = listOf(Cyan, Gray, Magenta /*...*/)
    var visible by remember { mutableStateOf(true) }
    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painterResource(R.drawable.gradient_9),
            contentDescription = "Background",
            modifier = Modifier
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            AnimatedVisibility(
                visible = visible,
                enter =
                        slideInVertically(
                            initialOffsetY = { -50 },

                        ),
                label = "Logo Fade In/Out",
            ) {
                Text(
                    modifier = Modifier
                        .offset(0.dp, 80.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                brush = Brush.linearGradient(
                                    colors = gradientColors
                                ),
                                fontSize = 50.sp,
                                fontFamily = facultyGlyphic,
                                fontWeight = FontWeight.W200
                            )
                        ) {
                            append("App Name")
                        }
                    }
                )
                Icon(
                    painter = painterResource(R.drawable.alpine_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .offset(0.dp, (-100).dp),
                    tint = Color(214, 117, 229, 255)
                )
            }

            Button(
                onClick = { navController.navigate(navigateToRegister) },
                modifier = Modifier
                    .padding(16.dp)
                    .size(350.dp, 55.dp)
                    .clickable(onClick = { /**/ })
                    .clip(shape = RoundedCornerShape(66.dp))
                    .border(
                        width = 1.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.5f),
                                Color.White.copy(alpha = 0.2f),
                            ),
                        ),
                        shape = RoundedCornerShape(66.dp)
                    )
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White.copy(alpha = 0.1f),
                            tint = HazeTint(
                                Color(128, 128, 128, 200),
                                BlendMode.Luminosity
                            ),
                            blurRadius = 2.dp,
                            noiseFactor = 0f
                        )
                    ),
                content = { Text(
                    text = "Register",
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W300) },
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                    disabledContentColor = Gray,
                    disabledContainerColor = Color.Gray),
                shape = ShapeDefaults.Medium
            )

            Button(
                onClick = { navController.navigate(navigateToPassword) },
                modifier = Modifier
                    .padding(16.dp)
                    .size(350.dp, 55.dp)
                    .clickable(onClick = { /* Handle click */ })
                    .clip(shape = RoundedCornerShape(66.dp))
                    .border(
                        width = 1.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.5f),
                                Color.White.copy(alpha = 0.2f),
                            ),
                        ),
                        shape = RoundedCornerShape(66.dp)
                    )
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White.copy(alpha = 0.1f),
                            tint = HazeTint(
                                Color(128, 128, 128, 200),
                                BlendMode.Luminosity
                            ),
                            blurRadius = 2.dp,
                            noiseFactor = 0f
                        )
                    ),
                content = { Text(
                                text = "Log In",
                                fontSize = 22.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.W300) },
                colors = ButtonColors(
                                    contentColor = Color.White,
                                    containerColor = Color.Transparent,
                                    disabledContentColor = Gray,
                                    disabledContainerColor = Color(0xFF609C10)),
                shape = ShapeDefaults.Medium
            )
        }
    }
}
