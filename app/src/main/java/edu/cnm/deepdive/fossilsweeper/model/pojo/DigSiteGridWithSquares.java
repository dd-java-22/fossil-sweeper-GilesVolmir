package edu.cnm.deepdive.fossilsweeper.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DigSiteGridWithSquares extends DigSiteGrid {

  @Relation(
      entity = DigSiteSquare.class,
      parentColumn = "dig_site_grid_id",
      entityColumn = "belonging_grid_id"
  )
  private List<DigSiteSquare> squares;

  public void setSquares(List<DigSiteSquare> squares) {
    this.squares = squares;
  }

  public Map<DigSiteCoord, DigSiteSquare> getGridSquares() {
    return squares.stream()
        .collect(Collectors.toMap(DigSiteSquare::getCoord, Function.identity()));
  }

  public Set<DigSiteCoord> getMooreNeighbors(DigSiteCoord coord) {
    Map<DigSiteCoord, DigSiteSquare> gridSquaresTemp = getGridSquares();
    return coord.getNeighbors().stream()
        .filter(gridSquaresTemp::containsKey)
        .collect(Collectors.toSet());
  }
}
