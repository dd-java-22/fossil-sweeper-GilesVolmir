package edu.cnm.deepdive.fossilsweeper.service.respository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import jakarta.inject.Singleton;

/**
 * Dagger Hilt module for binding repository interfaces to their implementations. All repositories
 * are installed in the singleton component with singleton scope for application-wide availability.
 */
@Module
@InstallIn(SingletonComponent.class)
interface RepositoryModule {

  /**
   * Binds the GoogleAuthRepository interface to its implementation.
   *
   * @param impl Implementation instance.
   * @return GoogleAuthRepository instance.
   */
  @Binds
  @Singleton
  GoogleAuthRepository bindGoogleAuthRepository(GoogleAuthRepositoryImpl impl);

  /**
   * Binds the UserProfileRepository interface to its implementation.
   *
   * @param impl Implementation instance.
   * @return UserProfileRepository instance.
   */
  @Binds
  @Singleton
  UserProfileRepository bindUserProfileRepository(UserProfileRepositoryImpl impl);

  /**
   * Binds the DigSiteGridRepository interface to its implementation.
   *
   * @param impl Implementation instance.
   * @return DigSiteGridRepository instance.
   */
  @Binds
  @Singleton
  DigSiteGridRepository bindDigSiteGridRepository(DigSiteGridRepositoryImpl impl);

  /**
   * Binds the DigSiteSquareRepository interface to its implementation.
   *
   * @param impl Implementation instance.
   * @return DigSiteSquareRepository instance.
   */
  @Binds
  @Singleton
  DigSiteSquareRepository bindDigSiteSquareRepository(DigSiteSquareRepositoryImpl impl);

  /**
   * Binds the CollectedFossilRepository interface to its implementation.
   *
   * @param impl Implementation instance.
   * @return CollectedFossilRepository instance.
   */
  @Binds
  @Singleton
  CollectedFossilRepository bindCollectionRepository(CollectedFossilRepositoryImpl impl);

  /**
   * Binds the FossilRepository interface to its implementation.
   *
   * @param impl Implementation instance.
   * @return FossilRepository instance.
   */
  @Binds
  @Singleton
  FossilRepository bindFossilRepository(FossilRepositoryImpl impl);
}
