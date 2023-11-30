package com.example.sharehub.sharehub_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class Card(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "father") val father: String,
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "cvv") val cvv: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
