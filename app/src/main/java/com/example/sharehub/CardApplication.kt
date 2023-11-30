package com.example.sharehub

import android.app.Application
import com.example.sharehub.sharehub_db.CardDB
import com.example.sharehub.sharehub_db.CardRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class CardApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CardDB.getDatabase(this, applicationScope) }
    val repository by lazy { CardRepository(database.cardDao()) }
}