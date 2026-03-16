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

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

/**
 * Entity class representing a dig site grid in the game. Contains the game board dimensions and
 * tracks which user is playing.
 */
@Entity(
    tableName = "dig_site_grid",
    indices = {
        @Index(value = "player_id")
    },
    foreignKeys = {
        @ForeignKey(
            entity = UserProfile.class,
            parentColumns = "user_profile_id",
            childColumns = "player_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class DigSiteGrid {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "dig_site_grid_id")
  private long id;

  @ColumnInfo(name = "player_id")
  private long playerId;

  @ColumnInfo(name = "height")
  private int height;

  @ColumnInfo(name = "width")
  private int width;

  @NonNull
  @ColumnInfo(name = "start_time")
  private Instant startTime;

  /**
   * Gets the primary key identifier for this dig site grid.
   *
   * @return Grid ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the primary key identifier for this dig site grid.
   *
   * @param id Grid ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the foreign key reference to the user playing this grid.
   *
   * @return User ID.
   */
  public long getPlayerId() {
    return playerId;
  }

  /**
   * Sets the foreign key reference to the user playing this grid.
   *
   * @param playerId User ID.
   */
  public void setPlayerId(long playerId) {
    this.playerId = playerId;
  }

  /**
   * Gets the height of this grid in squares.
   *
   * @return Grid height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the height of this grid in squares.
   *
   * @param height Grid height.
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Gets the width of this grid in squares.
   *
   * @return Grid width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets the width of this grid in squares.
   *
   * @param width Grid width.
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Gets the timestamp when gameplay started on this grid.
   *
   * @return Start time.
   */
  @NonNull
  public Instant getStartTime() {
    return startTime;
  }

  /**
   * Sets the timestamp when gameplay started on this grid.
   *
   * @param startTime Start time.
   */
  public void setStartTime(@NonNull Instant startTime) {
    this.startTime = startTime;
  }

}
