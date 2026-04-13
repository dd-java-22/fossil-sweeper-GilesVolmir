package edu.cnm.deepdive.fossilsweeper.controller;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import edu.cnm.deepdive.fossilsweeper.R;

/**
 * Fragment for displaying and managing application settings. Loads preferences from an XML
 * resource.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.preferences, rootKey);
  }

}
