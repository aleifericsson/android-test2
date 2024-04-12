package com.example.second.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.second.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(GameUIState()) //makes a variable that stores the variables in GUIS, which is changeable
    val uiState: StateFlow<GameUIState> = _uiState.asStateFlow() //readonly version of GUIS that is public

    var userIn by mutableStateOf("")
        private set

    init{
        resetGame()
    }

    fun updateUserGuess(guessedWord:String){
        userIn = guessedWord
    }

    fun addCookie(added:Int = 1){
        _uiState.update { currentState ->
            currentState.copy(
                currentRandom = (0 .. 10).random(),
                cookies = currentState.cookies+added,
            )
        }
    }

    fun checkQuestion(): Boolean {
        return if (_uiState.value.currentRandom == 1){
            _uiState.update { currentState ->
                currentState.copy(
                    showQuestion = true,
                )
            }
            true
        } else{
            false
        }
    }

    fun checkAnswer(answer: String): Boolean{
        return if(_uiState.value.currentQuestion.answer == answer.toInt()){
            _uiState.update { currentState ->
                currentState.copy(
                    currentRandom = (0..10).random(),
                    showQuestion = false,
                    cookies = currentState.cookies + 10,
                    currentQuestion = Question()
                )
            }
            true
        } else{
            _uiState.update { currentState ->
                currentState.copy(
                    currentRandom = (0..10).random(),
                    showQuestion = false,
                    cookies = currentState.cookies -20,
                    currentQuestion = Question()
                )
            }
            false
        }
    }



    fun resetGame(){
        _uiState.value = GameUIState() //makes _uiState back into what the constructor was
    }
}