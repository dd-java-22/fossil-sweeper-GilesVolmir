package edu.cnm.deepdive.fossilsweeper.controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.widget.RadioGroup;
import com.google.android.material.card.MaterialCardView;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.fossilsweeper.databinding.FragmentDigSiteBinding;
import edu.cnm.deepdive.fossilsweeper.databinding.LayoutToolItemBinding;
import edu.cnm.deepdive.fossilsweeper.viewmodel.GameplayViewModel;
import edu.cnm.deepdive.fossilsweeper.viewmodel.ToolType;

@AndroidEntryPoint
public class DigSiteFragment extends Fragment {

  private FragmentDigSiteBinding binding;
  private GameplayViewModel gameplayViewModel;
//  private final LiveData<DigSiteGridWithSquares> digSiteGridWithSquares;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentDigSiteBinding.inflate(inflater, container, false);
    gameplayViewModel = new ViewModelProvider(requireActivity()).get(GameplayViewModel.class);
    for (ToolType tool : ToolType.values()) {
      Drawable icon = getResources().getDrawable(tool.getImageId(), null);
      LayoutToolItemBinding toolView = LayoutToolItemBinding.inflate(inflater, binding.digToolBar, false);
      toolView.toolImage.setImageDrawable(icon);
      toolView.toolName.setText(tool.getToolName());
      toolView.getRoot().setTag(tool);
      binding.digToolBar.addView(toolView.getRoot());
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
    // TODO: Set up ViewModel and observers
  }

  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }
}
