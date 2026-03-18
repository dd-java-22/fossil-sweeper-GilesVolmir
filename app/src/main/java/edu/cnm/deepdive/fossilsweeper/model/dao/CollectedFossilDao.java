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

@Dao
public interface CollectedFossilDao {

  @Insert
  long insert(CollectedFossil collectedFossil);

  @Update
  int update(CollectedFossil collectedFossil);

  @Update
  int updateRange(Collection<CollectedFossil> collectedFossils);

  @Query("SELECT * FROM collected_fossil WHERE collected_fossil_id = :id")
  List<CollectedFossil> getCollectedFossilById(long id);

  @Query("SELECT * FROM collected_fossil WHERE collecting_user = :userId")
  List<CollectedFossil> getAllCollectedFossilsForUser(long userId);

  @Query("SELECT * FROM collected_fossil WHERE collecting_user = :userId AND is_favorite = :isFavorite")
  List<CollectedFossil> getCollectedFossilsByUserAndFavorite(long userId, boolean isFavorite);

  @Query("SELECT COUNT(*) FROM collected_fossil WHERE collecting_user = :userId")
  int getCollectedFossilCountByUser(long userId);

  @Delete
  int delete(CollectedFossil collectedFossil);

//  @Transaction
//  @Query("SELECT * FROM CollectedFossilWithStats WHERE collected_fossil_id = :id")
//  LiveData<CollectedFossilWithStats> getCollectedFossilWithStatsById(long id);

}


