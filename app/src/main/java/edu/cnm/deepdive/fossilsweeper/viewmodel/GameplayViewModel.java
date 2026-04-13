package edu.cnm.deepdive.fossilsweeper.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;
import dagger.hilt.InstallIn;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.fossilsweeper.R;
import edu.cnm.deepdive.fossilsweeper.model.entity.UserProfile;
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
  private final Context context;
  private SharedPreferences prefs;
  private ToolType currentTool;

  @Inject
  public GameplayViewModel(GameplayService gameplayService,
      DigSiteGridRepository digSiteGridRepository,
      GoogleAuthRepository authRepo,
      UserProfileRepository userRepo,
      @ApplicationContext Context context) {
    this.gameplayService = gameplayService;
    this.digSiteGridRepository = digSiteGridRepository;
    this.context = context;
    currentUser = userRepo.getByOauthKey(authRepo.getLastOauthKey()).join();
    prefs = PreferenceManager.getDefaultSharedPreferences(context);
    digSiteGridWithSquares = digSiteGridRepository.
        getMostRecentDigSiteGridWithSquaresByPlayerId(currentUser.getId());
    // HACK this should really be something cleaner,  it will break if there's no games yet.
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
      int boardFossilDensity = prefs.getInt(contextResources.getString(R.string.board_fossil_density_pref_key),
          contextResources.getInteger(R.integer.board_fossil_density_pref_default));
      long newGameId = gameplayService.startNewDig(boardWidth, boardHeight, boardFossilDensity, currentUser.getId());
      digSiteGridWithSquares = digSiteGridRepository.getDigSiteGridWithSquaresById(newGameId);
    }
  }

  public void setCurrentTool(ToolType currentTool) {
    this.currentTool = currentTool;
  }
}
