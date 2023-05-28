package com.swu.hyperventilationsyndrome.model


data class Question(
    val type: Int,
    val question: String,
    val choice: List<String>
)
