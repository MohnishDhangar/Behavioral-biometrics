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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.DarkGray
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
fun CardPayScreenPreview()
{
    val previewNavController = rememberNavController()

    CardPayScreen(
        navController = previewNavController
    )
}

@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun CardPayScreen(navController: NavHostController){
    val hazeState = rememberHazeState()
    var pinVisibility by remember { mutableStateOf(false) }
    var atmPIN by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var lastChangeTime by remember { mutableStateOf(System.currentTimeMillis()) }

    val icon = if (pinVisibility){
        painterResource(id = R.drawable.visibility_100dp)
    }
    else
        painterResource(id = R.drawable.visibility_off_100dp)

    Box (
        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(
            painterResource(R.drawable.gradient_15),
            contentDescription = "Background",
            modifier = Modifier
                //.rotate(180f)
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )

        Surface(
            modifier = Modifier
                .size(370.dp, 450.dp)
                .align(Alignment.Center)
                .offset(0.dp, (-115).dp)
                .clip(shape = RoundedCornerShape(66.dp))
                .border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.9f),
                            Color.White.copy(alpha = 0.3f),
                        ),
                    ),
                    shape = RoundedCornerShape(66.dp)
                )
                .hazeEffect(
                    state = hazeState,
                    style = HazeStyle(
                        White,
                        tint = HazeTint(
                            Color(128, 128, 128, 0),
                            BlendMode.Luminosity
                        ),
                        blurRadius = 20.dp,
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
                    onClick = { navController.navigate("home_screen") },
                    icon = {
                        Icon(
                            painterResource(R.drawable.arrow_back_100dp),
                            contentDescription = "Back to Home",
                            modifier = Modifier.size(33.dp),
                            tint = Color.White
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

                Spacer(
                    Modifier
                        .size(50.dp)
                        .weight(5f)
                )
            }

            Text(
                text = "Enter Card Number",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W300,
                    color = Color(12, 122, 86, 255),
                    fontFamily = facultyGlyphic,
                ),
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .offset(10.dp, (-10).dp)
                    .size(340.dp, 40.dp),
                textAlign = TextAlign.Left
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(0.dp, (-70).dp)
            ) {
                repeat(4) { index ->
                    CardNumField()
                }
            }

            Text(
                text = "Expiry Date",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W300,
                    color = Color(12, 122, 86, 255),
                    fontFamily = facultyGlyphic,
                ),
                modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .offset(10.dp, (-100).dp)
                    .size(340.dp, 40.dp),
                textAlign = TextAlign.Left
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(0.dp, (-160).dp)
                    .fillMaxWidth()
                    .padding(30.dp, 0.dp, 0.dp, 0.dp)
            ) {
                CardDateNumField()
                VerticalDivider(
                    thickness = 2.dp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .height(50.dp)
                        .padding(10.dp),
                )
                CardDateNumField()
            }

            TextField(
                value = cvv,
                onValueChange = { newText ->
                    if (cvv.length <= 2)
                    {   val oldText = cvv
                        lastChangeTime = logTextFieldBehavior(
                            label = "Enter CVV",
                            oldText = oldText,
                            newText = newText,
                            lastTimestamp = lastChangeTime
                        )
                        cvv = newText }
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp , 10.dp, 0.dp)
                    .size(300.dp, 60.dp)
                    .offset(0.dp, (-200).dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                ),
                singleLine = true,
                label = { Text("Enter CVV", modifier = Modifier.offset((-8).dp, 0.dp)) },
                shape = Shapes().large,
                colors = TextFieldColors(
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    disabledLabelColor = Color.Black,
                    errorLabelColor = Gray,
                    focusedTextColor = White,
                    unfocusedTextColor = Blue,
                    focusedContainerColor = Transparent,
                    unfocusedContainerColor = Transparent,
                    unfocusedPrefixColor = White,
                    focusedPrefixColor = Gray,
                    unfocusedSuffixColor = Gray,
                    focusedSuffixColor = Gray,
                    focusedIndicatorColor = LightGray,
                    unfocusedIndicatorColor = LightGray,
                    focusedPlaceholderColor = Gray,
                    unfocusedPlaceholderColor = Gray,
                    focusedTrailingIconColor = Gray,
                    unfocusedLeadingIconColor = Gray,
                    focusedLeadingIconColor = Gray,
                    unfocusedTrailingIconColor = Gray,
                    focusedSupportingTextColor = Gray,
                    unfocusedSupportingTextColor = Gray,
                    disabledTextColor = Gray,
                    errorTextColor = Gray,
                    disabledContainerColor = Gray,
                    cursorColor = Transparent,
                    errorCursorColor = Gray,
                    disabledIndicatorColor = Gray,
                    errorIndicatorColor = Gray,
                    disabledLeadingIconColor = Gray,
                    errorLeadingIconColor = Gray,
                    disabledTrailingIconColor = Gray,
                    errorTrailingIconColor = Gray,
                    disabledPlaceholderColor = Gray,
                    errorPlaceholderColor = Gray,
                    disabledSupportingTextColor = Gray,
                    errorSupportingTextColor = Gray,
                    disabledPrefixColor = Gray,
                    errorPrefixColor = Gray,
                    disabledSuffixColor = Gray,
                    errorSuffixColor = Gray,
                    errorContainerColor = Gray,
                    textSelectionColors = TextSelectionColors(handleColor = Color.Magenta, backgroundColor = Blue),
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    showKeyboardOnFocus = true),
                visualTransformation = PasswordVisualTransformation(0x204E.toChar())
            )

            TextField(
                value = atmPIN,
                onValueChange = { newText ->
                    if (atmPIN.length <= 3)
                    {   val oldText = atmPIN
                        lastChangeTime = logTextFieldBehavior(
                        label = "Enter PIN",
                        oldText = oldText,
                        newText = newText,
                        lastTimestamp = lastChangeTime
                    )
                    atmPIN = newText }
                },
                modifier = Modifier
                    .padding(0.dp, 0.dp , 10.dp, 0.dp)
                    .size(300.dp, 60.dp)
                    .offset(0.dp, (-250).dp),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                ),
                singleLine = true,
                label = { Text("Enter PIN", modifier = Modifier.offset((-8).dp, 0.dp)) },
                shape = Shapes().large,
                colors = TextFieldColors(
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    disabledLabelColor = Color.Black,
                    errorLabelColor = Gray,
                    focusedTextColor = White,
                    unfocusedTextColor = Blue,
                    focusedContainerColor = Transparent,
                    unfocusedContainerColor = Transparent,
                    unfocusedPrefixColor = White,
                    focusedPrefixColor = Gray,
                    unfocusedSuffixColor = Gray,
                    focusedSuffixColor = Gray,
                    focusedIndicatorColor = LightGray,
                    unfocusedIndicatorColor = LightGray,
                    focusedPlaceholderColor = Gray,
                    unfocusedPlaceholderColor = Gray,
                    focusedTrailingIconColor = Gray,
                    unfocusedLeadingIconColor = Gray,
                    focusedLeadingIconColor = Gray,
                    unfocusedTrailingIconColor = Gray,
                    focusedSupportingTextColor = Gray,
                    unfocusedSupportingTextColor = Gray,
                    disabledTextColor = Gray,
                    errorTextColor = Gray,
                    disabledContainerColor = Gray,
                    cursorColor = Transparent,
                    errorCursorColor = Gray,
                    disabledIndicatorColor = Gray,
                    errorIndicatorColor = Gray,
                    disabledLeadingIconColor = Gray,
                    errorLeadingIconColor = Gray,
                    disabledTrailingIconColor = Gray,
                    errorTrailingIconColor = Gray,
                    disabledPlaceholderColor = Gray,
                    errorPlaceholderColor = Gray,
                    disabledSupportingTextColor = Gray,
                    errorSupportingTextColor = Gray,
                    disabledPrefixColor = Gray,
                    errorPrefixColor = Gray,
                    disabledSuffixColor = Gray,
                    errorSuffixColor = Gray,
                    errorContainerColor = Gray,
                    textSelectionColors = TextSelectionColors(handleColor = Color.Magenta, backgroundColor = Blue),
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    showKeyboardOnFocus = true),
                visualTransformation = PasswordVisualTransformation(0x204E.toChar())
            )

            Button(
                onClick = {
                        navController.navigate("payment_success")
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(300.dp, 55.dp)
                    .offset(0.dp, (-90).dp)
                    .clickable(onClick = { /**/ })
                    .clip(shape = RoundedCornerShape(66.dp))
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.9f),
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
                    text = "Pay",
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

        }
    }
}