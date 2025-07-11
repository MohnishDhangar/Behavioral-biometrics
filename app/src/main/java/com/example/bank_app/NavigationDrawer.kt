package com.example.bank_app

import android.annotation.SuppressLint
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NavigationDrawer() {

    ///List of Navigation Items that will be clicked
    val items = listOf(
        NavigationItems(
            title = "Start Logging Data",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavigationItems(
            title = "Info",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info
        ),
        NavigationItems(
            title = "Edit",
            selectedIcon = Icons.Filled.Edit,
            unselectedIcon = Icons.Outlined.Edit,
            badgeCount = 105
        ),
        NavigationItems(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )

    //Remember Clicked index state
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = Shapes().extraSmall,
                drawerContainerColor = Color(3, 14, 36, 255),
                drawerContentColor = Color(20, 30, 122, 255)
            ) {
                Spacer(modifier = Modifier.height(16.dp)) //space (margin) from top
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            //  navController.navigate(item.route)

                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        badge = {  // Show Badge
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)      //padding between items
                    )
                }

            }
        },
        gesturesEnabled = true
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
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
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.menu_50dp),
                            contentDescription = "Home Icon",
                            modifier = Modifier.size(33.dp),
                            tint = White
                        )
                    },
                    modifier = Modifier
                        .clickable(null, LocalIndication.current, true, null) { /* Handle click */ }
                        .background(Color.Transparent),
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

                Spacer(
                    modifier = Modifier
                        .size(200.dp, 0.dp)
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { /* Handle click */ },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search_60dp),
                            contentDescription = "Home Icon",
                            modifier = Modifier.size(33.dp),
                            tint = White
                        )
                    },
                    modifier = Modifier
                        .clickable(null, LocalIndication.current, true, null) { /* Handle click */ }
                        .background(Color.Transparent),
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

                BadgedBox(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(5.dp),
                    badge = {
                        Badge(
                            modifier = Modifier
                                .size(15.dp)
                                .offset(20.dp, 6.dp),
                            containerColor = Color.Red,
                            contentColor = White,
                            content = { Text("1") }
                        )
                    },
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.notifications_70dp),
                            contentDescription = "Notifications",
                            modifier = Modifier.size(35.dp),
                            tint = White
                        )
                    }
                )
            }

            FloatingActionButton(
                onClick = { /*navController.navigate("payment_screen") */},
                modifier = Modifier
                    .size(90.dp)
                    .offset(0.dp, 365.dp),
                containerColor = Color(12, 122, 86, 255),
                contentColor = White,
                shape = CircleShape,
            ) {
                Icon(
                    painter = painterResource(R.drawable.qr_code_scanner_80dp),
                    contentDescription = "QR Scanner",
                    modifier = Modifier
                        .size(55.dp)
                )
            }

            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(70.dp)
                    .clip(
                        BottomNavBarShape(
                            cornerRadius = 15.dp,
                            dipHeight = 70.dp,
                            dipWidth = 90.dp,
                            dipControlOffset = 13.dp // Sets the beginning of the dip curve
                        )
                    ),
                containerColor = Color(103, 104, 236, 255),
                contentColor = White,
                windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 10.dp)
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Handle click */ },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.assured_workload_100dp),
                            contentDescription = "Home Icon",
                            modifier = Modifier.size(33.dp),
                            tint = White
                        )
                    },
                    modifier = Modifier
                        .clickable(null, LocalIndication.current, true, null) { /* Handle click */ }
                        .background(Color.Transparent),
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

                NavigationBarItem(
                    selected = true,
                    onClick = { /* Handle click */ },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.credit_score_100dp),
                            contentDescription = "Home Icon",
                            modifier = Modifier.size(33.dp),
                            tint = White
                        )
                    },
                    modifier = Modifier
                        .clickable(
                            null,
                            LocalIndication.current,
                            true,
                            null,
                            null
                        ) { /* Handle click */ }
                        .background(Color.Transparent),
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

                Spacer(
                    modifier = Modifier
                        .size(35.dp, 0.dp)
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { /* Handle click */ },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.currency_exchange_100dp),
                            contentDescription = "Home Icon",
                            modifier = Modifier.size(33.dp),
                            tint = White
                        )
                    },
                    modifier = Modifier
                        .clickable(
                            null,
                            LocalIndication.current,
                            true,
                            null,
                            null
                        ) { /* Handle click */ }
                        .background(Color.Transparent),
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

                NavigationBarItem(
                    selected = true,
                    onClick = { /* Handle click */ },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.wallet_100dp),
                            contentDescription = "Home Icon",
                            modifier = Modifier.size(33.dp),
                            tint = White
                        )
                    },
                    modifier = Modifier
                        .clickable(
                            null,
                            LocalIndication.current,
                            true,
                            null,
                            null
                        ) { /* Handle click */ }
                        .background(Color.Transparent),
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


        }
    }
}

