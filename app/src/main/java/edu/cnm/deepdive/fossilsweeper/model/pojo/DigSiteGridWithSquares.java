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

  @Relation(entity = DigSiteSquare.class, parentColumn = "id", entityColumn = "belongingGridId")
  private List<DigSiteSquare> squares;

  private Map<DigSiteCoord, DigSiteSquare> gridSquares;

  public Map<DigSiteCoord, DigSiteSquare> getGridSquares() {
    if (gridSquares == null) {
      gridSquares = squares.stream()
          .collect(Collectors.toMap(DigSiteSquare::getCoord, Function.identity()));
    }
    return gridSquares;
  }

  public Set<DigSiteCoord> getMooreNeighbors(DigSiteCoord coord) {
    Map<DigSiteCoord, DigSiteSquare> gridSquaresTemp = getGridSquares();
    return coord.getNeighbors().stream()
        .filter(gridSquaresTemp::containsKey)
        .collect(Collectors.toSet());
  }
}
