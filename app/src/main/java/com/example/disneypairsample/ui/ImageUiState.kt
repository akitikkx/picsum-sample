package com.example.disneypairsample.ui

import com.example.disneypairsample.domain.PicsumPhotoItem

sealed interface ImageUiState {
    object Loading: ImageUiState
    data class Success(val images: List<PicsumPhotoItem>): ImageUiState
    data class Error(val message: String): ImageUiState
}