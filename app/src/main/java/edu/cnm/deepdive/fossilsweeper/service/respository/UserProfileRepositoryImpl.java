package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
import java.util.concurrent.CompletableFuture;

public class UserProfileRepositoryImpl implements UserProfileRepository {

  @Override
  public CompletableFuture<UserProfile> getByOauthKey(String key) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<UserProfile> getByUserId(long userId) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<UserProfile> insert(UserProfile userProfile) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public LiveData<Integer> getScanners(long userId) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<Integer> consumeScanners(int numberToConsume) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }
}
