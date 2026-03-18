package edu.cnm.deepdive.fossilsweeper.service;

import android.net.Uri;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.fossilsweeper.model.dao.CollectedFossilDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteGridDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteSquareDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.FossilDao;
import edu.cnm.deepdive.fossilsweeper.model.dao.UserProfileDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.CollectedFossil;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
import edu.cnm.deepdive.fossilsweeper.model.type.SquareState;
import edu.cnm.deepdive.fossilsweeper.service.FossilSweeperDatabase.Converters;
import java.time.Instant;

@Database(
    entities = {
        Fossil.class,
        CollectedFossil.class,
        UserProfile.class,
        DigSiteGrid.class,
        DigSiteSquare.class
    },
    version = FossilSweeperDatabase.DATABASE_VERSION)
@TypeConverters({Converters.class, SquareState.class})
public abstract class FossilSweeperDatabase extends RoomDatabase {

  static final int DATABASE_VERSION = 1;
  static final String DATABASE_NAME = "fossil-sweeper-db";

  public abstract FossilDao getFossilDao();

  public abstract CollectedFossilDao getCollectedFossilDao();

  public abstract UserProfileDao getUserProfileDao();

  public abstract DigSiteGridDao getDigSiteGridDao();

  public abstract DigSiteSquareDao getDigSiteSquareDao();


  /**
   * Type converters for Room database to handle non-primitive types.
   */
  public static class Converters {

    /**
     * Converts a {@link Long} timestamp to an {@link Instant}.
     *
     * @param value Timestamp in milliseconds since epoch, or {@code null}.
     * @return Corresponding {@link Instant}, or {@code null} if input is {@code null}.
     */
    @TypeConverter
    public static Instant longToInstant(Long value) {
      return (value != null) ? Instant.ofEpochMilli(value) : null;
    }

    /**
     * Converts an {@link Instant} to a {@link Long} timestamp.
     *
     * @param instant {@link Instant} to convert, or {@code null}.
     * @return Timestamp in milliseconds since epoch, or {@code null} if input is {@code null}.
     */
    @TypeConverter
    public static Long instantToLong(Instant instant) {
      return (instant != null) ? instant.toEpochMilli() : null;
    }

    /**
     * Converts a {@link String} to a {@link Uri}.
     *
     * @param value String representation of URI, or {@code null}.
     * @return Parsed {@link Uri}, or {@code null} if input is {@code null}.
     */
    @TypeConverter
    public static Uri stringToUri(String value) {
      return (value != null) ? Uri.parse(value) : null;
    }

    /**
     * Converts a {@link Uri} to a {@link String}.
     *
     * @param uri {@link Uri} to convert, or {@code null}.
     * @return String representation of URI, or {@code null} if input is {@code null}.
     */
    @TypeConverter
    public static String uriToString(Uri uri) {
      return (uri != null) ? uri.toString() : null;
    }

  }
}
