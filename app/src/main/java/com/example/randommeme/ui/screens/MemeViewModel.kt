package com.example.randommeme.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.randommeme.MemeApplication
import com.example.randommeme.data.MemeRepository
import com.example.randommeme.model.Meme
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface MemeUiState {
    data class Success(val data: List<Meme>) : MemeUiState
    object Error : MemeUiState
    object Loading : MemeUiState
}

class MemeViewModel(
    private val memeRepository: MemeRepository
) : ViewModel() {
    var memeUiState: MemeUiState by mutableStateOf(MemeUiState.Loading)
        private set

    init {
        getMemes()
    }

    fun getMemes() {
        viewModelScope.launch {
            try {
                memeUiState = MemeUiState.Success(memeRepository.getMemes())
                Log.d("MemeViewModel", "getMemes")
            } catch (e: IOException) {
                memeUiState = MemeUiState.Error
                Log.d("MemeViewModel", "getMemes error ${e.message}")
            } catch (e: Exception) {
                memeUiState = MemeUiState.Error
                Log.d("MemeViewModel", "getMemes error ${e.message}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MemeApplication)
                val memeRepository = application.container.memeRepository
                MemeViewModel(memeRepository = memeRepository)
            }
        }
    }
}