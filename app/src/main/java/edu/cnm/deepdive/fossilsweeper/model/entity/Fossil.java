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
package edu.cnm.deepdive.fossilsweeper.model.entity;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entity class representing a fossil in the database. Contains reference information about fossil
 * types available in the game.
 */
@Entity(
    tableName = "fossil",
    indices = {
        @Index(value = "origin_key", unique = true)
    }
)
public class Fossil {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "fossil_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "image_uri")
  private Uri imageUri;

  @Nullable
  @ColumnInfo(name = "latin_name")
  private String latinName;

  @Nullable
  @ColumnInfo(name = "geological_era")
  private String geologicalEra;

  @NonNull
  @ColumnInfo(name = "origin_key")
  private long originKey;

  /**
   * Gets the primary key identifier for this fossil.
   *
   * @return Fossil ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the primary key identifier for this fossil.
   *
   * @param id Fossil ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the URI of the image resource for this fossil.
   *
   * @return Image URI.
   */
  @NonNull
  public Uri getImageUri() {
    return imageUri;
  }

  /**
   * Sets the URI of the image resource for this fossil.
   *
   * @param imageUri Image URI.
   */
  public void setImageUri(@NonNull Uri imageUri) {
    this.imageUri = imageUri;
  }

  /**
   * Gets the scientific latin name of this fossil.
   *
   * @return Latin name, or {@code null} if not available.
   */
  @Nullable
  public String getLatinName() {
    return latinName;
  }

  /**
   * Sets the scientific latin name of this fossil.
   *
   * @param latinName Latin name.
   */
  public void setLatinName(@Nullable String latinName) {
    this.latinName = latinName;
  }

  /**
   * Gets the geological era this fossil is from.
   *
   * @return Geological era, or {@code null} if not available.
   */
  @Nullable
  public String getGeologicalEra() {
    return geologicalEra;
  }

  /**
   * Sets the geological era this fossil is from.
   *
   * @param geologicalEra Geological era.
   */
  public void setGeologicalEra(@Nullable String geologicalEra) {
    this.geologicalEra = geologicalEra;
  }

  /**
   * Gets the lookup key for the originating database.
   *
   * @return Origin key.
   */
  public long getOriginKey() {
    return originKey;
  }

  /**
   * Sets the lookup key for the originating database.
   *
   * @param originKey Origin key.
   */
  public void setOriginKey(long originKey) {
    this.originKey = originKey;
  }

}
