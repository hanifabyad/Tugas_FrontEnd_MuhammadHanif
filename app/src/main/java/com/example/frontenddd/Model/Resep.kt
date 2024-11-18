package com.example.frontenddd.Model

data class Resep (
    val id: Int,
    val nama : String,
    val bahan : String,
    val langkah : String,
    val foto : Int? = null
)