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
import com.google.android.material.snackbar.Snackbar;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.fossilsweeper.R;
import edu.cnm.deepdive.fossilsweeper.databinding.FragmentLoginBinding;
import edu.cnm.deepdive.fossilsweeper.viewmodel.LoginViewModel;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

  private FragmentLoginBinding binding;
  private LoginViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentLoginBinding.inflate(inflater, container, false);
    binding.signInButton.setOnClickListener((v) -> viewModel.signIn(requireActivity()));
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
    viewModel.getAuthenticationState().observe(getViewLifecycleOwner(), (state) -> {
      switch (state) {
        case AUTHENTICATED:
          Navigation.findNavController(binding.getRoot())
              .navigate(LoginFragmentDirections.navigateToMainFragment());
          break;
        case ERROR:
          Snackbar.make(binding.getRoot(), R.string.login_error_message, Snackbar.LENGTH_LONG)
              .show();
          break;
        case UNAUTHENTICATED:
          // Do nothing, stay on login screen
          break;
      }
    });
//   viewModel.signIn(requireActivity());
  }

  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }
}
