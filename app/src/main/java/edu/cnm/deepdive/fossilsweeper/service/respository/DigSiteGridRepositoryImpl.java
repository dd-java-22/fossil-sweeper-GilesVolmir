package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteGridDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.concurrent.CompletableFuture;

@Singleton
public class DigSiteGridRepositoryImpl implements DigSiteGridRepository {

  private final DigSiteGridDao dao;
  private final Context context;

  @Inject
  DigSiteGridRepositoryImpl(DigSiteGridDao dao, @ApplicationContext Context context) {
    this.dao = dao;
    this.context = context;
  }

  @Override
  public LiveData<DigSiteGrid> getById(long id) {
    throw new UnsupportedOperationException("not yet implemented");
  }

  @Override
  public CompletableFuture<DigSiteSquare> getSquareByCoordinates(int x, int y) {
    throw new UnsupportedOperationException("not yet implemented");
  }
}
