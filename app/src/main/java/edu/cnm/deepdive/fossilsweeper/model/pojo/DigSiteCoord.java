package edu.cnm.deepdive.fossilsweeper.model.pojo;

import java.util.HashSet;
import java.util.Set;

public record DigSiteCoord(int x, int y) {

  boolean onDigSiteGrid(DigSiteGridWithSquares grid) {
    return grid.getGridSquares().containsKey(this);
  }

  Set<DigSiteCoord> getNeighbors() {
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
