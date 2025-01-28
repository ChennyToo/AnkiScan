package com.example.ankiscan.ui.screens

import androidx.lifecycle.ViewModel
import com.example.ankiscan.data.DictRepository

class DictViewModel(private val dictRepository: DictRepository) : ViewModel() {

}

data class DictUiState(
    val searchedWord: String = "",

)