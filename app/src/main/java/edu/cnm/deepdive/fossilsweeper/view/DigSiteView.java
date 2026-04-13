package edu.cnm.deepdive.fossilsweeper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import edu.cnm.deepdive.fossilsweeper.R;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteCoord;
import edu.cnm.deepdive.fossilsweeper.model.type.DigSiteSquareState;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Custom view for rendering the dig site game board. Displays a grid of squares with different
 * appearances based on their state, and handles touch events for cell selection.
 */
public class DigSiteView extends View {

  private final Context context;
  private int xCells = 1;
  private int yCells = 1;
  private int cellDim;
  private Map<DigSiteCoord, DigSiteSquare> gridSquares;
  private Consumer<DigSiteCoord> onCellClickListener;

  /**
   * Constructs a DigSiteView with the specified context.
   *
   * @param context Application context.
   */
  public DigSiteView(Context context) {
    super(context);
    this.context = context;
  }

  /**
   * Constructs a DigSiteView with the specified context and attribute set.
   *
   * @param context Application context.
   * @param attrs XML attributes.
   */
  public DigSiteView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    this.context = context;

  }

  /**
   * Constructs a DigSiteView with the specified context, attributes, and default style.
   *
   * @param context Application context.
   * @param attrs XML attributes.
   * @param defStyleAttr Default style attribute.
   */
  public DigSiteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;

  }

  /**
   * Constructs a DigSiteView with the specified context, attributes, and default style resources.
   *
   * @param context Application context.
   * @param attrs XML attributes.
   * @param defStyleAttr Default style attribute.
   * @param defStyleRes Default style resource.
   */
  public DigSiteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    this.context = context;
  }

  /**
   * Sets the grid data to display and triggers a layout or invalidation as needed.
   *
   * @param gridSquares Map of coordinates to dig site squares.
   */
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

  /**
   * Sets the listener to be called when a cell is clicked.
   *
   * @param listener Consumer that receives the clicked coordinate.
   */
  public void setOnCellClickListener(Consumer<DigSiteCoord> listener) {
    this.onCellClickListener = listener;
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
        // Add drawing a number in the center of the square based on getMooreNeighborFossils value
        boolean visibleNumber = cellData.getState() == DigSiteSquareState.DUG || cellData.getState() == DigSiteSquareState.EXTRACTED;
        int mooreNeighborFossils = cellData.getMooreNeighborFossils();
        if (mooreNeighborFossils > 0 && visibleNumber) {
          float textCellRatio = 0.8F;
          float centerOffset = cellDim * ( 0.5F + textCellRatio / 2.0F);
          Paint paint = new Paint();
          paint.setColor(Color.GREEN);
          paint.setShadowLayer(4, 4, 4, Color.BLACK);
          paint.setTextSize(textCellRatio * cellDim);
          paint.setTextAlign(Paint.Align.CENTER);
          canvas.drawText(String.valueOf(mooreNeighborFossils),
                          key.x() * cellDim + cellDim / 2.0F,
                          key.y() * cellDim + centerOffset,
                          paint);
        }
      });
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_UP) {
      if (gridSquares != null && onCellClickListener != null) {
        DigSiteCoord coord = pixelToGridCoordinate((int) event.getX(), (int) event.getY());
        if (gridSquares.containsKey(coord)) {
          onCellClickListener.accept(coord);
          return true;
        }
      }
    }
    return super.onTouchEvent(event);
  }

  /**
   * Converts pixel coordinates to grid coordinates.
   *
   * @param x X pixel position.
   * @param y Y pixel position.
   * @return Grid coordinate.
   */
  private DigSiteCoord pixelToGridCoordinate(int x, int y) {
    return new DigSiteCoord(x / cellDim, y / cellDim);
  }

  /**
   * Converts grid coordinates to pixel bounds for drawing.
   *
   * @param coord Grid coordinate.
   * @return Rectangle bounds for the cell.
   */
  private Rect gridCoordinateToBounds(DigSiteCoord coord) {
    return new Rect(
        coord.x() * cellDim,
        coord.y() * cellDim,
        (coord.x() + 1) * cellDim,
        (coord.y() + 1) * cellDim);
  }

  /**
   * Selects the appropriate drawable tile for a cell based on its state and fossil content.
   *
   * @param cellData Dig site square data.
   * @return Drawable to render for this cell.
   */
  private Drawable cellToDrawableTile(DigSiteSquare cellData) {
    DigSiteSquareState state = cellData.getState();
    return switch (state) {
      case UNTOUCHED -> AppCompatResources.getDrawable(context, R.drawable.rock);
      case FENCED -> AppCompatResources.getDrawable(context, R.drawable.packed_planks);
      case DUG -> cellData.isHasFossil()
          ? AppCompatResources.getDrawable(context, R.drawable.powder)
          : AppCompatResources.getDrawable(context, R.drawable.path_tile);
      case EXTRACTED -> cellData.isHasFossil()
          ? AppCompatResources.getDrawable(context, R.drawable.fossil)
          : AppCompatResources.getDrawable(context, R.drawable.path_tile);
    };
  }

}
