package edu.cnm.deepdive.fossilsweeper.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.fossilsweeper.R;
import javax.inject.Inject;

@HiltViewModel
public class LoginViewModel extends ViewModel {

  private static final String TAG = LoginViewModel.class.getSimpleName();

  private final MutableLiveData<AuthenticationState> authenticationState;
  private final Application application;
  private final GoogleSignInClient signInClient;

  @Inject
  public LoginViewModel(@NonNull Application application, GoogleSignInClient signInClient) {
    this.application = application;
    this.signInClient = signInClient;
    this.authenticationState = new MutableLiveData<>(AuthenticationState.UNAUTHENTICATED);
  }

  public LiveData<AuthenticationState> getAuthenticationState() {
    return authenticationState;
  }

  public void signIn() {
    // This method should be called from the fragment with an ActivityResultLauncher
    // For now, we'll just update the state to ERROR as a placeholder
    authenticationState.setValue(AuthenticationState.ERROR);
  }

  public void handleSignInResult(Task<GoogleSignInAccount> task) {
    try {
      GoogleSignInAccount account = task.getResult(ApiException.class);
      if (account != null) {
        authenticationState.setValue(AuthenticationState.AUTHENTICATED);
      } else {
        authenticationState.setValue(AuthenticationState.ERROR);
      }
    } catch (ApiException e) {
      Log.e(TAG, "Sign-in failed", e);
      authenticationState.setValue(AuthenticationState.ERROR);
    }
  }

  public Intent getSignInIntent() {
    return signInClient.getSignInIntent();
  }

  public enum AuthenticationState {
    UNAUTHENTICATED,
    AUTHENTICATED,
    ERROR
  }
}
