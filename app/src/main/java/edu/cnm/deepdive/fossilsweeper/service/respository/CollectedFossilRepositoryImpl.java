package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.CollectedFossil;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CollectedFossilRepositoryImpl implements CollectedFossilRepository {

  @Override
  public LiveData<List<CollectedFossil>> getAllOrderByDateCollectedDesc() {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public LiveData<List<CollectedFossil>> getAllByFavoriteStateOrderByDateCollectedDesc(
      boolean favorite) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<Long> insert(CollectedFossil collectedFossil) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<Boolean> setFossil(CollectedFossil collectedFossil, Fossil fossil) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<Boolean> setFavoriteState(CollectedFossil collectedFossil,
      boolean favoriteState) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<List<CollectedFossil>> getAllWithoutFossil() {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }
}
