package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.CollectedFossilDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.CollectedFossil;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import jakarta.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CollectedFossilRepositoryImpl implements CollectedFossilRepository {

  private final CollectedFossilDao collectedFossilDao;
  private final Context context;
  private final Executor executor;

  @Inject
  CollectedFossilRepositoryImpl(CollectedFossilDao collectedFossilDao, @ApplicationContext Context context) {
    this.collectedFossilDao = collectedFossilDao;
    this.context = context;
    this.executor = Executors.newFixedThreadPool(4);
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
    return CompletableFuture.supplyAsync(() -> collectedFossilDao.insert(collectedFossil), executor);
  }

  @Override
  public CompletableFuture<Boolean> setFossil(CollectedFossil collectedFossil, Fossil fossil) {
    return CompletableFuture.supplyAsync(() -> {
      collectedFossil.setFossilStatsId(fossil.getId());
      int updated = collectedFossilDao.update(collectedFossil);
      return updated > 0;
    }, executor);
  }

  @Override
  public CompletableFuture<Boolean> setFavoriteState(CollectedFossil collectedFossil,
      boolean favoriteState) {
    return CompletableFuture.supplyAsync(() -> {
      collectedFossil.setFavorite(favoriteState);
      int updated = collectedFossilDao.update(collectedFossil);
      return updated > 0;
    }, executor);
  }

  @Override
  public CompletableFuture<List<CollectedFossil>> getAllWithoutFossil(long userId) {
    return CompletableFuture.supplyAsync(() -> collectedFossilDao.getAllWithoutFossilForUser(userId), executor);
  }
}
