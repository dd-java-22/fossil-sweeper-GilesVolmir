package edu.cnm.deepdive.fossilsweeper.service.respository;

import edu.cnm.deepdive.fossilsweeper.model.type.SquareState;
import java.util.concurrent.CompletableFuture;

public interface DigSiteSquareRepository {

  CompletableFuture<Void> updateStateByCoordinates(int x, int y, SquareState state);

  CompletableFuture<SquareState> getStateByCoordinates(int x, int y);

}
