package edu.cnm.deepdive.fossilsweeper.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.fossilsweeper.model.entity.CollectedFossil;
import java.util.Collection;
import java.util.List;

/**
 * Data Access Object interface for {@link CollectedFossil} entity. Provides methods for managing
 * fossils collected by users, including filtering by favorites and tracking collection statistics.
 */
@Dao
public interface CollectedFossilDao {

  /**
   * Inserts a new collected fossil into the database.
   *
   * @param collectedFossil Collected fossil to insert.
   * @return Generated primary key ID.
   */
  @Insert
  long insert(CollectedFossil collectedFossil);

  /**
   * Updates an existing collected fossil record.
   *
   * @param collectedFossil Collected fossil to update.
   * @return Number of rows updated (should be 1).
   */
  @Update
  int update(CollectedFossil collectedFossil);

  /**
   * Updates multiple collected fossils in a batch operation.
   *
   * @param collectedFossils Collection of collected fossils to update.
   * @return Number of rows updated.
   */
  @Update
  int updateRange(Collection<CollectedFossil> collectedFossils);

  /**
   * Retrieves collected fossils by their primary key ID.
   *
   * @param id Collected fossil ID.
   * @return List of collected fossils with the specified ID.
   */
  @Query("SELECT * FROM collected_fossil WHERE collected_fossil_id = :id")
  List<CollectedFossil> getCollectedFossilById(long id);

  /**
   * Retrieves all collected fossils for a specific user.
   *
   * @param userId User ID.
   * @return List of all collected fossils for the specified user.
   */
  @Query("SELECT * FROM collected_fossil WHERE collecting_user = :userId")
  List<CollectedFossil> getAllCollectedFossilsForUser(long userId);

  /**
   * Retrieves all collected fossils for a specific user ordered by collection date (newest first)
   * as observable live data.
   *
   * @param userId User ID.
   * @return LiveData containing list of collected fossils ordered by date.
   */
  @Query("SELECT * FROM collected_fossil WHERE collecting_user = :userId ORDER BY date_time_collected DESC")
  LiveData<List<CollectedFossil>> getAllCollectedFossilsForUserOrderByDate(long userId);

  /**
   * Retrieves collected fossils for a specific user filtered by favorite status.
   *
   * @param userId User ID.
   * @param isFavorite Whether to retrieve favorite or non-favorite fossils.
   * @return List of collected fossils matching the favorite status.
   */
  @Query("SELECT * FROM collected_fossil WHERE collecting_user = :userId AND is_favorite = :isFavorite")
  List<CollectedFossil> getCollectedFossilsByUserAndFavorite(long userId, boolean isFavorite);

  /**
   * Retrieves collected fossils for a specific user filtered by favorite status and ordered by
   * collection date (newest first) as observable live data.
   *
   * @param userId User ID.
   * @param isFavorite Whether to retrieve favorite or non-favorite fossils.
   * @return LiveData containing list of collected fossils matching the favorite status, ordered by
   *     date.
   */
  @Query("SELECT * FROM collected_fossil WHERE collecting_user = :userId AND is_favorite = :isFavorite ORDER BY date_time_collected DESC")
  LiveData<List<CollectedFossil>> getCollectedFossilsByUserAndFavoriteOrderByDate(long userId, boolean isFavorite);

  /**
   * Retrieves all collected fossils for a specific user that don't have associated fossil stats
   * yet.
   *
   * @param userId User ID.
   * @return List of collected fossils without fossil stats.
   */
  @Query("SELECT * FROM collected_fossil WHERE collecting_user = :userId AND fossil_stats_id IS NULL")
  List<CollectedFossil> getAllWithoutFossilForUser(long userId);

  /**
   * Retrieves the total count of collected fossils for a specific user.
   *
   * @param userId User ID.
   * @return Total number of fossils collected by the user.
   */
  @Query("SELECT COUNT(*) FROM collected_fossil WHERE collecting_user = :userId")
  int getCollectedFossilCountByUser(long userId);

  /**
   * Deletes a collected fossil from the database.
   *
   * @param collectedFossil Collected fossil to delete.
   * @return Number of rows deleted (should be 1).
   */
  @Delete
  int delete(CollectedFossil collectedFossil);

//  @Transaction
//  @Query("SELECT * FROM CollectedFossilWithStats WHERE collected_fossil_id = :id")
//  LiveData<CollectedFossilWithStats> getCollectedFossilWithStatsById(long id);

}


