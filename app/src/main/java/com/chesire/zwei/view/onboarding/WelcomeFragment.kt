package com.chesire.zwei.view.onboarding

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chesire.zwei.BuildConfig
import com.chesire.zwei.R
import com.chesire.zwei.xivapi.XIVApi
import com.chesire.zwei.xivapi.XIVApiService
import com.chesire.zwei.xivapi.adapters.GenderAdapter
import com.chesire.zwei.xivapi.adapters.RaceAdapter
import com.chesire.zwei.xivapi.adapters.StateAdapter
import com.chesire.zwei.xivapi.interceptors.InterceptorOverride
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.fragment_welcome.view.testButton
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.Locale

class WelcomeFragment : Fragment() {
    private lateinit var xivApi: XIVApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createRetrofitInstance()

        return inflater.inflate(R.layout.fragment_welcome, container, false).apply {
            testButton.setOnClickListener {
                launch {
                    val data = xivApi.searchForCharacter("Cheshire Cat", "Phoenix").await()

                    Timber.e("INCOMING DATA")
                    Timber.e("Name: ${data.data}.name")
                    Timber.e("Message: ${data.message}")
                    Timber.e("Found: ${data.data.count()} characters")
                }
            }
        }
    }

    private fun createRetrofitInstance() {
        // This will be moved out into dagger
        val httpClient = OkHttpClient()
            .newBuilder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                addInterceptor(InterceptorOverride("lang", Locale.getDefault().language))
                addInterceptor(InterceptorOverride("key", BuildConfig.XIVApiKey))
            }

        val moshi = Moshi.Builder()
            .add(StateAdapter())
            .add(RaceAdapter())
            .add(GenderAdapter())
            .build()

        xivApi = XIVApi(
            Retrofit.Builder()
                .baseUrl("https://xivapi.com/")
                .client(httpClient.build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(XIVApiService::class.java)
        )
    }

    companion object {
        const val tag = "WelcomeFragment"
        fun newInstance(): WelcomeFragment {
            return WelcomeFragment().apply {
                arguments = Bundle()
            }
        }
    }
}