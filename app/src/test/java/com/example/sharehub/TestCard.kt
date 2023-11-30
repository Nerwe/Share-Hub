package com.example.sharehub

import com.example.sharehub.domain.Bank
import com.example.sharehub.domain.BankAddCard
import com.example.sharehub.domain.Card
import com.example.sharehub.domain.EmptyFieldException
import com.example.sharehub.domain.ExistingCardException
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.util.jar.Attributes.Name

class TestCard {
    private lateinit var card: Card
    private lateinit var bank: Bank
    private lateinit var bankAddCard: BankAddCard

    @Before
    fun setup()
    {
        card = Card("Yaroslav", "Karnizov", "Mykolayovich",
            1234556789, 1234)
        bank = Bank()
        bankAddCard = BankAddCard()
    }

    /**
     * Перевіряє виникнення помилки при додаванні вже існуючої картки до банку
     * Результат: з'являється виключення [ExistingCardException]
     */
    @Test
    fun test_InsertCard_AlreadyExists()
    {
        assertEquals(0, bank.getSizeOfBank())
        bankAddCard.addCard(bank, card)
        assertEquals(1, bank.getSizeOfBank())

        try
        {
            bankAddCard.addCard(bank, card)
            fail("Виключення ExistingCardException має з'явитися")
        }
        catch (ex: ExistingCardException)
        {
            println(ex)
        }
    }

    /**
     * Перевіряє виникнення помилки при додаванні картки, за умови, що одне з полів буде пустим
     * Результат: з'являється виключення [EmptyFieldException]
     */
    @Test
    fun test_EmptyCardField()
    {
        val name = ""

        val card = Card(name, "Karnizov", "Mykolayovich",
            1234556789, 1234)

        try
        {
            bankAddCard.addCard(bank, card)
            fail("Виключення EmptyFieldException має з'явитися")
        }
        catch (ex: EmptyFieldException)
        {
            println(ex)
        }
    }
}