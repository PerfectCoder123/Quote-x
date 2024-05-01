package com.example.quotex.viewmodels

import androidx.lifecycle.ViewModel
import com.example.quotex.repository.QuoteRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quotex.models.QuoteList
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    init{
        viewModelScope.launch {
            repository.getQuotes()
        }
    }

    val quotes : LiveData<QuoteList>
    get() = repository.quotes

}