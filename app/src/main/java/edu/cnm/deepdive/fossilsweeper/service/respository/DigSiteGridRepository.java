package edu.cnm.deepdive.fossilsweeper.service.respository;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import java.util.concurrent.CompletableFuture;

public interface DigSiteGridRepository {

  LiveData<DigSiteGrid> getById(long id);

  CompletableFuture<DigSiteSquare> getSquareByCoordinates(int x, int y);

}
