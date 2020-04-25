package com.example.jansenapp.data.remote.service

import com.example.jansenapp.data.remote.response.SongsApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface SongsApiService {

    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    fun getSongs(): Single<SongsApiResponse>

}