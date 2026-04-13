package edu.cnm.deepdive.fossilsweeper.controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.fossilsweeper.databinding.FragmentDigSiteBinding;
import edu.cnm.deepdive.fossilsweeper.databinding.LayoutToolItemBinding;
import edu.cnm.deepdive.fossilsweeper.model.pojo.DigSiteGridWithSquares;
import edu.cnm.deepdive.fossilsweeper.viewmodel.GameplayViewModel;
import edu.cnm.deepdive.fossilsweeper.viewmodel.ToolType;

@AndroidEntryPoint
public class DigSiteFragment extends Fragment {

  private FragmentDigSiteBinding binding;
  private GameplayViewModel gameplayViewModel;
  private LiveData<DigSiteGridWithSquares> digSiteGridWithSquares;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentDigSiteBinding.inflate(inflater, container, false);
    for (ToolType tool : ToolType.values()) {
      Drawable icon = getResources().getDrawable(tool.getImageId(), null);
      RadioButton toolButton = (RadioButton) LayoutToolItemBinding.inflate(inflater,
          binding.digToolBar, false).getRoot();
      toolButton.setText(tool.getToolName());
      toolButton.setCompoundDrawablesRelativeWithIntrinsicBounds(null, icon, null, null);
      toolButton.setTag(tool);
      binding.digToolBar.addView(toolButton);
      binding.digToolBar.check(toolButton.getId());
    }
    binding.digToolBar.setOnCheckedChangeListener(
        (group, checkedId) -> {
          if (checkedId == -1) {
            return;
          }
          View checkedView = binding.digToolBar.findViewById(checkedId);
          ToolType tool = (ToolType) checkedView.getTag();
          gameplayViewModel.setCurrentTool(tool);
          Log.d("DigSiteFragment", "Selected tool: " + tool.getToolName());
        }
    );
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    gameplayViewModel = new ViewModelProvider(requireActivity()).get(GameplayViewModel.class);
    digSiteGridWithSquares = gameplayViewModel.getGameLivedata();
    binding.digSite.setOnCellClickListener(
        coord -> {
          gameplayViewModel.handleTapWithCurrentTool(coord);
          Log.d("DigSiteFragment", "Tapped on cell: " + coord);
        });
    Transformations.distinctUntilChanged(
        Transformations.map(
            digSiteGridWithSquares, DigSiteGridWithSquares::getGridSquares))
        .observe(getViewLifecycleOwner(), (gridSquares) ->
            binding.digSite.setGridSquares(gridSquares)
        );
    gameplayViewModel.getRemainingBrushes()
        .observe(getViewLifecycleOwner(), (remainingBrushes) -> {
          if (remainingBrushes != null) {
            binding.brushesCount.setText(String.valueOf(remainingBrushes));
            if (remainingBrushes <= 0) {
              gameOver();
            }
          }
        });
    gameplayViewModel.getScannerCount()
        .observe(getViewLifecycleOwner(), (scannerCount) -> {
          if (scannerCount != null) {
            binding.scannersCount.setText(String.valueOf(scannerCount));
          }
        });
  }

  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }

  private void gameOver() {
    Toast.makeText(requireContext(), "Game Over!", Toast.LENGTH_SHORT).show();
    binding.digSite.setEnabled(false);
  }
}
