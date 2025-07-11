package com.example.bank_app

import androidx.compose.foundation.Image
import androidx.compose.animation.slideInVertically
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val facultyGlyphic = FontFamily(
    Font(R.font.faculty_glyphic_regular)
)

@Composable
@Preview(showBackground = true)
fun ContactsScreenPreview(){
    val previewNavController = rememberNavController()

    ContactsScreen(previewNavController)
}

@Composable
fun ContactsScreen(navController: NavHostController) {

    val navigateToPay = "pay_contacts"

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(3, 14, 36, 255),
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(55.dp),
                containerColor = Color.Transparent,
                contentColor = White,
                windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /*navController.navigate(navigateToRoute) */},
                    icon = {
                        Icon(
                            painterResource(R.drawable.arrow_back_100dp),
                            contentDescription = "Back to Home",
                            modifier = Modifier.size(33.dp),
                            tint = Color.Cyan
                        )
                    },
                    modifier = Modifier
                        .clickable(null, LocalIndication.current, true, null) { /* Handle click */ }
                        .background(Color.Transparent)
                        .weight(1f),
                        //.offset((-170).dp, 0.dp),
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

                Text(
                    "Pay to Phonebook",
                    modifier = Modifier
                        .weight(7f)
                        .padding(15.dp, 0.dp, 15.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Start,
                        fontFamily = facultyGlyphic,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer( Modifier.size(20.dp).fillMaxWidth() )
            createContactTab( "Fernando Alonso", Cyan, navController, navigateToPay )
            createContactTab( "Lewis Hamilton", Color.Yellow, navController, navigateToPay)
            createContactTab( "Charles Leclerc", Color.Red, navController, navigateToPay )
            createContactTab( "Max Emilian Verstappen", Color.Blue, navController, navigateToPay )
            createContactTab( "Rafael Nadal", Color.Green, navController, navigateToPay )
            createContactTab( "Laurens Vanthoor",Color.Red, navController, navigateToPay )
            createContactTab( "Robert Kubica", Color.Yellow, navController, navigateToPay )
            createContactTab( "Kevin Estre", Color.Red, navController, navigateToPay )
            createContactTab( "Dries Vanthoor", Color.Red, navController, navigateToPay )
            createContactTab( "Antonio Giovinazzi", Color.Yellow, navController, navigateToPay )
            createContactTab( "Ross Brawn", Color.Blue, navController, navigateToPay )
            createContactTab( "Pastor Maldonado", Color.Cyan, navController, navigateToPay )
            createContactTab( "Emmerson Fittipaldi", Color.Blue, navController, navigateToPay)
            createContactTab( "Stirling Moss", Color.Green, navController, navigateToPay )
            createContactTab( "Alastair Cook", Color.Red, navController, navigateToPay )
            createContactTab( "Alessandro Pier Guidi", Color.Cyan, navController, navigateToPay )
            createContactTab( "Alberto Ascari", Color.Yellow, navController, navigateToPay )
            createContactTab( "Sebastian Vettel", Color.Blue, navController, navigateToPay )
            createContactTab( "Nico Hulkenberg", Color.Green, navController, navigateToPay )
            createContactTab( "Mika Hakkinen", Color.Cyan, navController, navigateToPay )
        }
    }
}

@Composable
fun createContactTab(
    name: String = "User",
    tint: Color,
    navController: NavHostController,
    route: String
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent, RectangleShape),

    ) {
        Image(
            painterResource(R.drawable.account_circle_100dp),
            contentDescription = null,
            modifier = Modifier
//                .weight(1f)
                .size(60.dp)
                .padding(15.dp, 1.dp, 1.dp, 1.dp),
            colorFilter = ColorFilter.tint(tint)
        )

        Text(
            text = name,
            color = Color.White,
            modifier = Modifier
                //.weight(8f)
                .padding(15.dp, 15.dp, 15.dp, 15.dp)
                .clickable(true, onClick = { navController.navigate(route)},
                    onClickLabel = null),
            style = TextStyle(
                fontSize = 19.sp,
                textAlign = TextAlign.Start,
                fontFamily = facultyGlyphic,
                fontWeight = FontWeight.W300
            )
        )
    }
}