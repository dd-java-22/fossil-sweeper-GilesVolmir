package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteSquareDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.type.SquareState;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DigSiteSquareRepositoryImpl implements DigSiteSquareRepository {

  private final DigSiteSquareDao digSiteSquareDao;
  private final Context context;
  private final Executor executor;

  @Inject
  DigSiteSquareRepositoryImpl(DigSiteSquareDao digSiteSquareDao, @ApplicationContext Context context) {
    this.digSiteSquareDao = digSiteSquareDao;
    this.context = context;
    this.executor = Executors.newFixedThreadPool(4);
  }

  @Override
  public LiveData<List<DigSiteSquare>> getAllByGridId(long gridId) {
    return digSiteSquareDao.selectByGridId(gridId);
  }

  @Override
  public CompletableFuture<List<Long>> insertBatch(Collection<DigSiteSquare> squares) {
    return CompletableFuture.supplyAsync(() -> digSiteSquareDao.insert(squares), executor);
  }

  @Override
  public CompletableFuture<Integer> update(DigSiteSquare square) {
    return CompletableFuture.supplyAsync(() -> digSiteSquareDao.update(square), executor);
  }

  @Override
  public CompletableFuture<Integer> updateBatch(Collection<DigSiteSquare> squares) {
    return CompletableFuture.supplyAsync(() -> digSiteSquareDao.update(squares), executor);
  }

  @Override
  public CompletableFuture<Void> updateStateByCoordinates(long gridId, int x, int y, SquareState state) {
    return CompletableFuture.runAsync(() -> {
      DigSiteSquare square = digSiteSquareDao.selectByCoordinates(gridId, x, y);
      if (square != null) {
        square.setState(state);
        digSiteSquareDao.update(square);
      }
    }, executor);
  }

  @Override
  public CompletableFuture<SquareState> getStateByCoordinates(long gridId, int x, int y) {
    return CompletableFuture.supplyAsync(() -> {
      DigSiteSquare square = digSiteSquareDao.selectByCoordinates(gridId, x, y);
      return square != null ? square.getState() : null;
    }, executor);
  }
}
