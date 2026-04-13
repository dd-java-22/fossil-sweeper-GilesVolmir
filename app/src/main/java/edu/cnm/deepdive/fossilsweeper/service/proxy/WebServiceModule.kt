/*
 *  Copyright 2026 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package edu.cnm.deepdive.fossilsweeper.service.proxy

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.cnm.deepdive.fossilsweeper.R
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Hilt module that provides web service dependencies including Retrofit, OkHttpClient, and the
 * GBIF API service.
 */
@Module
@InstallIn(SingletonComponent::class)
class WebServiceModule {
    /**
     * Provides a configured Gson instance for JSON serialization/deserialization.
     * 
     * @return Gson instance.
     */
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    /**
     * Provides a configured OkHttpClient with logging interceptor.
     * 
     * @return OkHttpClient instance.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            setLevel(
                HttpLoggingInterceptor.Level.valueOf(
                    context.getString(R.string.log_level).uppercase()
                )
            )
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    /**
     * Provides the GBIF service interface implementation.
     * 
     * @param client OkHttpClient instance.
     * @param gson Gson instance.
     * @return GbifService implementation.
     */
    @Provides
    @Singleton
    fun provideGbifService(client: OkHttpClient, gson: Gson): GbifService {
        return Retrofit.Builder()
            .baseUrl("https://api.gbif.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(GbifService::class.java)
    }
}
