package com.example.ankiscan.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ankiscan.AnkiScanApplication
import com.example.ankiscan.data.AnkiFields
import com.example.ankiscan.data.DictRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Heres a good reference https://github.com/google-developer-training/basic-android-kotlin-compose-training-mars-photos/blob/coil-starter/app/src/main/java/com/example/marsphotos/ui/screens/MarsViewModel.kt
class DictViewModel(private val dictRepository: DictRepository) : ViewModel() {
    // This allows the uiState to be modified within the class and read outside the class
    private val _uiState = MutableStateFlow(DictUiState())
    val uiState: StateFlow<DictUiState> = _uiState.asStateFlow()

    fun updateSearchWord(newSearchWord: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchWord = newSearchWord
            )
        }
    }

    fun searchForAnkiFields() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    ankiFields = dictRepository.getAnkiFields(uiState.value.searchWord)
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AnkiScanApplication)
                DictViewModel(application.dictRepository)
            }
        }
    }
}

data class DictUiState(
    val searchWord: String = "",
    val ankiFields: AnkiFields? = null
)