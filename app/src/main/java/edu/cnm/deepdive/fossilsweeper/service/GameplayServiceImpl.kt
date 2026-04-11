package edu.cnm.deepdive.fossilsweeper.service

import android.util.Log
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteCoord
import edu.cnm.deepdive.fossilsweeper.model.type.DigSiteSquareState
import edu.cnm.deepdive.fossilsweeper.service.respository.DigSiteGridRepository
import edu.cnm.deepdive.fossilsweeper.service.respository.DigSiteSquareRepository
import jakarta.inject.Inject
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors
import java.util.stream.IntStream


class GameplayServiceImpl @Inject constructor(
    private val digSiteGridRepository: DigSiteGridRepository,
    private val digSiteSquareRepository: DigSiteSquareRepository
) : GameplayService {

    val TAG = GameplayServiceImpl::class.java.simpleName

    override fun startNewDig(width: Int, height: Int, density: Int, userId: Long): Long {
        val newDig = DigSiteGrid()
        newDig.playerId = userId
        newDig.width = width
        newDig.height = height
        val numberOfCells = width * height
        newDig.totalFossils = numberOfCells * density / 100
        newDig.remainingBrushes = newDig.totalFossils
        val completableDigSiteId: CompletableFuture<Long> = digSiteGridRepository.insert(newDig)
        val digSiteSquares: Map<DigSiteCoord, DigSiteSquare> = IntStream
            .range(0, numberOfCells)
            .mapToObj {
                val newSquare = DigSiteSquare()
                newSquare.xCoordinate = it / width
                newSquare.yCoordinate = it % width
                newSquare
            }
            .collect(Collectors.toMap({ DigSiteCoord(it.xCoordinate, it.yCoordinate) }, { it }))

        digSiteSquares
            .keys
            .shuffled()
            .take(newDig.totalFossils)
            .forEach {
                digSiteSquares[it]?.isHasFossil = true
                it.getNeighbors().forEach { neighbor ->
                    digSiteSquares[neighbor]?.mooreNeighborFossils++
                }
            }


        completableDigSiteId
            .thenAccept { gridId ->
                digSiteSquares.values.forEach { it.belongingGridId = gridId }
                digSiteSquareRepository.insertBatch(digSiteSquares.values)
                    .exceptionally {
                        Log.e(TAG, "Error inserting DigSiteSquares for new DigSiteGrid", it)
                        null
                    }
            }
            .exceptionally { e ->
                Log.e(TAG, "Error inserting new DigSiteGrid", e)
                null
            }
        return completableDigSiteId.join()
    }

    override fun digSquare(boardMap: Map<DigSiteCoord, DigSiteSquare>, location: DigSiteCoord) {
        val square = boardMap[location] ?: return
        if (square.state == DigSiteSquareState.UNTOUCHED) {
            square.state = DigSiteSquareState.DUG
            digSiteSquareRepository.update(square)
                .exceptionally {
                    Log.e(TAG, "Error updating DigSiteSquare", it)
                    null
                }
            if (square.mooreNeighborFossils == 0) {
                location.neighbors
                    .filter { boardMap.containsKey(it) }
                    .forEach { digSquare(boardMap, it) }
            }
        }
    }

    override fun toggleFenceSquare(square: DigSiteSquare) {
        if (square.state == DigSiteSquareState.UNTOUCHED) {
            square.state = DigSiteSquareState.FENCED
            digSiteSquareRepository.update(square)
                .exceptionally {
                    Log.e(TAG, "Error updating DigSiteSquare", it)
                    null
                }
        } else if (square.state == DigSiteSquareState.FENCED) {
            square.state = DigSiteSquareState.UNTOUCHED
            digSiteSquareRepository.update(square)
                .exceptionally {
                    Log.e(TAG, "Error updating DigSiteSquare", it)
                }
        }
    }

    override fun extractSquare(square: DigSiteSquare) {
        if (square.state == DigSiteSquareState.UNTOUCHED) {
            square.state = DigSiteSquareState.EXTRACTED
            digSiteSquareRepository.update(square)
                .exceptionally {
                    Log.e(TAG, "Error updating DigSiteSquare", it)
                    null
                }
        }
    }

    override fun scanSquare(boardMap: Map<DigSiteCoord, DigSiteSquare>, location: DigSiteCoord) {
        val square = boardMap[location] ?: return

        if (square.state == DigSiteSquareState.UNTOUCHED || square.state == DigSiteSquareState.FENCED) {
            if (square.isHasFossil) {
                extractSquare(square)
            } else {
                digSquare(boardMap, location)
            }
        }
    }
}