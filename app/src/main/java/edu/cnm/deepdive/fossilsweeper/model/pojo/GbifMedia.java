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
package edu.cnm.deepdive.fossilsweeper.model.pojo;

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;

/**
 * POJO representing a media object from the GBIF API response. Contains information about images
 * and other media associated with a fossil specimen.
 */
public class GbifMedia {

  @Nullable
  @SerializedName("type")
  private String type;

  @Nullable
  @SerializedName("format")
  private String format;

  @Nullable
  @SerializedName("license")
  private String license;

  @Nullable
  @SerializedName("identifier")
  private String identifier;

  @Nullable
  @SerializedName("references")
  private String references;

  /**
   * Gets the media type (e.g., "StillImage").
   *
   * @return Media type, or {@code null} if not available.
   */
  @Nullable
  public String getType() {
    return type;
  }

  /**
   * Sets the media type.
   *
   * @param type Media type.
   */
  public void setType(@Nullable String type) {
    this.type = type;
  }

  /**
   * Gets the media format (e.g., "image/jpeg").
   *
   * @return Media format, or {@code null} if not available.
   */
  @Nullable
  public String getFormat() {
    return format;
  }

  /**
   * Sets the media format.
   *
   * @param format Media format.
   */
  public void setFormat(@Nullable String format) {
    this.format = format;
  }

  /**
   * Gets the license URL for this media.
   *
   * @return License URL, or {@code null} if not available.
   */
  @Nullable
  public String getLicense() {
    return license;
  }

  /**
   * Sets the license URL for this media.
   *
   * @param license License URL.
   */
  public void setLicense(@Nullable String license) {
    this.license = license;
  }

  /**
   * Gets the identifier (URL) for this media resource.
   *
   * @return Media identifier URL, or {@code null} if not available.
   */
  @Nullable
  public String getIdentifier() {
    return identifier;
  }

  /**
   * Sets the identifier (URL) for this media resource.
   *
   * @param identifier Media identifier URL.
   */
  public void setIdentifier(@Nullable String identifier) {
    this.identifier = identifier;
  }

  /**
   * Gets the reference URL for this media.
   *
   * @return Reference URL, or {@code null} if not available.
   */
  @Nullable
  public String getReferences() {
    return references;
  }

  /**
   * Sets the reference URL for this media.
   *
   * @param references Reference URL.
   */
  public void setReferences(@Nullable String references) {
    this.references = references;
  }

}
