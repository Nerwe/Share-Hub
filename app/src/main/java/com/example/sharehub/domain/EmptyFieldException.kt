package com.example.sharehub.domain

class EmptyFieldException(fieldName: String): Exception("$fieldName cannot be empty.")