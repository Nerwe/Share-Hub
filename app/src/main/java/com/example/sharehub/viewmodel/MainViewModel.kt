package com.example.sharehub.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sharehub.domain.Bank
import com.example.sharehub.domain.BankAddCard
import com.example.sharehub.domain.Card
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val bank = Bank()

    val nameDt = MutableLiveData<String>("")
    val surnameDt = MutableLiveData<String>("")
    val fatherDt = MutableLiveData<String>("")
    val cardsNumDt = MutableLiveData<String>("Num of cards: 0")

    @SuppressLint("SetTextI18n")
    fun onClickAddCard(){
        val name = nameDt.value.toString()
        val surname = surnameDt.value.toString()
        val father = fatherDt.value.toString()
        val cardNum = Random.nextInt(100000000,1000000000)
        val cardCVV = Random.nextInt(100,1000)

        val card = Card(name, surname, father, cardNum, cardCVV)
        val add = BankAddCard()
        add.addCard(bank, card)
        cardsNumDt.value = "Num of cards: " + bank.getSizeOfBank().toString()
    }
}