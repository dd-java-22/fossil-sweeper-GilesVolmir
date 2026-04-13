package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteSquareDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.type.DigSiteSquareState;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of {@link DigSiteSquareRepository} that delegates to the corresponding DAO for
 * database operations.
 */
public class DigSiteSquareRepositoryImpl implements DigSiteSquareRepository {

  private final DigSiteSquareDao digSiteSquareDao;

  /**
   * Constructs a DigSiteSquareRepositoryImpl with the specified DAO.
   *
   * @param digSiteSquareDao DAO for database operations.
   */
  @Inject
  DigSiteSquareRepositoryImpl(DigSiteSquareDao digSiteSquareDao) {
    this.digSiteSquareDao = digSiteSquareDao;
  }

  @Override
  public LiveData<List<DigSiteSquare>> getAllByGridId(long gridId) {
    return digSiteSquareDao.selectByGridId(gridId);
  }

  @Override
  public CompletableFuture<List<Long>> insertBatch(Collection<DigSiteSquare> squares) {
    return CompletableFuture.supplyAsync(() -> digSiteSquareDao.insert(squares));
  }

  @Override
  public CompletableFuture<Integer> update(DigSiteSquare square) {
    return CompletableFuture.supplyAsync(() -> digSiteSquareDao.update(square));
  }

  @Override
  public CompletableFuture<Integer> updateBatch(Collection<DigSiteSquare> squares) {
    return CompletableFuture.supplyAsync(() -> digSiteSquareDao.update(squares));
  }

  @Override
  public CompletableFuture<Void> updateStateByCoordinates(long gridId, int x, int y,
      DigSiteSquareState state) {
    return CompletableFuture.runAsync(() -> {
      DigSiteSquare square = digSiteSquareDao.selectByCoordinates(gridId, x, y);
      if (square != null) {
        square.setState(state);
        digSiteSquareDao.update(square);
      }
    });
  }

  @Override
  public CompletableFuture<DigSiteSquareState> getStateByCoordinates(long gridId, int x, int y) {
    return CompletableFuture.supplyAsync(() -> {
      DigSiteSquare square = digSiteSquareDao.selectByCoordinates(gridId, x, y);
      return square != null ? square.getState() : null;
    });
  }
}
