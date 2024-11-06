package com.example.disneypairsample.data

import com.example.disneypairsample.domain.PicsumPhotoItem
import com.example.disneypairsample.util.Result
import kotlinx.coroutines.flow.Flow

interface PicsumRepository {

    suspend fun getImages(): Flow<Result<List<PicsumPhotoItem>>>
}