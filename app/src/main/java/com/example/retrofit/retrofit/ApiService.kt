package com.example.retrofit.retrofit

import com.example.retrofit.models.ImageData
import com.example.retrofit.models.ImageData2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?key=21502313-2d21ca91298451ec0ba0582ae")
    fun getCategoryPhoto(
        @Query("q") q: String,
        @Query("image_type") image_type: String
    ): Call<ImageData2>
}