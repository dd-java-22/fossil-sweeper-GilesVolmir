package edu.cnm.deepdive.fossilsweeper.controller;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.fossilsweeper.R;
import edu.cnm.deepdive.fossilsweeper.databinding.ActivityMainBinding;

/**
 * Main activity for the Fossil Sweeper application. Manages the navigation host and handles
 * edge-to-edge display with appropriate window insets.
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private NavController navController;
  private AppBarConfiguration barConfig;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    EdgeToEdge.enable(this);
    ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), MainActivity::adjustInsets);
    setupNavigation();
    setContentView(binding.getRoot());
  }

  /**
   * Adjusts view margins to accommodate system window insets (status bar, navigation bar, etc.).
   *
   * @param view The view to adjust.
   * @param insets Window insets from the system.
   * @return WindowInsetsCompat.CONSUMED to indicate insets have been handled.
   */
  private static @NonNull WindowInsetsCompat adjustInsets(
      @NonNull View view, @NonNull WindowInsetsCompat insets) {
    Insets bounds = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
    params.setMargins(bounds.left, bounds.top, bounds.right, bounds.bottom);
    view.setLayoutParams(params);
    return WindowInsetsCompat.CONSUMED;
  }

  @Override
  public boolean onSupportNavigateUp() {
    return NavigationUI.navigateUp(navController, barConfig);
  }

  /**
   * Configures the navigation controller and action bar behavior based on the current destination.
   * Hides the action bar when on the login fragment.
   */
  private void setupNavigation() {
    barConfig = new AppBarConfiguration.Builder(R.id.main_fragment).build();
    NavHostFragment host = binding.navHostFragmentContainer.getFragment();
    navController = host.getNavController();
    navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
      ActionBar bar = getSupportActionBar();
      if (bar != null) {
        if (destination.getId() == R.id.login_fragment) {
          bar.hide();
        } else {
          bar.show();
        }
      }
    });
    NavigationUI.setupActionBarWithNavController(this, navController, barConfig);
  }

}