package edu.cnm.deepdive.fossilsweeper.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * POJO (Plain Old Java Object) extending {@link DigSiteGrid} to include its associated squares.
 * Used by Room for transactional queries that retrieve a grid along with all its squares.
 */
public class DigSiteGridWithSquares extends DigSiteGrid {

  @Relation(
      entity = DigSiteSquare.class,
      parentColumn = "dig_site_grid_id",
      entityColumn = "belonging_grid_id"
  )
  private List<DigSiteSquare> digSiteSquares;

  /**
   * Sets the list of dig site squares associated with this grid.
   *
   * @param digSiteSquares List of squares.
   */
  public void setDigSiteSquares(List<DigSiteSquare> digSiteSquares) {
    this.digSiteSquares = digSiteSquares;
  }

  /**
   * Gets the list of dig site squares associated with this grid.
   *
   * @return List of squares.
   */
  public List<DigSiteSquare> getDigSiteSquares() {
    return digSiteSquares;
  }

  /**
   * Converts the list of squares into a map keyed by their coordinates for efficient lookup.
   *
   * @return Map of coordinates to dig site squares.
   */
  public Map<DigSiteCoord, DigSiteSquare> getGridSquares() {
    return digSiteSquares.stream()
        .collect(Collectors.toMap(DigSiteSquare::getCoord, Function.identity()));
  }

  /**
   * Retrieves the Moore neighborhood (8 surrounding squares) for a given coordinate, filtering out
   * coordinates that don't exist in the grid.
   *
   * @param coord Center coordinate.
   * @return Set of valid neighboring coordinates.
   */
  public Set<DigSiteCoord> getMooreNeighbors(DigSiteCoord coord) {
    Map<DigSiteCoord, DigSiteSquare> gridSquaresTemp = getGridSquares();
    return coord.getNeighbors().stream()
        .filter(gridSquaresTemp::containsKey)
        .collect(Collectors.toSet());
  }
}
