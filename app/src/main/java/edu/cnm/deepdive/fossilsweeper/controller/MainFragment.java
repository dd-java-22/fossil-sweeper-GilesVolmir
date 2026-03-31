package edu.cnm.deepdive.fossilsweeper.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.fossilsweeper.databinding.FragmentMainBinding;
import edu.cnm.deepdive.fossilsweeper.viewmodel.LoginViewModel;

@AndroidEntryPoint
public class MainFragment extends Fragment {

  private FragmentMainBinding binding;
  private LoginViewModel loginViewModel;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentMainBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
    binding.playGameButton.setOnClickListener((v) ->
        Navigation.findNavController(binding.getRoot())
            .navigate(MainFragmentDirections.navigateToDigSiteFragment()));
    binding.fossilGalleryButton.setOnClickListener((v) ->
        Navigation.findNavController(binding.getRoot())
            .navigate(MainFragmentDirections.navigateToFossilGalleryFragment()));
    binding.logoutButton.setOnClickListener((v) -> {
      loginViewModel.signOut();
      Navigation.findNavController(binding.getRoot())
          .navigate(MainFragmentDirections.navigateToLoginFragment());
    });
  }

  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }
}
