package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
import java.util.concurrent.CompletableFuture;

public interface UserProfileRepository {

  CompletableFuture<UserProfile> getByOauthKey(String key);

  LiveData<UserProfile> getLiveByOauthKey(String key);

  CompletableFuture<UserProfile> getByUserId(long userId);

  CompletableFuture<UserProfile> insert(UserProfile userProfile);

  LiveData<Integer> getScanners(long userId);

  CompletableFuture<Integer> consumeScanners(int numberToConsume, long userId);
}
