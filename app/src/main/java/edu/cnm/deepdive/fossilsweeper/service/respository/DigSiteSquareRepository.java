package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.type.DigSiteSquareState;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing dig site square data. Provides asynchronous and observable
 * methods for retrieving, inserting, and updating game board squares.
 */
public interface DigSiteSquareRepository {

  /**
   * Retrieves all dig site squares for a specific grid as observable live data.
   *
   * @param gridId Grid ID.
   * @return LiveData containing list of all squares in the specified grid.
   */
  LiveData<List<DigSiteSquare>> getAllByGridId(long gridId);

  /**
   * Inserts a batch of dig site squares into the database asynchronously.
   *
   * @param squares Collection of squares to insert.
   * @return CompletableFuture containing list of generated primary key IDs.
   */
  CompletableFuture<List<Long>> insertBatch(Collection<DigSiteSquare> squares);

  /**
   * Updates a single dig site square asynchronously.
   *
   * @param square Square to update.
   * @return CompletableFuture containing the number of rows updated (should be 1).
   */
  CompletableFuture<Integer> update(DigSiteSquare square);

  /**
   * Updates multiple dig site squares in a batch operation asynchronously.
   *
   * @param squares Collection of squares to update.
   * @return CompletableFuture containing the number of rows updated.
   */
  CompletableFuture<Integer> updateBatch(Collection<DigSiteSquare> squares);

  /**
   * Updates the state of a dig site square identified by its coordinates asynchronously.
   *
   * @param gridId Grid ID.
   * @param x X coordinate.
   * @param y Y coordinate.
   * @param state New square state.
   * @return CompletableFuture that completes when the update is done.
   */
  CompletableFuture<Void> updateStateByCoordinates(long gridId, int x, int y, DigSiteSquareState state);

  /**
   * Retrieves the state of a dig site square by its coordinates asynchronously.
   *
   * @param gridId Grid ID.
   * @param x X coordinate.
   * @param y Y coordinate.
   * @return CompletableFuture containing the square state, or {@code null} if not found.
   */
  CompletableFuture<DigSiteSquareState> getStateByCoordinates(long gridId, int x, int y);

}
