package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.CollectedFossil;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CollectedFossilRepository {

  LiveData<List<CollectedFossil>> getAllOrderByDateCollectedDesc(long userId);

  LiveData<List<CollectedFossil>> getAllByFavoriteStateOrderByDateCollectedDesc(long userId, boolean favorite);

  CompletableFuture<Long> insert(CollectedFossil collectedFossil);

  CompletableFuture<Boolean> setFossil(CollectedFossil collectedFossil, Fossil fossil);

  CompletableFuture<Boolean> setFavoriteState(CollectedFossil collectedFossil,
      boolean favoriteState);

  CompletableFuture<List<CollectedFossil>> getAllWithoutFossil(long userId);
}
