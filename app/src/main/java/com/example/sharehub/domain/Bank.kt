package com.example.sharehub.domain

/**
* Клас, що представляє собою банк, який містить список карток та дії з ними
* */
class Bank {
    private var bank = mutableListOf<Card>()
    /**
    * Дії: повертає усі картки
    * */
    fun getAllCards(): MutableList<Card>
    {
        return bank;
    }
    /**
    * Дії: повертає кількість карток у банку
    * */
    fun getSizeOfBank(): Int
    {
        return bank.size;
    }
}