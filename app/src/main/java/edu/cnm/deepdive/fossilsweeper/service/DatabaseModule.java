package edu.cnm.deepdive.fossilsweeper.service;

import android.content.Context;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.fossilsweeper.model.dao.CollectedFossilDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteGridDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteSquareDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.FossilDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.UserProfileDao;
import jakarta.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

  @Provides
  @Singleton
  FossilSweeperDatabase provideDatabase(@ApplicationContext Context context) {
    return Room
        .databaseBuilder(context, FossilSweeperDatabase.class, FossilSweeperDatabase.DATABASE_NAME)
        .build();
  }

  @Provides
  @Singleton
  FossilDao provideFossilDao(FossilSweeperDatabase db) {
    return db.getFossilDao();
  }

  @Provides
  @Singleton
  CollectedFossilDao provideCollectedFossilDao(FossilSweeperDatabase db) {
    return db.getCollectedFossilDao();
  }

  @Provides
  @Singleton
  UserProfileDao provideUserProfileDao(FossilSweeperDatabase db) {
    return db.getUserProfileDao();
  }

  @Provides
  @Singleton
  DigSiteGridDao provideDigSiteGridDao(FossilSweeperDatabase db) {
    return db.getDigSiteGridDao();
  }

  @Provides
  @Singleton
  DigSiteSquareDao provideDigSiteSquareDao(FossilSweeperDatabase db) {
    return db.getDigSiteSquareDao();
  }

}
