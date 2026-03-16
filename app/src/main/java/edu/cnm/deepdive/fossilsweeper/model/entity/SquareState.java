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

/**
 * Enumeration representing the possible states of a dig site square in the fossil sweeper game.
 */
public enum SquareState {

  /** Square has not been interacted with yet. */
  UNTOUCHED,

  /** Square has been marked with a fence/flag by the player. */
  FENCED,

  /** Square has been dug up by the player. */
  DUG,

  /** Fossil has been extracted from this square. */
  EXTRACTED

}
