package com.example.sharehub.sharehub_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Card::class], version = 1)
abstract class CardDB : RoomDatabase() {
    abstract fun cardDao(): CardDao
    companion object {
        @Volatile
        private var INSTANCE: CardDB? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CardDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDB::class.java,
                    "card_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(CardDBCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class CardDBCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.cardDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(cardDao: CardDao) {
            cardDao.deleteAll()
            val card = Card(name = "Yar", surname = "Karnizov", father = "Mykolayovich", number = "123456789", cvv = "123")
            cardDao.insert(card)
        }
    }
}