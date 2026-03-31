package edu.cnm.deepdive.fossilsweeper.service.respository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import jakarta.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public interface RepositoryModule {

  @Binds
  @Singleton
  GoogleAuthRepository bindGoogleAuthRepository(GoogleAuthRepositoryImpl impl);

}
