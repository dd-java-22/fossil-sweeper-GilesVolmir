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
package edu.cnm.deepdive.fossilsweeper.model.pojo

import com.google.gson.annotations.SerializedName

/**
 * POJO representing a paginated response from the GBIF API. Contains metadata about pagination and
 * the list of fossil results.
 *
 * @property offset The starting position in the result set (for pagination).
 * @property limit The maximum number of results in this page.
 * @property isEndOfRecords Whether this is the last page of results.
 * @property count The total number of matching records available.
 * @property results The list of fossil specimens in this page, or null if no results.
 */
data class GbifPageResponse(

    @SerializedName("offset")
    var offset: Int = 0,

    @SerializedName("limit")
    var limit: Int = 0,

    @SerializedName("endOfRecords")
    var isEndOfRecords: Boolean = false,

    @SerializedName("count")
    var count: Int = 0,

    @SerializedName("results")
    var results: MutableList<GbifFossil?>? = null,
)
