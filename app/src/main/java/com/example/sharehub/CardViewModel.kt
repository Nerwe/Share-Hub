package com.example.sharehub

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sharehub.sharehub_db.Card
import com.example.sharehub.sharehub_db.CardRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardViewModel @Inject constructor(private val repository: CardRepository) : ViewModel() {
    val allCards: LiveData<List<Card>> = repository.allCards.asLiveData()
    fun insert(card: Card) = viewModelScope.launch {
        repository.insert(card)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

/*class CardViewModelFactory(private val repository: CardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
