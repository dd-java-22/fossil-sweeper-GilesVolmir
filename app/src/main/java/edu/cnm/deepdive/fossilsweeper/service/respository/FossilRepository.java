package edu.cnm.deepdive.fossilsweeper.service.respository;

import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing fossil reference data. Provides asynchronous methods for
 * retrieving and inserting fossil types.
 */
public interface FossilRepository {

  /**
   * Retrieves a random set of unassigned fossils (not yet associated with any collected fossil)
   * asynchronously.
   *
   * @param limit Maximum number of fossils to retrieve.
   * @return CompletableFuture containing list of unassigned fossils.
   */
  CompletableFuture<List<Fossil>> getUnassignedFossils(int limit);

  /**
   * Retrieves a fossil by its primary key ID asynchronously.
   *
   * @param id Fossil ID.
   * @return CompletableFuture containing the fossil, or {@code null} if not found.
   */
  CompletableFuture<Fossil> getById(long id);

  /**
   * Inserts a batch of fossils into the database asynchronously.
   *
   * @param fossils Collection of fossils to insert.
   * @return CompletableFuture containing {@code true} if insert was successful, {@code false}
   *     otherwise.
   */
  CompletableFuture<Boolean> batchInsert(Collection<Fossil> fossils);
}
