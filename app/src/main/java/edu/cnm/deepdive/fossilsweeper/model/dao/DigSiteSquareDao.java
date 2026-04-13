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
package edu.cnm.deepdive.fossilsweeper.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object interface for {@link DigSiteSquare} entity. Provides methods for managing
 * individual squares on the game board, including batch operations for game initialization and
 * state updates.
 */
@Dao
public interface DigSiteSquareDao {

  /**
   * Inserts a batch of dig site squares into the database, typically when populating a new game
   * board.
   *
   * @param digSiteSquares Collection of squares to insert.
   * @return List of generated primary key IDs.
   */
  @Insert
  List<Long> insertRaw(Collection<DigSiteSquare> digSiteSquares);

  default List<Long> insert(Collection<DigSiteSquare> digSiteSquares) {
    Instant now = Instant.now();
    digSiteSquares.forEach((square) -> square.setLastModified(now));
    return insertRaw(digSiteSquares);
  }



  /**
   * Retrieves all dig site squares for a specific grid as observable live data.
   *
   * @param gridId Grid ID.
   * @return LiveData containing list of all squares in the specified grid.
   */
  @Query("SELECT * FROM dig_site_square WHERE belonging_grid_id = :gridId ORDER BY y_coordinate, x_coordinate")
  LiveData<List<DigSiteSquare>> selectByGridId(long gridId);

  /**
   * Retrieves a single dig site square by its primary key.
   *
   * @param id Square ID.
   * @return Dig site square with the specified ID, or {@code null} if not found.
   */
  @Query("SELECT * FROM dig_site_square WHERE dig_site_square_id = :id")
  DigSiteSquare selectById(long id);

  /**
   * Retrieves a single dig site square by its coordinates within a specific grid.
   *
   * @param gridId Grid ID.
   * @param x X coordinate.
   * @param y Y coordinate.
   * @return Dig site square at the specified coordinates, or {@code null} if not found.
   */
  @Query("SELECT * FROM dig_site_square WHERE belonging_grid_id = :gridId AND x_coordinate = :x AND y_coordinate = :y")
  DigSiteSquare selectByCoordinates(long gridId, int x, int y);

  // TODO: 3/16/2026 query moore neighborhood...? or is that a service ting?
  //  or just a query by x range, y range, and digsite? and zero neighbors field?

  /**
   * Updates a single dig site square, typically used when a player interacts with a square on the
   * board.
   *
   * @param digSiteSquare Dig site square to update.
   * @return Number of rows updated (should be 1).
   */
  @Update
  int updateRaw(DigSiteSquare digSiteSquare);

  default int update(DigSiteSquare digSiteSquare) {
    digSiteSquare.setLastModified(Instant.now());
    return updateRaw(digSiteSquare);
  }

  /**
   * Updates multiple dig site squares in a batch operation.
   * Useful for cascading reveals.
   * @param digSiteSquares Collection of squares to update.
   * @return Number of rows updated.
   */
  @Update
  int updateRaw(Collection<DigSiteSquare> digSiteSquares);

  default int update(Collection<DigSiteSquare> digSiteSquares) {
    Instant now = Instant.now();
    digSiteSquares.forEach((square) -> square.setLastModified(now));
    return updateRaw(digSiteSquares);
  }
}
