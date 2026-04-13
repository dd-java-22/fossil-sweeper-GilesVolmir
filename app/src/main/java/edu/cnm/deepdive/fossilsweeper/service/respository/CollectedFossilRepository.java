package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.CollectedFossil;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing collected fossil data. Provides asynchronous and observable
 * methods for retrieving, inserting, and updating collected fossils.
 */
public interface CollectedFossilRepository {

  /**
   * Retrieves all collected fossils for a specific user, ordered by collection date (newest
   * first) as observable live data.
   *
   * @param userId User ID.
   * @return LiveData containing list of collected fossils ordered by date.
   */
  LiveData<List<CollectedFossil>> getAllOrderByDateCollectedDesc(long userId);

  /**
   * Retrieves collected fossils for a specific user filtered by favorite status and ordered by
   * collection date (newest first) as observable live data.
   *
   * @param userId User ID.
   * @param favorite Whether to retrieve favorite or non-favorite fossils.
   * @return LiveData containing list of collected fossils matching the favorite status, ordered by
   *     date.
   */
  LiveData<List<CollectedFossil>> getAllByFavoriteStateOrderByDateCollectedDesc(long userId, boolean favorite);

  /**
   * Inserts a new collected fossil into the database asynchronously.
   *
   * @param collectedFossil Collected fossil to insert.
   * @return CompletableFuture containing the generated primary key ID.
   */
  CompletableFuture<Long> insert(CollectedFossil collectedFossil);

  /**
   * Associates a fossil type with a collected fossil asynchronously.
   *
   * @param collectedFossil Collected fossil to update.
   * @param fossil Fossil type to associate.
   * @return CompletableFuture containing {@code true} if update was successful, {@code false}
   *     otherwise.
   */
  CompletableFuture<Boolean> setFossil(CollectedFossil collectedFossil, Fossil fossil);

  /**
   * Updates the favorite status of a collected fossil asynchronously.
   *
   * @param collectedFossil Collected fossil to update.
   * @param favoriteState New favorite status.
   * @return CompletableFuture containing {@code true} if update was successful, {@code false}
   *     otherwise.
   */
  CompletableFuture<Boolean> setFavoriteState(CollectedFossil collectedFossil,
      boolean favoriteState);

  /**
   * Retrieves all collected fossils for a specific user that don't have associated fossil stats
   * yet, asynchronously.
   *
   * @param userId User ID.
   * @return CompletableFuture containing list of collected fossils without fossil stats.
   */
  CompletableFuture<List<CollectedFossil>> getAllWithoutFossil(long userId);
}
