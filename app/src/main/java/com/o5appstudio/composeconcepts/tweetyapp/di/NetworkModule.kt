package com.o5appstudio.composeconcepts.tweetyapp.di

import com.o5appstudio.composeconcepts.tweetyapp.api.TweetyApi
import com.o5appstudio.composeconcepts.tweetyapp.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesTweetyApi(retrofit: Retrofit) : TweetyApi{
        return retrofit.create(TweetyApi::class.java)
    }
}