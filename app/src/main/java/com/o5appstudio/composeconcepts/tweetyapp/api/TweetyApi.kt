package com.o5appstudio.composeconcepts.tweetyapp.api

import com.o5appstudio.composeconcepts.tweetyapp.model.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetyApi {

    @GET("/v3/b/6669806bacd3cb34a8567313?meta=false")
    suspend fun getTweetsApiData(@Header("X-JSON-Path") category : String) : Response<List<Tweet>>

    @GET("/v3/b/6669806bacd3cb34a8567313?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories() : Response<List<String>>

}