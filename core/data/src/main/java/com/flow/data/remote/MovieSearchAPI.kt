package com.flow.data.remote

import com.flow.common.Constants.Companion.NAVER_CLIENT_ID
import com.flow.common.Constants.Companion.NAVER_CLIENT_PW
import com.flow.domain.entity.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieSearchAPI {

    @GET("search/movie.json")
    suspend fun searchMovie(
        @Header("X-Naver-Client-Id") id: String = NAVER_CLIENT_ID,
        @Header("X-Naver-Client-Secret") pw:String = NAVER_CLIENT_PW,
        @Query("query") query: String,
        // 검색 시작 위치
        @Query("start") start: Int,
        // 한 번에 표시할 검색 결과 개수
        @Query("display") display: Int
    ): MovieSearchResponse
}