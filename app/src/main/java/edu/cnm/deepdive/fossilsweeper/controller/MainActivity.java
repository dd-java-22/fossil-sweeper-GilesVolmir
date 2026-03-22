package edu.cnm.deepdive.fossilsweeper.controller;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import edu.cnm.deepdive.fossilsweeper.R;
import edu.cnm.deepdive.fossilsweeper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

  ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    EdgeToEdge.enable(this);
    setContentView(binding.getRoot());
    ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), MainActivity::adjustInsets);
  }

  private static @NonNull WindowInsetsCompat adjustInsets(
      @NonNull View view, @NonNull WindowInsetsCompat insets) {
    Insets bounds = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
    params.setMargins(bounds.left, bounds.top, bounds.right, bounds.bottom);
    view.setLayoutParams(params);
    return WindowInsetsCompat.CONSUMED;
  }

}