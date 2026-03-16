/*
 *  Copyright 2026 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package edu.cnm.deepdive.fossilsweeper.model.entity;

import android.net.Uri;
import androidx.room.TypeConverter;
import java.time.Instant;

/**
 * Type converters for Room database to handle non-primitive types.
 */
public class Converters {

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
