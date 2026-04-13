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

/**
 * Hilt module that provides database dependencies including the Room database instance and all DAO
 * interfaces.
 */
@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

  /**
   * Provides a singleton instance of the Fossil Sweeper database.
   *
   * @param context Application context.
   * @return FossilSweeperDatabase instance.
   */
  @Provides
  @Singleton
  FossilSweeperDatabase provideDatabase(@ApplicationContext Context context) {
    return Room
        .databaseBuilder(context, FossilSweeperDatabase.class, FossilSweeperDatabase.DATABASE_NAME)
        .build();
  }

  /**
   * Provides the DAO for fossil operations.
   *
   * @param db Database instance.
   * @return FossilDao instance.
   */
  @Provides
  @Singleton
  FossilDao provideFossilDao(FossilSweeperDatabase db) {
    return db.getFossilDao();
  }

  /**
   * Provides the DAO for collected fossil operations.
   *
   * @param db Database instance.
   * @return CollectedFossilDao instance.
   */
  @Provides
  @Singleton
  CollectedFossilDao provideCollectedFossilDao(FossilSweeperDatabase db) {
    return db.getCollectedFossilDao();
  }

  /**
   * Provides the DAO for user profile operations.
   *
   * @param db Database instance.
   * @return UserProfileDao instance.
   */
  @Provides
  @Singleton
  UserProfileDao provideUserProfileDao(FossilSweeperDatabase db) {
    return db.getUserProfileDao();
  }

  /**
   * Provides the DAO for dig site grid operations.
   *
   * @param db Database instance.
   * @return DigSiteGridDao instance.
   */
  @Provides
  @Singleton
  DigSiteGridDao provideDigSiteGridDao(FossilSweeperDatabase db) {
    return db.getDigSiteGridDao();
  }

  /**
   * Provides the DAO for dig site square operations.
   *
   * @param db Database instance.
   * @return DigSiteSquareDao instance.
   */
  @Provides
  @Singleton
  DigSiteSquareDao provideDigSiteSquareDao(FossilSweeperDatabase db) {
    return db.getDigSiteSquareDao();
  }

}
