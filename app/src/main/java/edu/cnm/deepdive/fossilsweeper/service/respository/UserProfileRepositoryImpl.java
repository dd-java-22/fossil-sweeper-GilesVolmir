package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import androidx.room.Transaction;
import edu.cnm.deepdive.fossilsweeper.model.dao.UserProfileDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
import jakarta.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class UserProfileRepositoryImpl implements UserProfileRepository {

  private final UserProfileDao userProfileDao;

  @Inject
  UserProfileRepositoryImpl(UserProfileDao userProfileDao) {
    this.userProfileDao = userProfileDao;
  }

  @Override
  public CompletableFuture<UserProfile> getByOauthKey(String key) {
    return CompletableFuture.supplyAsync(() -> {
      UserProfile userProfile = userProfileDao.selectByOauthKey(key);
      if (userProfile == null) {
        userProfile = new UserProfile();
        userProfile.setOauthKey(key);
        userProfile.setScannerItems(1);
        long id = userProfileDao.insert(userProfile);
        userProfile.setId(id);
      }
      return userProfile;
    });
  }

  @Override
  public LiveData<UserProfile> getLiveByOauthKey(String key) {
    UserProfile userProfile = userProfileDao.selectByOauthKey(key);
    if (userProfile == null) {
      userProfile = new UserProfile();
      userProfile.setOauthKey(key);
      userProfile.setScannerItems(1);
      long id = userProfileDao.insert(userProfile);
      userProfile.setId(id);
    }
    return userProfileDao.selectLiveByOauthKey(key);
  }

  @Override
  public CompletableFuture<UserProfile> getByUserId(long userId) {
    return CompletableFuture.supplyAsync(() -> userProfileDao.selectByIdSync(userId));
  }

  @Override
  public CompletableFuture<UserProfile> insert(UserProfile userProfile) {
    return CompletableFuture.supplyAsync(() -> {
      long id = userProfileDao.insert(userProfile);
      userProfile.setId(id);
      return userProfile;
    });
  }

  @Override
  public LiveData<Integer> getScanners(long userId) {
    return userProfileDao.selectScannerCountById(userId);
  }

  @Override
  @Transaction
  public CompletableFuture<Integer> consumeScanners(int numberToConsume, long userId) {
    return CompletableFuture.supplyAsync(() -> {
      UserProfile user = userProfileDao.selectByIdSync(userId);
      if (user != null) {
        int newScanners = user.getScannerItems() - numberToConsume;
        return userProfileDao.updateScannerItems(userId, newScanners);
      }
      return 0;
    });
  }
}
