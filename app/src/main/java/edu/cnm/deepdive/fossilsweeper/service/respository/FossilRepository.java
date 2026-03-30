package edu.cnm.deepdive.fossilsweeper.service.respository;

import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FossilRepository {

  CompletableFuture<List<Fossil>> getUnassignedFossils(int limit);

  CompletableFuture<Fossil> getById(long id);

  CompletableFuture<Boolean> batchInsert(Collection<Fossil> fossils);
}
