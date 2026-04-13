package edu.cnm.deepdive.fossilsweeper.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;
import dagger.hilt.InstallIn;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.fossilsweeper.R;
import edu.cnm.deepdive.fossilsweeper.model.entity.DigSiteSquare;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteCoord;
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteGridWithSquares;
import edu.cnm.deepdive.fossilsweeper.service.GameplayService;
import edu.cnm.deepdive.fossilsweeper.service.respository.DigSiteGridRepository;
import edu.cnm.deepdive.fossilsweeper.service.respository.GoogleAuthRepository;
import edu.cnm.deepdive.fossilsweeper.service.respository.UserProfileRepository;
import jakarta.inject.Inject;
import java.util.Map;

@HiltViewModel
public class GameplayViewModel extends ViewModel {

  private final GameplayService gameplayService;
  private final UserProfile currentUser;
  private LiveData<DigSiteGridWithSquares> digSiteGridWithSquares;
  private final DigSiteGridRepository digSiteGridRepository;
  private final UserProfileRepository userProfileRepository;
  private final Context context;
  private final SharedPreferences prefs;
  private ToolType currentTool = ToolType.DIG;
  private LiveData<Integer> remainingBrushes;
  private LiveData<Integer> scannerCount;

  @Inject
  public GameplayViewModel(GameplayService gameplayService,
      DigSiteGridRepository digSiteGridRepository,
      GoogleAuthRepository authRepo,
      UserProfileRepository userRepo,
      @ApplicationContext Context context) {
    this.gameplayService = gameplayService;
    this.digSiteGridRepository = digSiteGridRepository;
    this.userProfileRepository = userRepo;
    this.context = context;
    currentUser = userRepo.getByOauthKey(authRepo.getLastOauthKey()).join();
    prefs = PreferenceManager.getDefaultSharedPreferences(context);
  }

  public void continueOrStartNewGame() {
    digSiteGridWithSquares = digSiteGridRepository.
        getMostRecentDigSiteGridWithSquaresByPlayerId(currentUser.getId());
    if (digSiteGridWithSquares.getValue() == null) {
      Resources contextResources = context.getResources();
      int boardWidth = prefs.getInt(contextResources.getString(R.string.board_width_pref_key),
          contextResources.getInteger(R.integer.board_width_pref_default));
      int boardHeight = prefs.getInt(contextResources.getString(R.string.board_height_pref_key),
          contextResources.getInteger(R.integer.board_height_pref_default));
      int boardFossilDensity = prefs.getInt(
          contextResources.getString(R.string.board_fossil_density_pref_key),
          contextResources.getInteger(R.integer.board_fossil_density_pref_default));
      long newGameId = gameplayService.startNewDig(boardWidth, boardHeight, boardFossilDensity,
          currentUser.getId());
      digSiteGridWithSquares = digSiteGridRepository.getDigSiteGridWithSquaresById(newGameId);
    }
    remainingBrushes = Transformations.map(digSiteGridWithSquares,
        DigSiteGridWithSquares::getRemainingBrushes);
    scannerCount = userProfileRepository.getScanners(currentUser.getId());
  }

  public LiveData<DigSiteGridWithSquares> getGameLivedata() {
    return digSiteGridWithSquares;
  }

  public void handleTapWithCurrentTool(DigSiteCoord coord) {
    Log.d("GameplayViewModel", "Handling with tool: " + currentTool + " at coord: " + coord);
    DigSiteGridWithSquares game = digSiteGridWithSquares.getValue();
    if (game == null) {
      return;
    }
    Map<DigSiteCoord, DigSiteSquare> gridSquaresMap = game.getGridSquares();
    DigSiteSquare square = gridSquaresMap.get(coord);
    if (square == null) {
      return;
    }
    switch (currentTool) {
      case DIG -> gameplayService.digSquare(gridSquaresMap, coord);
      case EXTRACT -> gameplayService.extractSquare(square, game.getId(), game.getRemainingBrushes());
      case FENCE -> gameplayService.toggleFenceSquare(square);
      case SCAN -> gameplayService.scanSquare(gridSquaresMap, coord, currentUser.getId(), game.getId(), game.getRemainingBrushes());
    }
  }

  public void setCurrentTool(ToolType currentTool) {
    this.currentTool = currentTool;
  }

  public LiveData<Integer> getRemainingBrushes() {
    return remainingBrushes;
  }

  public LiveData<Integer> getScannerCount() {
    return scannerCount;
  }
}
