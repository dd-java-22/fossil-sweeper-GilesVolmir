package edu.cnm.deepdive.fossilsweeper.service.respository;

import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FossilRepositoryImpl implements FossilRepository {

  @Override
  public CompletableFuture<List<Fossil>> getUnassignedFossils(int limit) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<Fossil> getById(long id) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<Boolean> batchInsert(Collection<Fossil> fossils) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }
}
