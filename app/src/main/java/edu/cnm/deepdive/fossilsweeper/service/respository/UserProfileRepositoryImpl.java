package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.UserProfileDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
import jakarta.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserProfileRepositoryImpl implements UserProfileRepository {

  private final UserProfileDao userProfileDao;
  private final Context context;
  private final Executor executor;

  @Inject
  UserProfileRepositoryImpl(UserProfileDao userProfileDao, @ApplicationContext Context context) {
    this.userProfileDao = userProfileDao;
    this.context = context;
    this.executor = Executors.newFixedThreadPool(4);
  }

  @Override
  public CompletableFuture<UserProfile> getByOauthKey(String key) {
    return CompletableFuture.supplyAsync(() -> userProfileDao.selectByOauthKey(key), executor);
  }

  @Override
  public CompletableFuture<UserProfile> getByUserId(long userId) {
    return CompletableFuture.supplyAsync(() -> userProfileDao.selectByIdSync(userId), executor);
  }

  @Override
  public CompletableFuture<UserProfile> insert(UserProfile userProfile) {
    return CompletableFuture.supplyAsync(() -> {
      long id = userProfileDao.insert(userProfile);
      userProfile.setId(id);
      return userProfile;
    }, executor);
  }

  @Override
  public LiveData<Integer> getScanners(long userId) {
    return userProfileDao.selectScannerCountById(userId);
  }

  @Override
  public CompletableFuture<Integer> consumeScanners(int numberToConsume, long userId) {
    return CompletableFuture.supplyAsync(() -> {
      UserProfile user = userProfileDao.selectByIdSync(userId);
      if (user != null) {
        int newScanners = user.getScannerItems() - numberToConsume;
        return userProfileDao.updateScannerItems(userId, newScanners);
      }
      return 0;
    }, executor);
  }
}
