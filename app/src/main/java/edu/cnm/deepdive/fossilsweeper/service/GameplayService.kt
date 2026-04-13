package edu.cnm.deepdive.fossilsweeper.service

import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteCoord

/**
 * Service interface for managing Minesweeper-style fossil digging gameplay operations. Provides
 * methods for creating dig sites, digging squares, and using game tools.
 */
interface GameplayService {

    /**
     * Starts a new dig site game with the specified parameters.
     *
     * @param width The width of the dig site grid.
     * @param height The height of the dig site grid.
     * @param density The percentage of squares that contain fossils (0-100).
     * @param userId The ID of the user starting the dig.
     * @return The ID of the newly created dig site grid.
     */
    fun startNewDig(width: Int, height: Int, density: Int, userId: Long): Long

    /**
     * Digs a square at the specified location, revealing its contents. If the square has no
     * adjacent fossils, recursively digs neighboring squares.
     *
     * @param boardMap The map of all squares in the dig site.
     * @param location The coordinates of the square to dig.
     */
    fun digSquare(boardMap: Map<DigSiteCoord, DigSiteSquare>, location: DigSiteCoord)

    /**
     * Toggles the fence marker on a square to indicate a suspected fossil location.
     *
     * @param square The square to toggle the fence marker on.
     */
    fun toggleFenceSquare(square: DigSiteSquare)

    /**
     * Extracts a fossil from a square using a brush tool, consuming one brush item.
     *
     * @param square The square to extract from.
     * @param gridId The ID of the dig site grid.
     * @param currentBrushes The current number of available brushes.
     */
    fun extractSquare(square: DigSiteSquare, gridId: Long, currentBrushes: Int)

    /**
     * Scans a square using a scanner tool, automatically extracting if it contains a fossil or
     * digging if it doesn't. Consumes one scanner item.
     *
     * @param boardMap The map of all squares in the dig site.
     * @param location The coordinates of the square to scan.
     * @param userId The ID of the user performing the scan.
     * @param gridId The ID of the dig site grid.
     * @param currentBrushes The current number of available brushes.
     * @param currentScanners The current number of available scanners.
     */
    fun scanSquare(boardMap: Map<DigSiteCoord, DigSiteSquare>, location: DigSiteCoord, userId: Long, gridId: Long, currentBrushes: Int, currentScanners: Int)

}
