package com.example.sharehub

import android.app.Application
import com.example.sharehub.sharehub_db.CardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CardModule{
    @Provides
    fun provideCardDao(
        cardDaoApplication: Application
    ): CardDao{
        return (cardDaoApplication as CardApplication).database.cardDao()
    }
}