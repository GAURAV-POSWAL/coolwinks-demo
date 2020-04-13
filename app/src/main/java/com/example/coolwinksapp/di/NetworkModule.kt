package com.example.coolwinksapp.di

import com.example.coolwinksapp.network.CoolApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// @Module informs Dagger that this class is a Dagger Module
@Module
class NetworkModule {

    // @Provides tell Dagger how to create instances of the type that this function
    // returns (i.e. LoginRetrofitService).
    // Function parameters are the dependencies of this type.
    @Singleton
    @Provides
    fun httpInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttp(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        return httpClient.build()

    }

    @Singleton
    @Provides
    fun gsonConvertor(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun provideCoolService(
        okHttpClient: OkHttpClient,
        gsonConvertor: GsonConverterFactory
    ): CoolApiService {
        // Whenever Dagger needs to provide an instance of type LoginRetrofitService,
        // this code (the one inside the @Provides method) is run.
        return Retrofit.Builder()
            .baseUrl(CoolApiService.BASE_URL)
            .addConverterFactory(gsonConvertor)
            .client(okHttpClient)
            .build()
            .create(CoolApiService::class.java)
    }


}
