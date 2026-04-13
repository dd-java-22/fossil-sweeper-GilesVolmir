package edu.cnm.deepdive.fossilsweeper.service;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * Dagger Hilt module for binding service interfaces to their implementations. Installed in the
 * singleton component for application-wide availability.
 */
@Module
@InstallIn(SingletonComponent.class)
interface ServicesModule {

  /**
   * Binds the GameplayService interface to its implementation.
   *
   * @param impl Implementation instance.
   * @return GameplayService instance.
   */
  @Binds
  GameplayService bindGameplayService(GameplayServiceImpl impl);

}
