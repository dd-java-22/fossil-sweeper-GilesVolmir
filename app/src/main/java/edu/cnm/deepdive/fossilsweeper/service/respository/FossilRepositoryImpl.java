package edu.cnm.deepdive.fossilsweeper.service.respository;

import edu.cnm.deepdive.fossilsweeper.model.dao.FossilDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of {@link FossilRepository} that delegates to the corresponding DAO for database
 * operations.
 */
public class FossilRepositoryImpl implements FossilRepository {

  private final FossilDao fossilDao;

  /**
   * Constructs a FossilRepositoryImpl with the specified DAO.
   *
   * @param fossilDao DAO for database operations.
   */
  @Inject
  FossilRepositoryImpl(FossilDao fossilDao) {
    this.fossilDao = fossilDao;
  }

  @Override
  public CompletableFuture<List<Fossil>> getUnassignedFossils(int limit) {
    return CompletableFuture.supplyAsync(() -> fossilDao.selectRandomUnassigned(limit));
  }

  @Override
  public CompletableFuture<Fossil> getById(long id) {
    return CompletableFuture.supplyAsync(() -> fossilDao.selectById(id));
  }

  @Override
  public CompletableFuture<Boolean> batchInsert(Collection<Fossil> fossils) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        fossilDao.insert(fossils);
        return true;
      } catch (Exception e) {
        return false;
      }
    });
  }
}
