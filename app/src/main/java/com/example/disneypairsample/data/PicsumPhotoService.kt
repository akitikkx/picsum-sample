package com.example.disneypairsample.data

import com.example.disneypairsample.data.models.PicsumPhotoResponse
import retrofit2.http.GET

interface PicsumPhotoService {

    @GET("list")
    suspend fun getImageList(): PicsumPhotoResponse
}