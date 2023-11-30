package com.example.sharehub.domain

class ExistingCardException(card: String): Exception("$card already exists.")