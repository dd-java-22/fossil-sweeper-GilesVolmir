package edu.cnm.deepdive.fossilsweeper.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import edu.cnm.deepdive.fossilsweeper.model.type.SquareState;

@DatabaseView("""
            SELECT
            """
)
public class DigSiteGridWithSquares {
  @ColumnInfo(name = "player_id")
  private long playerId;

  @ColumnInfo(name = "height")
  private int height;

  @ColumnInfo(name = "width")
  private int width;

  @ColumnInfo(name = "x_coordinate")
  private int xCoordinate;

  @ColumnInfo(name = "y_coordinate")
  private int yCoordinate;

  @NonNull
  @ColumnInfo(name = "has_fossil")
  private boolean hasFossil;

  @ColumnInfo(name = "moore_neighbor_fossils")
  private int mooreNeighborFossils;

  @NonNull
  @ColumnInfo(name = "state")
  private SquareState state;
}
