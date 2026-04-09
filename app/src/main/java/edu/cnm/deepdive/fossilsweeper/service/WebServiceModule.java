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
package edu.cnm.deepdive.fossilsweeper.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Hilt module that provides web service dependencies including Retrofit, OkHttpClient, and the
 * GBIF API service.
 */
@Module
@InstallIn(SingletonComponent.class)
public class WebServiceModule {

  /**
   * Provides a configured Gson instance for JSON serialization/deserialization.
   *
   * @return Gson instance.
   */
  @Provides
  @Singleton
  Gson provideGson() {
    return new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
  }

  /**
   * Provides a configured OkHttpClient with logging interceptor.
   *
   * @return OkHttpClient instance.
   */
  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(Level.BODY);
    return new OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build();
  }

  /**
   * Provides a configured Retrofit instance for the GBIF API.
   *
   * @param client OkHttpClient instance.
   * @param gson Gson instance.
   * @return Retrofit instance.
   */
  @Provides
  @Singleton
  Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
    return new Retrofit.Builder()
        .baseUrl("https://api.gbif.org/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build();
  }

  /**
   * Provides the GBIF service interface implementation.
   *
   * @param retrofit Retrofit instance.
   * @return GbifService implementation.
   */
  @Provides
  @Singleton
  GbifService provideGbifService(Retrofit retrofit) {
    return retrofit.create(GbifService.class);
  }

}
