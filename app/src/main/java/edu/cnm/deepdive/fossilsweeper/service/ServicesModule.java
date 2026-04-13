package edu.cnm.deepdive.fossilsweeper.service;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
interface ServicesModule {

  @Binds
  GameplayService bindGameplayService(GameplayServiceImpl impl);

}
