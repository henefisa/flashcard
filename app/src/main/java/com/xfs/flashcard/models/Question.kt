package com.xfs.flashcard.models

class Question(
    val id: String,
    val question: String,
    val img: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Long
)
