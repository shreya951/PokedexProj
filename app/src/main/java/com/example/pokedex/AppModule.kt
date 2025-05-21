package com.example.pokedex

import com.example.pokedex.PokeAPIService
import com.example.pokedex.PokeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://pokeapi.co/api/v2/"
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    //for networking
    fun provideOkHttpClient(): OkHttpClient {
        //okhttp is the underlying http that allows for api requests made by retrofit
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    //singleton means it only makes one instance to conserve set up time
    @Singleton
    //for parsing json
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    //for making api calls
    fun provideRetrofit(
        okHttpClient: OkHttpClient, moshi: Moshi
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory
            .create(moshi))
            .build()
    }

    @Provides
    @Singleton
    //api service is the actual api interface
    fun providePokeApiService(retrofit: Retrofit): PokeAPIService {
        return retrofit.create(PokeAPIService::class.java)
    }

    @Provides
    @Singleton
    //repository is an abstraction layer
    fun providePokeRepository(api: PokeAPIService): PokeRepository {
        return PokeRepository(api)
    }


}
