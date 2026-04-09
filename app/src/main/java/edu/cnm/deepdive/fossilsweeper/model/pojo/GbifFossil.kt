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
import java.util.List;

/**
 * POJO representing a fossil specimen from the GBIF API response. Contains taxonomic, geographic,
 * temporal, and media information about a fossil occurrence.
 */
public class GbifFossil {

  @Nullable
  @SerializedName("gbifID")
  private String gbifId;

  @Nullable
  @SerializedName("catalogNumber")
  private String catalogNumber;

  @Nullable
  @SerializedName("eventDate")
  private String eventDate;

  @Nullable
  @SerializedName("countryCode")
  private String countryCode;

  @Nullable
  @SerializedName("country")
  private String country;

  @Nullable
  @SerializedName("stateProvince")
  private String stateProvince;

  @Nullable
  @SerializedName("decimalLatitude")
  private Double decimalLatitude;

  @Nullable
  @SerializedName("decimalLongitude")
  private Double decimalLongitude;

  @Nullable
  @SerializedName("earliestAgeOrLowestStage")
  private String earliestAgeOrLowestStage;

  @Nullable
  @SerializedName("scientificName")
  private String scientificName;

  @Nullable
  @SerializedName("kingdom")
  private String kingdom;

  @Nullable
  @SerializedName("phylum")
  private String phylum;

  @Nullable
  @SerializedName("order")
  private String order;

  @Nullable
  @SerializedName("family")
  private String family;

  @Nullable
  @SerializedName("genus")
  private String genus;

  @Nullable
  @SerializedName("species")
  private String species;

  @Nullable
  @SerializedName("genericName")
  private String genericName;

  @Nullable
  @SerializedName("specificEpithet")
  private String specificEpithet;

  @Nullable
  @SerializedName("media")
  private List<GbifMedia> media;

  /**
   * Gets the GBIF identifier for this fossil occurrence.
   *
   * @return GBIF ID, or {@code null} if not available.
   */
  @Nullable
  public String getGbifId() {
    return gbifId;
  }

  /**
   * Sets the GBIF identifier for this fossil occurrence.
   *
   * @param gbifId GBIF ID.
   */
  public void setGbifId(@Nullable String gbifId) {
    this.gbifId = gbifId;
  }

  /**
   * Gets the catalog number from the source institution.
   *
   * @return Catalog number, or {@code null} if not available.
   */
  @Nullable
  public String getCatalogNumber() {
    return catalogNumber;
  }

  /**
   * Sets the catalog number from the source institution.
   *
   * @param catalogNumber Catalog number.
   */
  public void setCatalogNumber(@Nullable String catalogNumber) {
    this.catalogNumber = catalogNumber;
  }

  /**
   * Gets the event date when the specimen was collected or observed.
   *
   * @return Event date, or {@code null} if not available.
   */
  @Nullable
  public String getEventDate() {
    return eventDate;
  }

  /**
   * Sets the event date when the specimen was collected or observed.
   *
   * @param eventDate Event date.
   */
  public void setEventDate(@Nullable String eventDate) {
    this.eventDate = eventDate;
  }

  /**
   * Gets the ISO country code for the location where the specimen was found.
   *
   * @return Country code, or {@code null} if not available.
   */
  @Nullable
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * Sets the ISO country code for the location where the specimen was found.
   *
   * @param countryCode Country code.
   */
  public void setCountryCode(@Nullable String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * Gets the country name where the specimen was found.
   *
   * @return Country name, or {@code null} if not available.
   */
  @Nullable
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country name where the specimen was found.
   *
   * @param country Country name.
   */
  public void setCountry(@Nullable String country) {
    this.country = country;
  }

  /**
   * Gets the state or province where the specimen was found.
   *
   * @return State/province, or {@code null} if not available.
   */
  @Nullable
  public String getStateProvince() {
    return stateProvince;
  }

  /**
   * Sets the state or province where the specimen was found.
   *
   * @param stateProvince State/province.
   */
  public void setStateProvince(@Nullable String stateProvince) {
    this.stateProvince = stateProvince;
  }

  /**
   * Gets the decimal latitude coordinate where the specimen was found.
   *
   * @return Latitude, or {@code null} if not available.
   */
  @Nullable
  public Double getDecimalLatitude() {
    return decimalLatitude;
  }

  /**
   * Sets the decimal latitude coordinate where the specimen was found.
   *
   * @param decimalLatitude Latitude.
   */
  public void setDecimalLatitude(@Nullable Double decimalLatitude) {
    this.decimalLatitude = decimalLatitude;
  }

  /**
   * Gets the decimal longitude coordinate where the specimen was found.
   *
   * @return Longitude, or {@code null} if not available.
   */
  @Nullable
  public Double getDecimalLongitude() {
    return decimalLongitude;
  }

  /**
   * Sets the decimal longitude coordinate where the specimen was found.
   *
   * @param decimalLongitude Longitude.
   */
  public void setDecimalLongitude(@Nullable Double decimalLongitude) {
    this.decimalLongitude = decimalLongitude;
  }

  /**
   * Gets the geological age or stage of this fossil.
   *
   * @return Geological age/stage, or {@code null} if not available.
   */
  @Nullable
  public String getEarliestAgeOrLowestStage() {
    return earliestAgeOrLowestStage;
  }

  /**
   * Sets the geological age or stage of this fossil.
   *
   * @param earliestAgeOrLowestStage Geological age/stage.
   */
  public void setEarliestAgeOrLowestStage(@Nullable String earliestAgeOrLowestStage) {
    this.earliestAgeOrLowestStage = earliestAgeOrLowestStage;
  }

  /**
   * Gets the complete scientific name of the organism.
   *
   * @return Scientific name, or {@code null} if not available.
   */
  @Nullable
  public String getScientificName() {
    return scientificName;
  }

  /**
   * Sets the complete scientific name of the organism.
   *
   * @param scientificName Scientific name.
   */
  public void setScientificName(@Nullable String scientificName) {
    this.scientificName = scientificName;
  }

  /**
   * Gets the taxonomic kingdom.
   *
   * @return Kingdom, or {@code null} if not available.
   */
  @Nullable
  public String getKingdom() {
    return kingdom;
  }

  /**
   * Sets the taxonomic kingdom.
   *
   * @param kingdom Kingdom.
   */
  public void setKingdom(@Nullable String kingdom) {
    this.kingdom = kingdom;
  }

  /**
   * Gets the taxonomic phylum.
   *
   * @return Phylum, or {@code null} if not available.
   */
  @Nullable
  public String getPhylum() {
    return phylum;
  }

  /**
   * Sets the taxonomic phylum.
   *
   * @param phylum Phylum.
   */
  public void setPhylum(@Nullable String phylum) {
    this.phylum = phylum;
  }

  /**
   * Gets the taxonomic order.
   *
   * @return Order, or {@code null} if not available.
   */
  @Nullable
  public String getOrder() {
    return order;
  }

  /**
   * Sets the taxonomic order.
   *
   * @param order Order.
   */
  public void setOrder(@Nullable String order) {
    this.order = order;
  }

  /**
   * Gets the taxonomic family.
   *
   * @return Family, or {@code null} if not available.
   */
  @Nullable
  public String getFamily() {
    return family;
  }

  /**
   * Sets the taxonomic family.
   *
   * @param family Family.
   */
  public void setFamily(@Nullable String family) {
    this.family = family;
  }

  /**
   * Gets the taxonomic genus.
   *
   * @return Genus, or {@code null} if not available.
   */
  @Nullable
  public String getGenus() {
    return genus;
  }

  /**
   * Sets the taxonomic genus.
   *
   * @param genus Genus.
   */
  public void setGenus(@Nullable String genus) {
    this.genus = genus;
  }

  /**
   * Gets the full species name.
   *
   * @return Species, or {@code null} if not available.
   */
  @Nullable
  public String getSpecies() {
    return species;
  }

  /**
   * Sets the full species name.
   *
   * @param species Species.
   */
  public void setSpecies(@Nullable String species) {
    this.species = species;
  }

  /**
   * Gets the generic name (first part of binomial name).
   *
   * @return Generic name, or {@code null} if not available.
   */
  @Nullable
  public String getGenericName() {
    return genericName;
  }

  /**
   * Sets the generic name (first part of binomial name).
   *
   * @param genericName Generic name.
   */
  public void setGenericName(@Nullable String genericName) {
    this.genericName = genericName;
  }

  /**
   * Gets the specific epithet (second part of binomial name).
   *
   * @return Specific epithet, or {@code null} if not available.
   */
  @Nullable
  public String getSpecificEpithet() {
    return specificEpithet;
  }

  /**
   * Sets the specific epithet (second part of binomial name).
   *
   * @param specificEpithet Specific epithet.
   */
  public void setSpecificEpithet(@Nullable String specificEpithet) {
    this.specificEpithet = specificEpithet;
  }

  /**
   * Gets the list of media objects associated with this fossil.
   *
   * @return List of media, or {@code null} if not available.
   */
  @Nullable
  public List<GbifMedia> getMedia() {
    return media;
  }

  /**
   * Sets the list of media objects associated with this fossil.
   *
   * @param media List of media.
   */
  public void setMedia(@Nullable List<GbifMedia> media) {
    this.media = media;
  }

}
