package com.example.bank_app

import androidx.compose.foundation.Image
import androidx.compose.animation.slideInVertically
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
@Preview(showBackground = true)
fun PayToContactPreview(){
    val previewNavController = rememberNavController()

    PayToContact(previewNavController)
}

@Composable
fun PayToContact(navController: NavHostController){

    val navigateToHome = "home_screen"

    var accNum by remember { mutableStateOf("") }
    var ifsc by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    val maxCharAcc = 12
    val maxCharIFSC = 10

    val gradientColors: List<Color> = listOf(
        Color(9, 15, 99, 255),
        Color(73, 30, 138, 255),
        Color(12, 122, 86, 255),
        Color(14, 63, 176, 255),
        Color(46, 121, 191, 255),

        )

    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        contentColor = Color.Black

    ) {
        Column(
            modifier = Modifier
                .background(color = Color(3, 133, 72, 255))
                .fillMaxSize(),
            //horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(55.dp),
                containerColor = Color(103, 104, 236, 255),
                contentColor = White,
                windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*navController.navigate(navigateToRoute)*/ },
                    icon = {
                        Icon(
                            painterResource(R.drawable.arrow_back_100dp),
                            contentDescription = "Back to Home",
                            modifier = Modifier.size(33.dp),
                            tint = White
                        )
                    },
                    modifier = Modifier
                        .clickable(null, LocalIndication.current, true, null) { /* Handle click */ }
                        .background(Color.Transparent)
                        .offset((-170).dp, 0.dp),
                    enabled = true,
                    alwaysShowLabel = false,
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color(12, 122, 86, 255),
                        unselectedIconColor = Gray,
                        selectedTextColor = White,
                        unselectedTextColor = Gray,
                        selectedIndicatorColor = Color.Transparent,
                        disabledIconColor = Gray,
                        disabledTextColor = Gray
                    ),
                    interactionSource = null
                )
            }

            Text(
                "Paying to ContactName",
                Modifier
                    .padding(30.dp, 20.dp, 10.dp, 40.dp),
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                    textAlign = TextAlign.Start,
                ),

                )

            Text(
                "Enter amount",
                Modifier
                    .padding(30.dp, 0.dp, 10.dp, 5.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                    textAlign = TextAlign.Start,
                ),

                )

            TextField(
                value = amount,
                onValueChange = {if(it.length <= maxCharIFSC)
                {
                    amount = it
                } },
                modifier = Modifier
                    .padding(15.dp, 0.dp , 10.dp, 0.dp)
                    .size(360.dp, 60.dp)
                    .border(BorderStroke(2.dp, linearGradient(gradientColors)), Shapes().large),
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.W100,
                    color = White,
                ),
                singleLine = true,
                label = { Text("0.00", fontSize = 24.sp) },
                shape = Shapes().large,
                leadingIcon = {Image(
                    painterResource(R.drawable.currency_rupee_100dp),
                    "Rupees"
                )},
                colors = TextFieldColors(
                    focusedLabelColor = White,
                    unfocusedLabelColor = White,
                    disabledLabelColor = Color.Black,
                    errorLabelColor = Gray,
                    focusedTextColor = White,
                    unfocusedTextColor = Blue,
                    focusedContainerColor = LightGray,
                    unfocusedContainerColor = Gray,
                    unfocusedPrefixColor = White,
                    focusedPrefixColor = Gray,
                    unfocusedSuffixColor = Gray,
                    focusedSuffixColor = Gray,
                    focusedIndicatorColor = Gray,
                    unfocusedIndicatorColor = Gray,
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
                    cursorColor = Gray,
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
                    textSelectionColors = TextSelectionColors(handleColor = Red, backgroundColor = Blue),
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    showKeyboardOnFocus = true),

                )

            Button(
                onClick = { navController.navigate(navigateToHome) },
                modifier = Modifier
                    .padding(50.dp)
                    .size(350.dp, 55.dp)
                    .clickable(onClick = { /* Handle click */ }),
                content = { Text(
                    text = "Pay",
                    fontSize = 25.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W300) },
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = Color(120, 199, 122, 255),
                    disabledContentColor = Gray,
                    disabledContainerColor = Color(0xFF609C10)),
                shape = ShapeDefaults.Medium
            )

        }
    }
}