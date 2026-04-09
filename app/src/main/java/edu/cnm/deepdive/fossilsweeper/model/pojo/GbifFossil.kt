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

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * POJO representing a fossil specimen from the GBIF API response. Contains taxonomic, geographic,
 * temporal, and media information about a fossil occurrence.
 */
data class GbifFossil (

    @Expose
    @SerializedName("gbifID")
    var gbifId: Long,

    @Expose
    @SerializedName("catalogNumber")
    var catalogNumber: String?,

    @Expose
    @SerializedName("eventDate")
    var eventDate: String?,

    @Expose
    @SerializedName("countryCode")
    var countryCode: String?,

    @Expose
    @SerializedName("country")
    var country: String?,

    @Expose
    @SerializedName("stateProvince")
    var stateProvince: String?,

    @Expose
    @SerializedName("decimalLatitude")
    var decimalLatitude: Double?,

    @Expose
    @SerializedName("decimalLongitude")
    var decimalLongitude: Double?,

    @Expose
    @SerializedName("earliestAgeOrLowestStage")
    var earliestAgeOrLowestStage: String?,

    @Expose
    @SerializedName("scientificName")
    var scientificName: String?,

    @Expose
    @SerializedName("kingdom")
    var kingdom: String?,

    @Expose
    @SerializedName("phylum")
    var phylum: String?,

    @Expose
    @SerializedName("order")
    var order: String?,

    @Expose
    @SerializedName("family")
    var family: String?,

    @Expose
    @SerializedName("genus")
    var genus: String?,

    @Expose
    @SerializedName("species")
    var species: String?,

    @Expose
    @SerializedName("genericName")
    var genericName: String?,

    @Expose
    @SerializedName("specificEpithet")
    var specificEpithet: String?,

    @Expose
    @SerializedName("media")
    var media: List<GbifMedia>,
)
