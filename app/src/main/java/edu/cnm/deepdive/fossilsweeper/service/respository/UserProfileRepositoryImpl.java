package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.UserProfileDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
import java.util.concurrent.CompletableFuture;

public class UserProfileRepositoryImpl implements UserProfileRepository {

  private final UserProfileDao userProfileDao;
  private final Context context;

  public UserProfileRepositoryImpl(UserProfileDao userProfileDao, @ApplicationContext Context context) {
    this.userProfileDao = userProfileDao;
    this.context = context;
  }

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
  public CompletableFuture<Integer> consumeScanners(int numberToConsume, long userId) {
    int newScanners = getScanners(userId).getValue() - numberToConsume;
    return CompletableFuture.supplyAsync(() -> userProfileDao.updateScanners(userId, newScanners));
  }
}
