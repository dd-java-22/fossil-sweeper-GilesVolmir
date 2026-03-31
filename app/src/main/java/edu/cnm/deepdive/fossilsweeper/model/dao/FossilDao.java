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
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object interface for {@link Fossil} entity. Provides methods for creating, reading,
 * and deleting fossil reference data.
 */
@Dao
public interface FossilDao {

  /**
   * Inserts a batch of fossils into the database, typically from a webservice call.
   *
   * @param fossils Collection of fossils to insert.
   * @return List of generated primary key IDs.
   */
  @Insert
  List<Long> insert(Collection<Fossil> fossils);

  /**
   * Retrieves all fossils from the database as observable live data.
   *
   * @return LiveData containing list of all fossils.
   */
  @Query("SELECT * FROM fossil ORDER BY latin_name")
  LiveData<List<Fossil>> selectAll();
  // TODO: 3/16/2026 Review Claude AI's choices: is this needed? probably just wrap in collected_fossil.

  /**
   * Retrieves a single fossil by its primary key.
   *
   * @param id Fossil ID.
   * @return Fossil with the specified ID, or {@code null} if not found.
   */
  @Query("SELECT * FROM fossil WHERE fossil_id = :id")
  Fossil selectById(long id);


  /**
   * Retrieves a list of unassigned fossils (not referenced by any CollectedFossil).
   *
   * @param limit Maximum number of fossils to return.
   * @return List of unassigned fossils.
   */
  @Query("SELECT f.* FROM fossil f "
      + "LEFT JOIN collected_fossil cf ON f.fossil_id = cf.fossil_stats_id "
      + "WHERE cf.fossil_stats_id IS NULL "
      + "ORDER BY RANDOM() LIMIT :limit")
  List<Fossil> selectRandomUnassigned(int limit);

  /**
   * Deletes a fossil from the database.
   *
   * @param fossil Fossil to delete.
   * @return Number of rows deleted (should be 1).
   */
  @Delete
  int delete(Fossil fossil);
  // TODO: 3/16/2026 review...do I even need this? should it be by ID instead?

}
