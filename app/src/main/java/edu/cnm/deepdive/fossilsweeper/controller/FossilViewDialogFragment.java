package edu.cnm.deepdive.fossilsweeper.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.fossilsweeper.databinding.FragmentFossilViewDialogBinding;

/**
 * Full-screen dialog fragment for displaying detailed information about a fossil.
 * Shows the fossil image, scientific name, geological era, and other relevant details.
 */
@AndroidEntryPoint
public class FossilViewDialogFragment extends DialogFragment {

  public static final String TAG = "FossilViewDialogFragment";

  private FragmentFossilViewDialogBinding binding;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Set full-screen dialog style
    setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_NoActionBar);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentFossilViewDialogBinding.inflate(inflater, container, false);

    // Set up close button
    binding.toolbar.setNavigationOnClickListener((v) -> dismiss());

    // TODO: Get fossil data from arguments
    // TODO: Load fossil image from URI
    // TODO: Populate TextViews with fossil information

    return binding.getRoot();
  }

  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }
}
