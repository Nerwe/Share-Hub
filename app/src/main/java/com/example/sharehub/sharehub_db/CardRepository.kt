package com.example.sharehub.sharehub_db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepository @Inject constructor(private val cardDao: CardDao) {

    val allCards: Flow<List<Card>> = cardDao.getCardByName()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(card: Card) {
        cardDao.insert(card)
    }

    @WorkerThread
    suspend fun deleteAll(){
        cardDao.deleteAll()
    }
}