package project.penadidik.geocoding.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import project.penadidik.geocoding.data.remote.api.GeoCodingApi
import project.penadidik.geocoding.data.remote.builder.RetrofitBuilder
import project.penadidik.geocoding.data.remote.interceptor.HeaderInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(retrofitBuilder: RetrofitBuilder, headerInterceptor: HeaderInterceptor): Retrofit = retrofitBuilder
        .addInterceptors(headerInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideGeoCodingApi(retrofit: Retrofit): GeoCodingApi = retrofit.create(GeoCodingApi::class.java)
}