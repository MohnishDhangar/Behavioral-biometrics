package com.example.bank_app

import androidx.compose.foundation.Image
import androidx.compose.material3.Badge
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    val previewNavController = rememberNavController()

    HomeScreen(previewNavController)
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val TopBarGradientColors: List<Color> = listOf(
        Color(9, 15, 99, 255),
        Color(73, 30, 138, 255),
        Color(12, 122, 86, 255),
        Color(14, 63, 176, 255),
        Color(46, 121, 191, 255),

    )
    val navigateToPay = "payment_screen"
    val navigateToUPI = "pin_screen"
    val navigateToContacts = "phonebook_screen"

    val hazeState = rememberHazeState()

    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )

    Surface(
        modifier = Modifier
            .fillMaxSize(),

    ) {

        Image(
            painterResource(R.drawable.gradient_9),
            contentDescription = "Background",
            modifier = Modifier
                //.rotate(180f)
                .requiredSize(900.dp)
                .hazeSource(state = hazeState)
        )

        NavigationDrawer()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            //verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Spacer(
                modifier = Modifier
                    .size(0.dp, 70.dp)
            )

            Row(
                modifier = Modifier
                    .size(360.dp, 150.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White,
                            tint = HazeTint(
                                Color(128, 128, 128, 190),
                                BlendMode.Luminosity),
                            blurRadius = 0.dp,
                            noiseFactor = 0f)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text("User Name",
                        modifier = Modifier
                            .padding(10.dp, 5.dp, 10.dp, 2.dp)
                            .size(150.dp, 25.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.Bold
                        ))

                    Text("Balance",
                        modifier = Modifier
                            .padding(10.dp, 5.dp, 10.dp, 2.dp)
                            .size(150.dp, 25.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.Bold
                        ))

                    Text("Savings",
                        modifier = Modifier
                            .padding(10.dp, 5.dp, 10.dp, 2.dp)
                            .size(150.dp, 25.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.Bold
                        ))

                    Text("Account Number",
                        modifier = Modifier
                            .padding(10.dp, 5.dp, 10.dp, 2.dp)
                            .size(150.dp, 25.dp),
                        color = White,
                        style = TextStyle(
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = facultyGlyphic,
                            fontWeight = FontWeight.Bold
                        ))
                }

                IconButton(
                    onClick = { /* Handle click */ },
                    modifier = Modifier
                        .padding(1.dp, 5.dp, 10.dp, 2.dp)
                        .size(100.dp, 200.dp),
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.person_edit_200dp),
                            contentDescription = "Account Icon",
                            modifier = Modifier
                                .requiredSize(100.dp),
                            tint = Color(193, 254, 236, 255)
                        )
                    }
                )

            }

            Text(
                "Pay & Transfer",
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 10.dp, 0.dp, 10.dp),
                color = White,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Row(
                modifier = Modifier
                    .size(360.dp, 120.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White,
                            tint = HazeTint(
                                Color(128, 128, 128, 190),
                                BlendMode.Luminosity),
                            blurRadius = 0.dp,
                            noiseFactor = 0f)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,

            ) {

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.payments_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "Send Money",
                    navController,
                    navigateToPay
                )

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.savings_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "Savings",
                    navController,
                    navigateToPay
                )

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.wallet_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "Wallet",
                    navController,
                    navigateToPay
                )

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.payment_card_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "Cards",
                    navController,
                    navigateToPay
                )
            }

            Text(
                "UPI",
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 10.dp, 0.dp, 10.dp),
                color = White,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Row(
                modifier = Modifier
                    .size(360.dp, 120.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White,
                            tint = HazeTint(
                                Color(128, 128, 128, 190),
                                BlendMode.Luminosity),
                            blurRadius = 0.dp,
                            noiseFactor = 0f)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.atm_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "ATM/Branch",
                    navController,
                    navigateToPay
                )


                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.upi_pay_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "UPI Payments",
                    navController,
                    navigateToUPI
                )

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.currency_bitcoin_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "Crypto",
                    navController,
                    navigateToPay
                )

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.checkbook_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "Passbook",
                    navController,
                    navigateToPay
                )
            }

            Text(
                "Bills & Recharge",
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 10.dp, 0.dp, 10.dp),
                color = White,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Row(
                modifier = Modifier
                    .size(360.dp, 120.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White,
                            tint = HazeTint(
                                Color(128, 128, 128, 190),
                                BlendMode.Luminosity),
                            blurRadius = 0.dp,
                            noiseFactor = 0f)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.account_circle_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "ATM/Branch",
                    navController,
                    navigateToContacts
                )

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.upi_pay_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "UPI Payments",
                    navController,
                    navigateToPay
                )

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.currency_bitcoin_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "Crypto",
                    navController,
                    navigateToPay
                )

                summonUserIcon(
                    80.dp,
                    120.dp,
                    Shapes().medium,
                    Color.Transparent,
                    Color(103, 104, 236, 255),
                    R.drawable.checkbook_100dp,
                    "Menu",
                    50.dp,
                    Color(193, 254, 196, 255),
                    true,
                    "Passbook",
                    navController,
                    navigateToPay
                )
            }

            Text(
                "Services",
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 10.dp, 0.dp, 10.dp),
                color = White,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Row(
                modifier = Modifier
                    .size(360.dp, 120.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White,
                            tint = HazeTint(
                                Color(128, 128, 128, 190),
                                BlendMode.Luminosity),
                            blurRadius = 0.dp,
                            noiseFactor = 0f)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
            )
            {}

            Text(
                "Cards",
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 10.dp, 0.dp, 10.dp),
                color = White,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Row(
                modifier = Modifier
                    .size(360.dp, 120.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White,
                            tint = HazeTint(
                                Color(128, 128, 128, 190),
                                BlendMode.Luminosity),
                            blurRadius = 0.dp,
                            noiseFactor = 0f)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
            )
            {}

            Text(
                "Currency",
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 10.dp, 0.dp, 10.dp),
                color = White,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Row(
                modifier = Modifier
                    .size(360.dp, 120.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White,
                            tint = HazeTint(
                                Color(128, 128, 128, 190),
                                BlendMode.Luminosity),
                            blurRadius = 0.dp,
                            noiseFactor = 0f)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
            )
            {}

        }

    }
}

@Composable
fun summonUserIcon(
    buttonWidth: Dp,
    buttonHeight: Dp,
    buttonShape: CornerBasedShape,
    buttonColor: Color,
    buttonContentColor: Color,
    imageAddress: Int,
    imageLabel: String,
    iconSize: Dp,
    iconTint: Color,
    hasName: Boolean,
    iconTitle: String = "",
    buttonNavController: NavHostController,
    route: String
) {

    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )

    IconButton(
        modifier = Modifier
            .clickable(onClick = { /* Handle click */ })
            .size(buttonWidth, buttonHeight)
            .clip(buttonShape),
        colors = IconButtonColors(
            containerColor = buttonColor,
            contentColor = buttonContentColor,
            disabledContainerColor = Gray,
            disabledContentColor = LightGray,
        ),
        onClick = { /**/ },
    ) {
        Icon(
            painter = painterResource(imageAddress),
            contentDescription = imageLabel,
            modifier = Modifier
                .requiredSize(iconSize)
                .offset(0.dp, (-20).dp)
                .clickable(
                    interactionSource = null,
                    indication = LocalIndication.current,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = { buttonNavController.navigate(route) }
                ),
            tint = iconTint,
        )

        if (hasName) {
            Text(
                text = iconTitle,
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp, 0.dp, 4.dp, 0.dp)
                    .offset(0.dp, 25.dp),
                color = White,
                style = TextStyle(
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = facultyGlyphic,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}


@Composable
fun summonSysIcon(
    buttonSize: Dp,
    buttonShape: CornerBasedShape,
    buttonColor: Color,
    buttonContentColor: Color,
    imageAddress: Int,
    imageLabel: String,
    iconSize: Dp,
    iconTint: Color,
)
{
    IconButton(
        modifier = Modifier
            .clickable(onClick = { /* Handle click */ })
            .size(buttonSize)
            .clip(buttonShape),
        onClick = { /* Handle button click */ },
        colors = IconButtonColors(
            containerColor = buttonColor,
            contentColor = buttonContentColor,
            disabledContainerColor = Gray,
            disabledContentColor = LightGray,
        )
    ) {
        Icon(
            painter = painterResource(imageAddress),
            contentDescription = imageLabel,
            modifier = Modifier
                .requiredSize(iconSize)
                .clickable(
                    interactionSource = null,
                    indication = LocalIndication.current,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = { /* Handle click */ }
                ),
            tint = iconTint
        )

    }
}