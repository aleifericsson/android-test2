package com.example.second.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CardData(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)