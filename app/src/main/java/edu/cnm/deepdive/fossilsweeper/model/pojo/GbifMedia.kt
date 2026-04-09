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
 * DTO representing a media object from the GBIF API response. Contains information about images
 * and other media associated with a fossil specimen.
 */
data class GbifMedia (
    @SerializedName("format")
    var format: String,

    @SerializedName("license")
    var license: String? = null,

    @SerializedName("identifier")
    var identifier: String,
)

