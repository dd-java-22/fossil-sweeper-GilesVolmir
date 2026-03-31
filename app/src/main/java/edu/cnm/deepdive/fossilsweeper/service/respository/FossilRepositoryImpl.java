package edu.cnm.deepdive.fossilsweeper.service.respository;

import edu.cnm.deepdive.fossilsweeper.model.dao.FossilDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FossilRepositoryImpl implements FossilRepository {

  private final FossilDao fossilDao;

  @Inject
  FossilRepositoryImpl(FossilDao fossilDao) {
    this.fossilDao = fossilDao;
  }

  @Override
  public CompletableFuture<List<Fossil>> getUnassignedFossils(int limit) {
    return CompletableFuture.supplyAsync(() -> fossilDao.selectUnassigned(limit));
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
