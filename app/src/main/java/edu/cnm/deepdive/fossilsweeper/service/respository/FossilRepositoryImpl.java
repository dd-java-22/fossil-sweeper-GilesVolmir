package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.FossilDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.Fossil;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FossilRepositoryImpl implements FossilRepository {

  private final FossilDao fossilDao;
  private final Context context;
  private final Executor executor;

  @Inject
  FossilRepositoryImpl(FossilDao fossilDao, @ApplicationContext Context context) {
    this.fossilDao = fossilDao;
    this.context = context;
    this.executor = Executors.newFixedThreadPool(4);
  }

  @Override
  public CompletableFuture<List<Fossil>> getUnassignedFossils(int limit) {
    return CompletableFuture.supplyAsync(() -> {
      List<Fossil> fossils = new ArrayList<>();
      for (int i = 0; i < limit; i++) {
        Fossil fossil = fossilDao.selectRandom();
        if (fossil != null) {
          fossils.add(fossil);
        }
      }
      return fossils;
    }, executor);
  }

  @Override
  public CompletableFuture<Fossil> getById(long id) {
    return CompletableFuture.supplyAsync(() -> fossilDao.selectById(id), executor);
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
    }, executor);
  }
}
