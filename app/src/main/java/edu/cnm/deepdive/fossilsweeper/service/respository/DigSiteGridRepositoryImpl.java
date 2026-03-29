package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteGridDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import jakarta.inject.Inject;
import java.util.concurrent.CompletableFuture;

public class DigSiteGridRepositoryImpl implements DigSiteGridRepository {

  private final DigSiteGridDao dao;
  private final Context context;

  @Inject
  DigSiteGridRepositoryImpl(DigSiteGridDao dao, @ApplicationContext Context context) {
    this.dao = dao;
    this.context = context;
  }


  @Override
  public CompletableFuture<DigSiteGrid> getById(long id) {
    throw new UnsupportedOperationException("not yet implemented");
  }
}
