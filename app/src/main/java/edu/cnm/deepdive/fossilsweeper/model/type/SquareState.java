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
package edu.cnm.deepdive.fossilsweeper.model.type;

import androidx.room.TypeConverter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Enumeration representing the possible states of a dig site square in the fossil sweeper game.
 */
public enum SquareState {

  /** Square is in the default state. */
  UNTOUCHED(10),

  /** Square is marked with a fence. */
  FENCED(20),

  /** Square has been (destructively) dug up. */
  DUG(30),

  /** An Extractor tool has been used on this square. */
  EXTRACTED(40);

  private final int permanentId;
  private static final Map<Integer, SquareState> idToEnum;

  SquareState(int id) {
    permanentId = id;
  }

  static {
    idToEnum = Arrays.stream(SquareState.values())
        .collect(Collectors.toMap(
        (e) -> e.permanentId,
        (e) -> e)
    );
  }
  @TypeConverter
  public static SquareState fromConstantId(int id) {
    return idToEnum.get(id);
  }

  @TypeConverter
  public static int toConstantId(SquareState state) {
    return state.permanentId;
  }
}
