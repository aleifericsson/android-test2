package com.example.second.model

class Question ()
{
    //NEXT TIME USE LATEINIT INSTEAD OF WHATEVER THIS IS
    val operator:Operator = when( (0 .. 14).random()){
        in 0 .. 4 -> Operator.PLUS
        5,6,7 -> Operator.MINUS
        in 8 .. 11 -> Operator.MULT
        12,13 -> Operator.DIV
        else -> Operator.POWER
    }
    val randDiv = (0 .. 10).random()
    var number1 = when(operator){
        Operator.PLUS ->(0 .. 30).random()
        Operator.MINUS ->(0 .. 30).random()
        Operator.MULT ->(0 .. 15).random()
        Operator.DIV ->(0 .. 10).random() * randDiv
        else -> (0 .. 15).random()
    }
    var number2 = when(operator){
        Operator.PLUS ->(0 .. 30).random()
        Operator.MINUS ->(0 .. 30).random()
        Operator.MULT ->(0 .. 15).random()
        Operator.DIV -> number1 / randDiv
        else -> 2
    }
    val answer = when(operator) {
        Operator.PLUS -> number1 + number2
        Operator.MINUS -> number1 - number2
        Operator.MULT -> number1 * number2
        Operator.DIV -> number1 / number2
        else -> number1 * number1
    }
}

enum class Operator(val symbol: String, val weight:Int){
    PLUS("+",5),
    MINUS("-",3),
    MULT("×",4),
    DIV("÷",2),
    POWER("^",1),
}