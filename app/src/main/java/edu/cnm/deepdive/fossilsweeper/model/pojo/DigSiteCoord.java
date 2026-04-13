package edu.cnm.deepdive.fossilsweeper.model.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Record representing a coordinate position on a dig site grid. Provides utility methods for
 * working with coordinates and their neighbors.
 *
 * @param x X coordinate.
 * @param y Y coordinate.
 */
public record DigSiteCoord(int x, int y) {

  /**
   * Checks whether this coordinate exists on the specified dig site grid.
   *
   * @param grid Dig site grid to check against.
   * @return {@code true} if this coordinate is present in the grid, {@code false} otherwise.
   */
  boolean onDigSiteGrid(DigSiteGridWithSquares grid) {
    return grid.getGridSquares().containsKey(this);
  }

  /**
   * Gets all Moore neighborhood coordinates (8 surrounding squares) for this coordinate.
   *
   * @return Set of neighboring coordinates (may include coordinates outside the grid bounds).
   */
  public Set<DigSiteCoord> getNeighbors() {
    Set<DigSiteCoord> neighbors = new HashSet<>();
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        if (i == x && j == y) {
          continue;
        }
        neighbors.add(new DigSiteCoord(i, j));
      }
    }
    return neighbors;
  }

}
