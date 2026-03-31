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
import edu.cnm.deepdive.fossilsweeper.databinding.FragmentFossilGalleryBinding;

@AndroidEntryPoint
public class FossilGalleryFragment extends Fragment {

  private FragmentFossilGalleryBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentFossilGalleryBinding.inflate(inflater, container, false);
    // Set up navigation to MainFragment
    binding.backButton.setOnClickListener(v ->
        Navigation.findNavController(binding.getRoot())
            .navigate(FossilGalleryFragmentDirections.navigateToMainFragment()));
    // Set up test button for fossil dialog (with example fossil ID)
    binding.testFossilDialogButton.setOnClickListener(v ->
        Navigation.findNavController(binding.getRoot())
            .navigate(FossilGalleryFragmentDirections.navigateToFossilViewDialog()));
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
