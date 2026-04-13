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
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteCoord;
import edu.cnm.deepdive.fossilsweeper.model.type.DigSiteSquareState;
import java.time.Instant;

/**
 * Entity class representing a single square within a dig site grid. Tracks the state of each
 * square and whether it contains a fossil.
 */
@Entity(
    tableName = "dig_site_square",
    indices = {
        @Index(value = "belonging_grid_id"),
        @Index(value = {"belonging_grid_id", "x_coordinate", "y_coordinate"}, unique = true)
    },
    foreignKeys = {
        @ForeignKey(
            entity = DigSiteGrid.class,
            parentColumns = "dig_site_grid_id",
            childColumns = "belonging_grid_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class DigSiteSquare {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "dig_site_square_id")
  private long id;

  @ColumnInfo(name = "belonging_grid_id")
  private long belongingGridId;

  @ColumnInfo(name = "x_coordinate")
  private int xCoordinate;

  @ColumnInfo(name = "y_coordinate")
  private int yCoordinate;

  @NonNull
  @ColumnInfo(name = "has_fossil")
  private boolean hasFossil;

  @ColumnInfo(name = "moore_neighbor_fossils")
  private int mooreNeighborFossils;

  @NonNull
  @ColumnInfo(name = "state")
  private DigSiteSquareState state = DigSiteSquareState.UNTOUCHED;

  @NonNull
  @ColumnInfo(name = "last_modified")
  private Instant lastModified;

  /**
   * Gets the primary key identifier for this square.
   *
   * @return Square ID.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the primary key identifier for this square.
   *
   * @param id Square ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets the foreign key reference to the grid this square belongs to.
   *
   * @return Grid ID.
   */
  public long getBelongingGridId() {
    return belongingGridId;
  }

  /**
   * Sets the foreign key reference to the grid this square belongs to.
   *
   * @param belongingGridId Grid ID.
   */
  public void setBelongingGridId(long belongingGridId) {
    this.belongingGridId = belongingGridId;
  }

  /**
   * Gets the x-coordinate of this square within the grid.
   *
   * @return X coordinate.
   */
  public int getXCoordinate() {
    return xCoordinate;
  }

  /**
   * Sets the x-coordinate of this square within the grid.
   *
   * @param xCoordinate X coordinate.
   */
  public void setXCoordinate(int xCoordinate) {
    this.xCoordinate = xCoordinate;
  }

  /**
   * Gets the y-coordinate of this square within the grid.
   *
   * @return Y coordinate.
   */
  public int getYCoordinate() {
    return yCoordinate;
  }

  /**
   * Sets the y-coordinate of this square within the grid.
   *
   * @param yCoordinate Y coordinate.
   */
  public void setYCoordinate(int yCoordinate) {
    this.yCoordinate = yCoordinate;
  }

  /**
   * Gets whether this square contains a fossil.
   *
   * @return {@code true} if square has fossil, {@code false} otherwise.
   */
  public boolean isHasFossil() {
    return hasFossil;
  }

  /**
   * Sets whether this square contains a fossil.
   *
   * @param hasFossil {@code true} if square has fossil, {@code false} otherwise.
   */
  public void setHasFossil(boolean hasFossil) {
    this.hasFossil = hasFossil;
  }

  /**
   * Gets the count of fossils in the Moore neighborhood (8 surrounding squares).
   *
   * @return Number of neighboring fossils.
   */
  public int getMooreNeighborFossils() {
    return mooreNeighborFossils;
  }

  /**
   * Sets the count of fossils in the Moore neighborhood (8 surrounding squares).
   *
   * @param mooreNeighborFossils Number of neighboring fossils.
   */
  public void setMooreNeighborFossils(int mooreNeighborFossils) {
    this.mooreNeighborFossils = mooreNeighborFossils;
  }

  /**
   * Gets the current state of this square.
   *
   * @return Square state.
   */
  @NonNull
  public DigSiteSquareState getState() {
    return state;
  }

  /**
   * Sets the current state of this square.
   *
   * @param state Square state.
   */
  public void setState(@NonNull DigSiteSquareState state) {
    this.state = state;
  }

  /**
   * Gets the timestamp when this square was last modified.
   *
   * @return Last modified time.
   */
  @NonNull
  public Instant getLastModified() {
    return lastModified;
  }

  /**
   * Sets the timestamp when this square was last modified.
   *
   * @param lastModified Last modified time.
   */
  public void setLastModified(@NonNull Instant lastModified) {
    this.lastModified = lastModified;
  }

  /**
   * Gets the coordinate position of this square as a DigSiteCoord object.
   *
   * @return Coordinate object containing x and y positions.
   */
  public DigSiteCoord getCoord() {
    return new DigSiteCoord(xCoordinate, yCoordinate);
  }

}
