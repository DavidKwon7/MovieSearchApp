package com.flow.data.remote

import com.flow.common.Constants.Companion.NAVER_CLIENT_ID
import com.flow.common.Constants.Companion.NAVER_CLIENT_PW
import com.flow.data.model.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieSearchAPI {

    @GET("search/movie.json")
    suspend fun getMovie(
        @Header("X-Naver-Client-Id") id: String = NAVER_CLIENT_ID,
        @Header("X-Naver-Client-Secret") pw:String = NAVER_CLIENT_PW,
        @Query("query") query: String
    ): Response<MovieSearchResponse>
}