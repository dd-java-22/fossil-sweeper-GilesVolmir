package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteGridDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteSquareDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteGridWithSquares;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Singleton
public class DigSiteGridRepositoryImpl implements DigSiteGridRepository {

  private final DigSiteGridDao gridDao;
  private final DigSiteSquareDao squareDao;

  @Inject
  DigSiteGridRepositoryImpl(DigSiteGridDao gridDao, DigSiteSquareDao squareDao) {
    this.gridDao = gridDao;
    this.squareDao = squareDao;
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
    return CompletableFuture.supplyAsync(() -> gridDao.insert(digSiteGrid));
  }

  @Override
  public LiveData<DigSiteGridWithSquares> getMostRecentDigSiteGridWithSquaresByPlayerId(
      long playerId) {
    return gridDao.getMostRecentDigSiteGridWithSquaresByPlayerId(playerId);
  }

  @Override
  public LiveData<DigSiteGridWithSquares> getDigSiteGridWithSquaresById(long id) {
    return gridDao.selectWithSquares(id);
  }

  //  @Override
//  public CompletableFuture<Long> insert(DigSiteGridWithSquares digSiteGridWithSquares) {
//    CompletableFuture<Long> savingGrid = CompletableFuture.supplyAsync(() -> gridDao.insert(digSiteGridWithSquares.getDigSiteSquares()));
//    savingGrid.thenAccept(gridId -> {
//      for (DigSiteSquare square : digSiteGridWithSquares.getSquares()) {
//        square.setGridId(gridId);
//        squareDao.insert(square);
//      }
//    });
//    return savingGrid;
//  }

  @Override
  public CompletableFuture<Integer> updateRemainingBrushes(long gridId, int remainingBrushes) {
    return CompletableFuture.supplyAsync(
        () -> gridDao.updateRemainingBrushes(gridId, remainingBrushes));
  }

  @Override
  public CompletableFuture<Integer> delete(DigSiteGrid digSiteGrid) {
    return CompletableFuture.supplyAsync(() -> gridDao.delete(digSiteGrid));
  }

  @Override
  public CompletableFuture<DigSiteSquare> getSquareByCoordinates(long gridId, int x, int y) {
    return CompletableFuture.supplyAsync(() -> squareDao.selectByCoordinates(gridId, x, y));
  }
}
