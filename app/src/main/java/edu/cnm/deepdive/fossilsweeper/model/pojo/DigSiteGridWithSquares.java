package edu.cnm.deepdive.fossilsweeper.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DigSiteGridWithSquares extends DigSiteGrid {

  @Relation(entity = DigSiteSquare.class, parentColumn = "id", entityColumn = "belongingGridId")
  private List<DigSiteSquare> squares;

  private Map<DigSiteCoord, DigSiteSquare> squaresMap;

  public Map<DigSiteCoord, DigSiteSquare> getSquaresMap() {
    if (squaresMap == null) {
      squaresMap = squares.stream()
          .collect(Collectors.toMap(DigSiteSquare::getCoord, Function.identity()));
    }
    return squaresMap;
  }
}
