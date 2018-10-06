package com.chesire.zwei.dagger.modules

import com.chesire.zwei.BuildConfig
import com.chesire.zwei.xivapi.XIVApiService
import com.chesire.zwei.xivapi.adapters.GenderAdapter
import com.chesire.zwei.xivapi.adapters.RaceAdapter
import com.chesire.zwei.xivapi.adapters.StateAdapter
import com.chesire.zwei.xivapi.interceptors.InterceptorOverride
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Locale
import javax.inject.Singleton

@Suppress("unused")
@Module
class ServerModule {
    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .apply {
                addInterceptor(InterceptorOverride("lang", Locale.getDefault().language))
                addInterceptor(InterceptorOverride("key", BuildConfig.XIVApiKey))
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(StateAdapter())
            .add(RaceAdapter())
            .add(GenderAdapter())
            .build()
    }

    @Singleton
    @Provides
    fun providesXIVApiService(httpClient: OkHttpClient, moshi: Moshi): XIVApiService {
        return Retrofit.Builder()
            .baseUrl("https://xivapi.com/")
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(XIVApiService::class.java)
    }
}
