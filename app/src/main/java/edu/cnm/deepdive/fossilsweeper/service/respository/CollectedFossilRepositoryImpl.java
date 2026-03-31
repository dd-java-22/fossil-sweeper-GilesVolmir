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

public class CollectedFossilRepositoryImpl implements CollectedFossilRepository {

  private final CollectedFossilDao collectedFossilDao;
  private final UserProfileRepository userProfileRepository;
  private final Context context;

  @Inject
  public CollectedFossilRepositoryImpl(CollectedFossilDao collectedFossilDao,
      UserProfileRepository userProfileRepository, @ApplicationContext Context context) {
    this.collectedFossilDao = collectedFossilDao;
    this.userProfileRepository = userProfileRepository;
    this.context = context;
  }

  @Override
  public LiveData<List<CollectedFossil>> getCollectedFossils() {
    return collectedFossilDao.getAllCollectedFossilsForUser(userProfileRepository.getUserProfile());
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
