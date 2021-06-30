package com.xfs.flashcard.models

data class Word(
    val id: String,
    val value: String,
    val mean: String,
    val spell: String,
    val image: String,
    val examples: String,
) {

}