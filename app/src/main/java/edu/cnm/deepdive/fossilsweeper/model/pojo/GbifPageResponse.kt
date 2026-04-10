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
 * POJO representing a paginated response from the GBIF API. Contains metadata about pagination and
 * the list of fossil results.
 */
public class GbifPageResponse {

  @SerializedName("offset")
  private int offset;

  @SerializedName("limit")
  private int limit;

  @SerializedName("endOfRecords")
  private boolean endOfRecords;

  @SerializedName("count")
  private int count;

  @Nullable
  @SerializedName("results")
  private List<GbifFossil> results;

  /**
   * Gets the offset of this page in the result set.
   *
   * @return Offset value.
   */
  public int getOffset() {
    return offset;
  }

  /**
   * Sets the offset of this page in the result set.
   *
   * @param offset Offset value.
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }

  /**
   * Gets the maximum number of results in this page.
   *
   * @return Limit value.
   */
  public int getLimit() {
    return limit;
  }

  /**
   * Sets the maximum number of results in this page.
   *
   * @param limit Limit value.
   */
  public void setLimit(int limit) {
    this.limit = limit;
  }

  /**
   * Checks if this is the last page of results.
   *
   * @return {@code true} if no more records are available, {@code false} otherwise.
   */
  public boolean isEndOfRecords() {
    return endOfRecords;
  }

  /**
   * Sets whether this is the last page of results.
   *
   * @param endOfRecords {@code true} if no more records are available.
   */
  public void setEndOfRecords(boolean endOfRecords) {
    this.endOfRecords = endOfRecords;
  }

  /**
   * Gets the total count of records matching the query.
   *
   * @return Total count.
   */
  public int getCount() {
    return count;
  }

  /**
   * Sets the total count of records matching the query.
   *
   * @param count Total count.
   */
  public void setCount(int count) {
    this.count = count;
  }

  /**
   * Gets the list of fossil results in this page.
   *
   * @return List of fossils, or {@code null} if not available.
   */
  @Nullable
  public List<GbifFossil> getResults() {
    return results;
  }

  /**
   * Sets the list of fossil results in this page.
   *
   * @param results List of fossils.
   */
  public void setResults(@Nullable List<GbifFossil> results) {
    this.results = results;
  }

}
