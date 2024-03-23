package com.example.second.data
import com.example.second.model.CardData
import com.example.second.R

class Datasource {
    fun loadCards(): List<CardData>{
        return listOf<CardData>(
            CardData(R.string.string1, R.drawable.image1),
            CardData(R.string.string2, R.drawable.image2),
            CardData(R.string.string3, R.drawable.image3),
            CardData(R.string.string4, R.drawable.image4),
            CardData(R.string.string5, R.drawable.image5),
            CardData(R.string.string6, R.drawable.image6),
        )
    }
}