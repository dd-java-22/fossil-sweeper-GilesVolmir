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

import edu.cnm.deepdive.fossilsweeper.model.pojo.GbifPageResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit service interface for querying the GBIF (Global Biodiversity Information Facility) API.
 * Provides methods to search for fossil specimens with images.
 */
public interface GbifService {

  /**
   * Searches for fossil specimens with still images from the GBIF database.
   *
   * @param limit Maximum number of results to return (max 300).
   * @param offset Starting position in the result set (for pagination).
   * @return Single emitting a page of fossil results.
   */
  @GET("v1/occurrence/search/?basisOfRecord=FOSSIL_SPECIMEN&mediaType=StillImage")
  Single<GbifPageResponse> searchFossils(
      @Query("limit") int limit,
      @Query("offset") int offset
  );

}
