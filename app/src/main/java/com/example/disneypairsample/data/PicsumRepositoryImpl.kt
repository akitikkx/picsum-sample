package com.example.disneypairsample.data

import com.example.disneypairsample.data.models.PicsumPhotoResponseItem
import com.example.disneypairsample.domain.PicsumPhotoItem
import com.example.disneypairsample.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PicsumRepositoryImpl @Inject constructor(
    private val picsumPhotoService: PicsumPhotoService
) : PicsumRepository {

    override suspend fun getImages(): Flow<Result<List<PicsumPhotoItem>>> = flow {
        val result = withContext(Dispatchers.IO) {
            try {
                val picsumResponse = picsumPhotoService.getImageList()
                val imageList = picsumResponse.map { response ->
                    response.toPicsumPhotoItem()
                }
                Result.Success(imageList)

            } catch (e: Exception) {
                Result.Failure(e)
            }
        }
        emit(result)
    }

    fun PicsumPhotoResponseItem.toPicsumPhotoItem(): PicsumPhotoItem {
        return PicsumPhotoItem(
            author = author,
            downloadUrl = download_url,
            height = height,
            id = id,
            url = url,
            width = width
        )
    }
}