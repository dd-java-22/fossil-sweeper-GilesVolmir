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
import androidx.room.Update;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
import java.util.List;

/**
 * Data Access Object interface for {@link UserProfile} entity. Provides methods for managing user
 * profile data including scanner item inventory.
 */
@Dao
public interface UserProfileDao {

  /**
   * Inserts a new user profile into the database.
   *
   * @param userProfile User profile to insert.
   * @return Generated primary key ID.
   */
  @Insert
  long insert(UserProfile userProfile);

  /**
   * Retrieves all user profiles from the database as observable live data.
   *
   * @return LiveData containing list of all user profiles.
   */
  @Query("SELECT * FROM user_profile ORDER BY user_profile_id")
  LiveData<List<UserProfile>> selectAll();
  // TODO: 3/16/2026 review claude's decisions. is this useful? er,,, maybe if I have a pulldown of users? I'm not even sure I want more than 1....

  /**
   * Retrieves a single user profile by its primary key as observable live data.
   *
   * @param id User profile ID.
   * @return LiveData containing the user profile with the specified ID.
   */
  @Query("SELECT * FROM user_profile WHERE user_profile_id = :id")
  LiveData<UserProfile> selectById(long id);
  // TODO: 3/16/2026 review claude's decisions. Is this what I need for displaying the number of scanners in a UI? yeah, probably.

  /**
   * Updates an existing user profile, typically used for scanner item inventory changes.
   *
   * @param userProfile User profile to update.
   * @return Number of rows updated (should be 1).
   */
  @Update
  int update(UserProfile userProfile);
  // Could/Should I make increment/decrement methods for scanner items? by user?

  /**
   * Deletes a user profile from the database.
   *
   * @param userProfile User profile to delete.
   * @return Number of rows deleted (should be 1).
   */
  @Delete
  int delete(UserProfile userProfile);

}
