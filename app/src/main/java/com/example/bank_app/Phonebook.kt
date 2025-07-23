package com.example.bank_app

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.animation.slideInVertically
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
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
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import kotlin.plus

val facultyGlyphic = FontFamily(
    Font(R.font.faculty_glyphic_regular)
)

@Composable
@Preview(showBackground = true)
fun ContactsScreenPreview(){
    val previewNavController = rememberNavController()

    ContactsScreen(previewNavController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsScreen(navController: NavHostController) {
    val hazeState = remember { HazeState() }
    val navigateToPay = "pay_contacts"
    val contacts = remember { mutableStateOf(
        listOf(
            Pair("Fernando Alonso", R.drawable.alonso),
            Pair("Zinedine Zidane", R.drawable.zidane),
            Pair("Mika Hakkinen", R.drawable.mika),
            Pair("Charles Leclerc", R.drawable.mv33),
            Pair("Kimi Raikkonen", R.drawable.kimi),
            Pair("Jean Todt", R.drawable.jean_todt),
            Pair("Jose Mourinho", R.drawable.mourinho),
            Pair("Alex Ferguson", R.drawable.ferguson),
            Pair("Max Emilian Verstappen", R.drawable.mv33),
            Pair("Valentino Rossi", R.drawable.vale_rossi),
            Pair("Laurens Vanthoor", R.drawable.laurens_vanthoor),
            Pair("Kevin Estre", R.drawable.k_estre),
            Pair("Sebastian Vettel", R.drawable.seb),
            Pair("Antonio Giovinazzi", R.drawable.antonio_giovinazzi),
            Pair("Ross Brawn", R.drawable.ross_brawn),
            Pair("Toto Wolff", R.drawable.toto),
            Pair("Niki Lauda", R.drawable.niki),
            Pair("Marc Marquez", R.drawable.mm93),
            Pair("Alessandro Pier Guidi", R.drawable.pier_guidi),
            Pair("Alberto Ascari", R.drawable.ascari),
        )
    ) }
    var showDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf("") }
    var newImageRes by remember { mutableStateOf(R.drawable.contacts_product_100dp) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(380.dp, 75.dp)
                    .height(64.dp)
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White.copy(alpha = 0.1f),
                            tint = HazeTint(
                                Color(128, 128, 128, 0),
                                BlendMode.Luminosity
                            ),
                            blurRadius = 30.dp,
                            noiseFactor = 0f
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {navController.navigate("home_screen")},
                    modifier = Modifier
                        .size(60.dp)
                        .clickable(true, onClick = {})
                        .padding(4.dp, 30.dp, 4.dp, 0.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.arrow_back_100dp),
                        contentDescription = "Menu Button",
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }

                Text(
                    "Pay to Phonebook",
                    modifier = Modifier
                        .weight(7f)
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .offset(0.dp, 33.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        fontFamily = facultyGlyphic,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                // 2. Add contact on icon click
                IconButton(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .size(60.dp)
                        .padding(4.dp, 30.dp, 4.dp, 0.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.person_add_100dp),
                        contentDescription = "Add Contact",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painterResource(R.drawable.gradient_13),
                contentDescription = "Background",
                modifier = Modifier
                    .requiredSize(900.dp)
                    .hazeSource(state = hazeState)
            )

            Column(
                Modifier
                    .hazeSource(hazeState)
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.size(80.dp).fillMaxWidth())

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Add New Contact") },
                        text = {
                            Column {
                                OutlinedTextField(
                                    value = newName,
                                    onValueChange = { newName = it },
                                    label = { Text("Contact Name") }
                                )
                                // Placeholder for image picker
                                Text("Image: (default used, add picker as needed)")
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    if (newName.isNotBlank()) {
                                        contacts.value = contacts.value + Pair(newName, newImageRes)
                                        newName = ""
                                        showDialog = false
                                    }
                                }
                            ) { Text("Add") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) { Text("Cancel") }
                        }
                    )
                }

                contacts.value.forEach { (name, imageRes) ->
                    createContactTab(
                        name = name,
                        navController = navController,
                        route = navigateToPay,
                        hazeState = hazeState,
                        imageAddress = imageRes
                    )
                }
                Spacer(Modifier.size(80.dp).fillMaxWidth())
            }
        }
    }
}

@Composable
fun createContactTab(
    name: String = "User",
    navController: NavHostController,
    route: String,
    hazeState: HazeState,
    imageAddress: Int,
)
{
    Row(
        modifier = Modifier
            .size(380.dp, 65.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                true,
                onClick = { navController.navigate(route)},
                onClickLabel = null),
    ) {
        Image(
            painterResource(imageAddress),
            contentDescription = null,
            modifier = Modifier
//                .weight(1f)
                .size(60.dp)
                .padding(15.dp, 1.dp, 1.dp, 1.dp)
                .clip(CircleShape),
        )

        Text(
            text = name,
            color = Color.White,
            modifier = Modifier
                //.weight(8f)
                .padding(15.dp, 15.dp, 15.dp, 15.dp),
            style = TextStyle(
                fontSize = 19.sp,
                textAlign = TextAlign.Start,
                fontFamily = facultyGlyphic,
                fontWeight = FontWeight.W300
            )
        )
    }
}