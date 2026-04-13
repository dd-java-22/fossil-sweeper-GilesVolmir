package edu.cnm.deepdive.fossilsweeper.service.respository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import jakarta.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
interface RepositoryModule {

  @Binds
  @Singleton
  GoogleAuthRepository bindGoogleAuthRepository(GoogleAuthRepositoryImpl impl);

  @Binds
  @Singleton
  UserProfileRepository bindUserProfileRepository(UserProfileRepositoryImpl impl);

  @Binds
  @Singleton
  DigSiteGridRepository bindDigSiteGridRepository(DigSiteGridRepositoryImpl impl);

  @Binds
  @Singleton
  DigSiteSquareRepository bindDigSiteSquareRepository(DigSiteSquareRepositoryImpl impl);

  @Binds
  @Singleton
  CollectedFossilRepository bindCollectionRepository(CollectedFossilRepositoryImpl impl);

  @Binds
  @Singleton
  FossilRepository bindFossilRepository(FossilRepositoryImpl impl);
}
