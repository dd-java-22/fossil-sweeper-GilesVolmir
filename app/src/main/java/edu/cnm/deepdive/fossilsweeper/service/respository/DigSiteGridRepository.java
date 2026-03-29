package edu.cnm.deepdive.fossilsweeper.service.respository;

import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.model.dao.DigSiteGridDao;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteGrid;
import java.util.concurrent.CompletableFuture;

public interface DigSiteGridRepository {

  CompletableFuture<DigSiteGrid> getById(long id);

}
