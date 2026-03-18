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
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import java.util.List;

/**
 * Data Access Object interface for {@link DigSiteGrid} entity. Provides methods for managing game
 * board data and retrieving grids with their associated squares.
 */
@Dao
public interface DigSiteGridDao {

  /**
   * Inserts a new dig site grid into the database, typically when starting a new game.
   *
   * @param digSiteGrid Dig site grid to insert.
   * @return Generated primary key ID.
   */
  @Insert
  long insert(DigSiteGrid digSiteGrid);

  /**
   * Retrieves a single dig site grid by its primary key.
   *
   * @param id Grid ID.
   * @return Dig site grid with the specified ID, or {@code null} if not found.
   */
  @Query("SELECT * FROM dig_site_grid WHERE dig_site_grid_id = :id")
  DigSiteGrid selectById(long id);

  /**
   * Retrieves all dig site grids for a specific player as observable live data.
   *
   * @param playerId Player (user) ID.
   * @return LiveData containing list of grids for the specified player.
   */
  @Query("SELECT * FROM dig_site_grid WHERE player_id = :playerId ORDER BY start_time DESC")
  LiveData<List<DigSiteGrid>> selectByPlayerId(long playerId);

//  /**
//   * Retrieves a dig site grid with all its associated squares using a database view. This is a
//   * transactional query that joins grid and square data.
//   *
//   * @param id Grid ID.
//   * @return LiveData containing the grid with squares POJO.
//   */
//  @Transaction
//  @Query("SELECT * FROM DigSiteGridWithSquares WHERE dig_site_grid_id = :id")
//  LiveData<DigSiteGridWithSquares> selectWithSquares(long id);

  /**
   * Updates the remaining brushes count for a specific dig site grid.
   *
   * @param id Grid ID.
   * @param remainingBrushes New remaining brushes count.
   * @return Number of rows updated (should be 1).
   */
  @Query("UPDATE dig_site_grid SET remaining_brushes = :remainingBrushes WHERE dig_site_grid_id = :id")
  int updateRemainingBrushes(long id, int remainingBrushes);

  /**
   * Deletes a dig site grid from the database. Associated squares will be cascade deleted.
   *
   * @param digSiteGrid Dig site grid to delete.
   * @return Number of rows deleted (should be 1).
   */
  @Delete
  int delete(DigSiteGrid digSiteGrid);

}
