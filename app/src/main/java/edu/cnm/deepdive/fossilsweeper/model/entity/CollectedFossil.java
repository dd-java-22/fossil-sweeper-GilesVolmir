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
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Instant;

/**
 * Entity class representing a fossil collected by a user. Links users to the fossils they have
 * found during gameplay.
 */
@Entity(
    tableName = "collected_fossil",
    indices = {
        @Index(value = "fossil_stats_id"),
        @Index(value = "collecting_user")
    },
    foreignKeys = {
        @ForeignKey(
            entity = Fossil.class,
            parentColumns = "fossil_id",
            childColumns = "fossil_stats_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = UserProfile.class,
            parentColumns = "user_id",
            childColumns = "collecting_user",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class CollectedFossil {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "collected_fossil_id")
  private long id;

  @Nullable
  @ColumnInfo(name = "fossil_stats_id")
  private Long fossilStatsId;

  @ColumnInfo(name = "collecting_user")
  private long collectingUser;

  @NonNull
  @ColumnInfo(name = "is_favorite")
  private boolean isFavorite;

  @NonNull
  @ColumnInfo(name = "date_time_collected")
  private Instant dateTimeCollected;

  /**
   * Gets the primary key identifier for this collected fossil.
   *
   * @return Collected fossil ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the primary key identifier for this collected fossil.
   *
   * @param id Collected fossil ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the foreign key reference to the fossil type.
   *
   * @return Fossil ID, or {@code null} if not yet determined.
   */
  @Nullable
  public Long getFossilStatsId() {
    return fossilStatsId;
  }

  /**
   * Sets the foreign key reference to the fossil type.
   *
   * @param fossilStatsId Fossil ID.
   */
  public void setFossilStatsId(@Nullable Long fossilStatsId) {
    this.fossilStatsId = fossilStatsId;
  }

  /**
   * Gets the foreign key reference to the user who collected this fossil.
   *
   * @return User ID.
   */
  public long getCollectingUser() {
    return collectingUser;
  }

  /**
   * Sets the foreign key reference to the user who collected this fossil.
   *
   * @param collectingUser User ID.
   */
  public void setCollectingUser(long collectingUser) {
    this.collectingUser = collectingUser;
  }

  /**
   * Gets whether this fossil is marked as a favorite by the user.
   *
   * @return {@code true} if favorite, {@code false} otherwise.
   */
  public boolean isFavorite() {
    return isFavorite;
  }

  /**
   * Sets whether this fossil is marked as a favorite by the user.
   *
   * @param favorite {@code true} if favorite, {@code false} otherwise.
   */
  public void setFavorite(boolean favorite) {
    isFavorite = favorite;
  }

  /**
   * Gets the timestamp when this fossil was collected.
   *
   * @return Date and time collected.
   */
  @NonNull
  public Instant getDateTimeCollected() {
    return dateTimeCollected;
  }

  /**
   * Sets the timestamp when this fossil was collected.
   *
   * @param dateTimeCollected Date and time collected.
   */
  public void setDateTimeCollected(@NonNull Instant dateTimeCollected) {
    this.dateTimeCollected = dateTimeCollected;
  }

}
