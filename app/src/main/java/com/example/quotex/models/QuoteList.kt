package com.example.quotex.models

data class QuoteList(
    val limit: Int,
    val quotes: List<Quote>,
    val skip: Int,
    val total: Int
)