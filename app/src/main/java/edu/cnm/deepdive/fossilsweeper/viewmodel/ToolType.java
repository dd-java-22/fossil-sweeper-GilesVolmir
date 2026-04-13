package edu.cnm.deepdive.fossilsweeper.viewmodel;

import edu.cnm.deepdive.fossilsweeper.R;

public enum ToolType {
  SCAN("Scan", R.drawable.radar_sweep),
  EXTRACT("Extract", R.drawable.large_paint_brush),
  DIG("Dig", R.drawable.dig_dug),
  FENCE("Fence", R.drawable.packed_planks),
  MOVE("Move", R.drawable.tread);

  private final String toolName;
  private final int imageId;

  ToolType(String toolName, int imageId) {
    this.toolName = toolName;
    this.imageId = imageId;
  }

  public String getToolName() {
    return toolName;
  }

  public int getImageId() {
    return imageId;
  }
}
