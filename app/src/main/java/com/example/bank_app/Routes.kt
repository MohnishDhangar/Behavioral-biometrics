package com.example.bank_app

import androidx.annotation.DrawableRes

data class Screen(
    val route: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
)