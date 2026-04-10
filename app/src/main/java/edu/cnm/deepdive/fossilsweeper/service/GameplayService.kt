package edu.cnm.deepdive.fossilsweeper.service

import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteCoord

interface GameplayService {

    fun startNewDig(): Long

    fun digSquare(gridId: Long, coord: DigSiteCoord)

    fun toggleFenceSquare(gridId: Long, coord: DigSiteCoord)

    fun extractSquare(gridId: Long, coord: DigSiteCoord)

    fun scanSquare(gridId: Long, coord: DigSiteCoord)
}
