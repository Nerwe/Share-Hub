package com.example.sharehub.domain

/**
* Додає картку до банку
* */
class BankAddCard {
    /**
    * Вимагає: банк та дані з не пустими ФІО, номер картки та CVV код картки
    *
    * Дії: додає картку з даними про неї у банк
    *
    * Змінює: банк
    *
    * @param bank об'єкт класу [Bank]
    * @param card об'єкт класу [Card], який додається до банку [bank]
    *
    * @throws EmptyFieldException if [name] or [surname] or [father] == ""
    * @throws EmptyFieldException if [cardNum] or [cardCVV] == null
    * @throws ExistingCardException if [bank] has [card]
    * */
    fun addCard(bank: Bank, card: Card)
    {
        val amount = bank.getSizeOfBank();

        if (card.name.isBlank())
        {
            throw EmptyFieldException("name");
        }
        if (card.surname.isBlank())
        {
            throw EmptyFieldException("surname");
        }
        if (card.father.isBlank())
        {
            throw EmptyFieldException("father");
        }
        if (card.cardNum == null)
        {
            throw EmptyFieldException("card num");
        }
        if (card.cardCVV == null)
        {
            throw EmptyFieldException("card CVV");
        }
        if (bank.getAllCards().any { it.cardNum == card.cardNum }) {
            throw ExistingCardException(card.cardNum.toString())
        }

        bank.getAllCards().add(card)
        // TODO Implementation
        assert(amount + 1 == bank.getSizeOfBank())
    }
}