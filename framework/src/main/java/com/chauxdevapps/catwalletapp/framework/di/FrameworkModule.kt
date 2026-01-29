package com.chauxdevapps.catwalletapp.framework.di

import com.chauxdevapps.catwalletapp.data.source.CatRemoteDataSource
import com.chauxdevapps.catwalletapp.framework.image.GlideImageManager
import com.chauxdevapps.catwalletapp.framework.image.ImageManager
import com.chauxdevapps.catwalletapp.framework.request.ApiConstants
import com.chauxdevapps.catwalletapp.framework.request.CatApi
import com.chauxdevapps.catwalletapp.framework.request.RetrofitCatRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FrameworkModule {

    @Binds
    @Singleton
    abstract fun bindImageManager(glideImageManager: GlideImageManager): ImageManager

    @Binds
    @Singleton
    abstract fun bindCatRemoteDataSource(retrofitCatRemoteDataSource: RetrofitCatRemoteDataSource): CatRemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideCatApi(retrofit: Retrofit): CatApi {
            return retrofit.create(CatApi::class.java)
        }
    }
}
