package edu.cnm.deepdive.fossilsweeper.service

import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteCoord

interface GameplayService {

    fun startNewDig(width: Int, height: Int, density: Int, userId: Long): Long

    fun digSquare(boardMap: Map<DigSiteCoord, DigSiteSquare>, location: DigSiteCoord)

    fun toggleFenceSquare(square: DigSiteSquare)

    fun extractSquare(square: DigSiteSquare, gridId: Long, currentBrushes: Int)

    fun scanSquare(boardMap: Map<DigSiteCoord, DigSiteSquare>, location: DigSiteCoord, userId: Long, gridId: Long, currentBrushes: Int)

}
