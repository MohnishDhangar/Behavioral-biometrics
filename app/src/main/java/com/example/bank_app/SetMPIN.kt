package com.example.bank_app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bank_app.ui.theme.Shapes
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.rememberHazeState
import kotlin.collections.listOf

@Composable
@Preview(showBackground = true)
fun SetPinScreenPreview()
{
    val previewNavController = rememberNavController()

    SetPinScreen(
        navController = previewNavController
    )
}


@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun SetPinScreen(navController: NavHostController) {

    val navigateToRoute = "home_screen"
    val hazeState = rememberHazeState()

    var username by remember { mutableStateOf("") }
    val maxLenUsername = 10

    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )
    val gradientColors = listOf(Yellow, Red, Cyan, Magenta, Blue)

    Box (
        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(
            painterResource(R.drawable.gradient_9),
            contentDescription = "Background",
            modifier = Modifier
                //.rotate(180f)
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )

        Surface(
            //shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .size(370.dp, 400.dp)
                .align(Alignment.Center)
                .offset(0.dp, 50.dp)
                .clip(shape = RoundedCornerShape(66.dp))
                .border(
                    width = 2.dp,
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
            color = Transparent
        ) {  }

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(75.dp),
                containerColor = Transparent,
                contentColor = White,
                windowInsets = WindowInsets(0.dp, 30.dp, 0.dp, 0.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*navController.navigate(navigateToRoute) */ },
                    icon = {
                        Icon(
                            painterResource(R.drawable.arrow_back_100dp),
                            contentDescription = "Back to Home",
                            modifier = Modifier.size(33.dp),
                            tint = Cyan
                        )
                    },
                    modifier = Modifier
                        .clickable(null, LocalIndication.current, true, null) { /* Handle click */ }
                        .background(Transparent)
                        .weight(1f),
                    //.offset((-170).dp, 0.dp),
                    enabled = true,
                    alwaysShowLabel = false,
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color(12, 122, 86, 255),
                        unselectedIconColor = Gray,
                        selectedTextColor = White,
                        unselectedTextColor = Gray,
                        selectedIndicatorColor = Transparent,
                        disabledIconColor = Gray,
                        disabledTextColor = Gray
                    ),
                    interactionSource = null
                )

                Spacer(Modifier
                    .size(50.dp)
                    .weight(5f))
            }

            Icon(
                painter = painterResource(R.drawable.alpine_logo),
                contentDescription = null,
                modifier = Modifier
                    .offset(0.dp, (-120).dp)
                    .size(250.dp),
                tint = Color(214, 117, 229, 255)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color(214, 217, 229, 255),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = facultyGlyphic
                        )
                    ) {
                        append("Set MPIN")
                    }
                },
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 35.dp)
                    .offset(0.dp, (-190).dp),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(0.dp, (-250).dp)
            ) {
                repeat(6) { index ->
                    PasswordChar()
                }
            }

            ElevatedButton(
                onClick = { navController.navigate(navigateToRoute) },
                modifier = Modifier
                    .size(250.dp, 50.dp)
                    .offset(0.dp, (-300).dp)
                    .clickable { /**/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(21, 122, 175, 255), // Light color for button background
                    contentColor = Color(212, 214, 236, 255) // Dark color for text
                ),
                shape = Shapes().small,
                content = { Text(
                    text = "Confirm",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W300,
                        color = White,
                    )) },
            )
        }
    }
}