package edu.cnm.deepdive.fossilsweeper.viewmodel;

import edu.cnm.deepdive.fossilsweeper.R;

/**
 * Enumeration of available tools in the game. Each tool has a display name and associated icon
 * resource.
 */
public enum ToolType {
  /** Scan tool for revealing fossil locations and automatically digging or extracting them. */
  SCAN("Scan", R.drawable.radar_sweep),
  /** Extract tool for carefully removing fossils without destroying them. */
  EXTRACT("Extract", R.drawable.large_paint_brush),
  /** Dig tool for clearing squares (destroys fossils). */
  DIG("Dig", R.drawable.dig_dug),
  /** Fence tool for marking suspected fossil locations. */
  FENCE("Fence", R.drawable.packed_planks),
  /** Move tool for safe navigation. */
  MOVE("Move", R.drawable.tread);

  private final String toolName;
  private final int imageId;

  /**
   * Constructs a ToolType with the specified name and image resource.
   *
   * @param toolName Display name of the tool.
   * @param imageId Drawable resource ID for the tool icon.
   */
  ToolType(String toolName, int imageId) {
    this.toolName = toolName;
    this.imageId = imageId;
  }

  /**
   * Gets the display name of this tool.
   *
   * @return Tool name.
   */
  public String getToolName() {
    return toolName;
  }

  /**
   * Gets the drawable resource ID for this tool's icon.
   *
   * @return Image resource ID.
   */
  public int getImageId() {
    return imageId;
  }
}
