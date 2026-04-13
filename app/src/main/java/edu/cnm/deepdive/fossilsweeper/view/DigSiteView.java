package edu.cnm.deepdive.fossilsweeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import edu.cnm.deepdive.fossilsweeper.R;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteCoord;
import java.util.Map;

public class DigSiteView extends View {

  private final Context context;
  private int xCells = 1;
  private int yCells = 1;
  private int cellDim;
  private Map<DigSiteCoord, DigSiteSquare> gridSquares;

  public DigSiteView(Context context) {
    super(context);
    this.context = context;
  }

  public DigSiteView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    this.context = context;

  }

  public DigSiteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;

  }

  public DigSiteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    this.context = context;
  }

  public void setGridSquares(Map<DigSiteCoord, DigSiteSquare> gridSquares) {
    this.gridSquares = gridSquares;
    int xCellsNew = gridSquares.keySet().stream().mapToInt(DigSiteCoord::x).max().orElse(0) + 1;
    int yCellsNew = gridSquares.keySet().stream().mapToInt(DigSiteCoord::y).max().orElse(0) + 1;
    if (xCellsNew != xCells || yCellsNew != yCells) {
      xCells = xCellsNew;
      yCells = yCellsNew;
      requestLayout();
    } else {
      postInvalidate();
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (gridSquares == null) {
      setMeasuredDimension(0, 0);
      return;
    }
    int suggestedW = getSuggestedMinimumWidth();
    int suggestedH = getSuggestedMinimumHeight();
    int width = resolveSizeAndState(getPaddingLeft() + getPaddingRight() + suggestedW,
        widthMeasureSpec, 0);
    int height = resolveSizeAndState(getPaddingTop() + getPaddingBottom() + suggestedH,
        heightMeasureSpec, 0);

    int heightFromWidth = width * yCells / xCells;
    int widthFromHeight = height * xCells / yCells;

    if (heightFromWidth > height) {
      setMeasuredDimension(widthFromHeight, height);
      cellDim = height / yCells;
    } else {
      setMeasuredDimension(width, heightFromWidth);
      cellDim = width / xCells;
    }
  }

  @Override
  protected void onDraw(@NonNull Canvas canvas) {
    super.onDraw(canvas);
    if (gridSquares != null) {
      gridSquares.forEach((key, cellData) -> {
        Drawable tile = cellToDrawableTile(cellData);
        tile.setBounds(gridCoordinateToBounds(key));
        tile.draw(canvas);
      });
    }
  }

  private DigSiteCoord pixelToGridCoordinate(int x, int y) {
    return new DigSiteCoord(x / cellDim, y / cellDim);
  }

  private Rect gridCoordinateToBounds(DigSiteCoord coord) {
    return new Rect(
        coord.x() * cellDim,
        coord.y() * cellDim,
        (coord.x() + 1) * cellDim,
        (coord.y() + 1) * cellDim);
  }

  private Drawable cellToDrawableTile(DigSiteSquare cellData) {
    Drawable standIn = AppCompatResources.getDrawable(context, R.drawable.rock);
    return standIn;
  }

}
