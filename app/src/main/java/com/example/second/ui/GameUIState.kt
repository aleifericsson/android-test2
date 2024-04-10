package com.example.second.ui

import com.example.second.model.Question

data class GameUIState(
    val currentRandom: Int = 0,
    val cookies: Int = 0,
    val isGameOver: Boolean = false,
    val currentQuestion: Question = Question(),
    val showQuestion: Boolean = false,
)
