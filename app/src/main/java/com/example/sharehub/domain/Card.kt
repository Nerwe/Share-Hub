package com.example.sharehub.domain

/**
* Клас, що представляє собою банківську картку зі всією інформацією про неї
* */
data class Card (var name: String, var surname: String, var father: String, var cardNum: Int, var cardCVV: Int)