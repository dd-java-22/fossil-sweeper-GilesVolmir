package edu.cnm.deepdive.fossilsweeper.service.respository;

import edu.cnm.deepdive.fossilsweeper.model.type.SquareState;
import java.util.concurrent.CompletableFuture;

public class DigSiteSquareRepositoryImpl implements DigSiteSquareRepository {

  @Override
  public CompletableFuture<Void> updateStateByCoordinates(int x, int y, SquareState state) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }

  @Override
  public CompletableFuture<SquareState> getStateByCoordinates(int x, int y) {
    throw new UnsupportedOperationException("Not Yet Implemented");
  }
}
