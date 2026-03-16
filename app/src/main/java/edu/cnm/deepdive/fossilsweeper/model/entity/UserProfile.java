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

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class representing a user profile. Contains user-specific data and game progression
 * information.
 */
@Entity(
    tableName = "user_profile"
)
public class UserProfile {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_profile_id")
  private long id;

  @ColumnInfo(name = "scanner_items")
  private int scannerItems;

  /**
   * Gets the primary key identifier for this user profile.
   *
   * @return User ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the primary key identifier for this user profile.
   *
   * @param id User ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the number of scanner items available to this user.
   *
   * @return Scanner item count.
   */
  public int getScannerItems() {
    return scannerItems;
  }

  /**
   * Sets the number of scanner items available to this user.
   *
   * @param scannerItems Scanner item count.
   */
  public void setScannerItems(int scannerItems) {
    this.scannerItems = scannerItems;
  }

}
