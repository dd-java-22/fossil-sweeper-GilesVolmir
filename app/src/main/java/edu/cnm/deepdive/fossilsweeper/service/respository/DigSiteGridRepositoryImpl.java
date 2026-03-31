package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteGridDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteSquareDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Singleton
public class DigSiteGridRepositoryImpl implements DigSiteGridRepository {

  private final DigSiteGridDao gridDao;
  private final DigSiteSquareDao squareDao;
  private final Context context;
  private final Executor executor;

  @Inject
  DigSiteGridRepositoryImpl(DigSiteGridDao gridDao, DigSiteSquareDao squareDao, @ApplicationContext Context context) {
    this.gridDao = gridDao;
    this.squareDao = squareDao;
    this.context = context;
    this.executor = Executors.newFixedThreadPool(4);
  }

  @Override
  public LiveData<DigSiteGrid> getById(long id) {
    return gridDao.selectById(id);
  }

  @Override
  public LiveData<List<DigSiteGrid>> getAllByPlayerId(long playerId) {
    return gridDao.selectByPlayerId(playerId);
  }

  @Override
  public CompletableFuture<Long> insert(DigSiteGrid digSiteGrid) {
    return CompletableFuture.supplyAsync(() -> gridDao.insert(digSiteGrid), executor);
  }

  @Override
  public CompletableFuture<Integer> updateRemainingBrushes(long gridId, int remainingBrushes) {
    return CompletableFuture.supplyAsync(() -> gridDao.updateRemainingBrushes(gridId, remainingBrushes), executor);
  }

  @Override
  public CompletableFuture<Integer> delete(DigSiteGrid digSiteGrid) {
    return CompletableFuture.supplyAsync(() -> gridDao.delete(digSiteGrid), executor);
  }

  @Override
  public CompletableFuture<DigSiteSquare> getSquareByCoordinates(long gridId, int x, int y) {
    return CompletableFuture.supplyAsync(() -> squareDao.selectByCoordinates(gridId, x, y), executor);
  }
}
