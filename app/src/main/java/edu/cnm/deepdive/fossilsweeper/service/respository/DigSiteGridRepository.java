package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DigSiteGridRepository {

  LiveData<DigSiteGrid> getById(long id);

  LiveData<List<DigSiteGrid>> getAllByPlayerId(long playerId);

  CompletableFuture<Long> insert(DigSiteGrid digSiteGrid);

  CompletableFuture<Integer> updateRemainingBrushes(long gridId, int remainingBrushes);

  CompletableFuture<Integer> delete(DigSiteGrid digSiteGrid);

  CompletableFuture<DigSiteSquare> getSquareByCoordinates(long gridId, int x, int y);

}
