package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.type.DigSiteSquareState;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DigSiteSquareRepository {

  LiveData<List<DigSiteSquare>> getAllByGridId(long gridId);

  CompletableFuture<List<Long>> insertBatch(Collection<DigSiteSquare> squares);

  CompletableFuture<Integer> update(DigSiteSquare square);

  CompletableFuture<Integer> updateBatch(Collection<DigSiteSquare> squares);

  CompletableFuture<Void> updateStateByCoordinates(long gridId, int x, int y, DigSiteSquareState state);

  CompletableFuture<DigSiteSquareState> getStateByCoordinates(long gridId, int x, int y);

}
