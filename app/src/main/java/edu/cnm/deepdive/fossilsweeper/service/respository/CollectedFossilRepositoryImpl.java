package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.dao.CollectedFossilDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.CollectedFossil;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import jakarta.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of {@link CollectedFossilRepository} that delegates to the corresponding DAO for
 * database operations.
 */
public class CollectedFossilRepositoryImpl implements CollectedFossilRepository {

  private final CollectedFossilDao collectedFossilDao;

  /**
   * Constructs a CollectedFossilRepositoryImpl with the specified DAO.
   *
   * @param collectedFossilDao DAO for database operations.
   */
  @Inject
  CollectedFossilRepositoryImpl(CollectedFossilDao collectedFossilDao) {
    this.collectedFossilDao = collectedFossilDao;
  }

  @Override
  public LiveData<List<CollectedFossil>> getAllOrderByDateCollectedDesc(long userId) {
    return collectedFossilDao.getAllCollectedFossilsForUserOrderByDate(userId);
  }

  @Override
  public LiveData<List<CollectedFossil>> getAllByFavoriteStateOrderByDateCollectedDesc(
      long userId, boolean favorite) {
    return collectedFossilDao.getCollectedFossilsByUserAndFavoriteOrderByDate(userId, favorite);
  }

  @Override
  public CompletableFuture<Long> insert(CollectedFossil collectedFossil) {
    return CompletableFuture.supplyAsync(() -> collectedFossilDao.insert(collectedFossil));
  }

  @Override
  public CompletableFuture<Boolean> setFossil(CollectedFossil collectedFossil, Fossil fossil) {
    return CompletableFuture.supplyAsync(() -> {
      collectedFossil.setFossilStatsId(fossil.getId());
      int updated = collectedFossilDao.update(collectedFossil);
      return updated > 0;
    });
  }

  @Override
  public CompletableFuture<Boolean> setFavoriteState(CollectedFossil collectedFossil,
      boolean favoriteState) {
    return CompletableFuture.supplyAsync(() -> {
      collectedFossil.setFavorite(favoriteState);
      int updated = collectedFossilDao.update(collectedFossil);
      return updated > 0;
    });
  }

  @Override
  public CompletableFuture<List<CollectedFossil>> getAllWithoutFossil(long userId) {
    return CompletableFuture.supplyAsync(
        () -> collectedFossilDao.getAllWithoutFossilForUser(userId));
  }
}
