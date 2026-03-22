package edu.cnm.deepdive.fossilsweeper.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.fossilsweeper.databinding.FragmentMainBinding;

@AndroidEntryPoint
public class MainFragment extends Fragment {

  private FragmentMainBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentMainBinding.inflate(inflater, container, false);
    // Set up navigation to DigSiteFragment
    binding.playGameButton.setOnClickListener(v ->
        Navigation.findNavController(binding.getRoot())
            .navigate(MainFragmentDirections.navigateToDigSiteFragment()));
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
