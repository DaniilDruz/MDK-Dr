package ru.netology.nmedia.dto

import java.time.temporal.TemporalAmount

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    var amountlike: Int,
    var sharecount: Int,
    var link: String,
)

