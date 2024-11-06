package com.example.disneypairsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneypairsample.data.PicsumRepository
import com.example.disneypairsample.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    picsumRepository: PicsumRepository
) : ViewModel() {

    val uiState: StateFlow<ImageUiState> = flow {
        emit(picsumRepository.getImages())
    }
        .transformLatest { responseFlow ->
            responseFlow.map { result ->
                when (result) {
                    is Result.Loading -> ImageUiState.Loading
                    is Result.Success -> ImageUiState.Success(result.data)
                    is Result.Failure -> ImageUiState.Error(
                        "Failed to fetch the images: {${result.exception.message}}"
                    )
                }
            }
                .catch { exception ->
                    emit(ImageUiState.Error("An error occured: ${exception.message}"))
                }
                .collect { emit(it) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ImageUiState.Loading
        )
}