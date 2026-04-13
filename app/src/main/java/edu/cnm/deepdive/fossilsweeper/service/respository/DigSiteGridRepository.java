package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteGridWithSquares;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DigSiteGridRepository {

  LiveData<DigSiteGrid> getById(long id);

  LiveData<List<DigSiteGrid>> getAllByPlayerId(long playerId);

  LiveData<DigSiteGridWithSquares> getMostRecentDigSiteGridWithSquaresByPlayerId(long playerId);

  LiveData<DigSiteGridWithSquares> getDigSiteGridWithSquaresById(long id);

  CompletableFuture<Long> insert(DigSiteGrid digSiteGrid);

//  CompletableFuture<Long> insert(DigSiteGridWithSquares digSiteGridWithSquares);

  CompletableFuture<Integer> updateRemainingBrushes(long gridId, int remainingBrushes);

  CompletableFuture<Integer> endGame(long gridId);

  CompletableFuture<Integer> delete(DigSiteGrid digSiteGrid);

  CompletableFuture<DigSiteSquare> getSquareByCoordinates(long gridId, int x, int y);

}
