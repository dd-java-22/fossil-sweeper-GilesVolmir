package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteGridDao;
import edu.cnm.deepdive.fossilsweeper.model.type.SquareState;
import java.util.concurrent.CompletableFuture;

public class DigSiteSquareRepositoryImpl implements DigSiteSquareRepository {

  private final DigSiteGridDao digSiteGridDao;
  private final Context context;

  public DigSiteSquareRepositoryImpl(DigSiteGridDao digSiteGridDao, @ApplicationContext Context context) {
    this.digSiteGridDao = digSiteGridDao;
    this.context = context;
  }

  @Override
  public CompletableFuture<Void> updateStateByCoordinates(int x, int y, SquareState state) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<SquareState> getStateByCoordinates(int x, int y) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }
}
