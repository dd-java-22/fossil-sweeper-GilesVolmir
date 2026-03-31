package edu.cnm.deepdive.fossilsweeper.viewmodel;

import android.app.Activity;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.fossilsweeper.service.respository.GoogleAuthRepository;
import javax.inject.Inject;

/**
 * ViewModel for handling Google Sign-In authentication using GoogleAuthRepository.
 */
@HiltViewModel
public class LoginViewModel extends ViewModel {

  private static final String TAG = LoginViewModel.class.getSimpleName();

  private final MutableLiveData<AuthenticationState> authenticationState;
  private final GoogleAuthRepository authRepository;

  @Inject
  public LoginViewModel(@NonNull GoogleAuthRepository authRepository) {
    this.authRepository = authRepository;
    this.authenticationState = new MutableLiveData<>(AuthenticationState.UNAUTHENTICATED);
  }

  public LiveData<AuthenticationState> getAuthenticationState() {
    return authenticationState;
  }

  /**
   * Initiates Google Sign-In flow using GoogleAuthRepository.
   * First attempts quick sign-in (filtered by authorized accounts).
   * If that fails, falls back to full sign-in flow.
   *
   * @param activity The activity context required for credential flow.
   */
  public void signIn(@NonNull Activity activity) {
    authRepository.signInQuickly(activity)
        .whenComplete((credential, throwable) -> {
          if (throwable != null) {
            Log.d(TAG, "Quick sign-in failed, attempting full sign-in");
            attemptFullSignIn(activity);
          } else {
            handleSignInSuccess(credential);
          }
        });
  }

  private void attemptFullSignIn(@NonNull Activity activity) {
    authRepository.signIn(activity)
        .whenComplete((credential, throwable) -> {
          if (throwable != null) {
            handleSignInError(throwable);
          } else {
            handleSignInSuccess(credential);
          }
        });
  }

  private void handleSignInSuccess(GoogleIdTokenCredential credential) {
    String displayName = credential.getDisplayName();
    String email = credential.getId();
    Log.d(TAG, "Sign-in successful: " + displayName + " (" + email + ")");
    authenticationState.postValue(AuthenticationState.AUTHENTICATED);
  }

  private void handleSignInError(Throwable throwable) {
    if (throwable instanceof GoogleAuthRepository.SignInRequiredException) {
      Log.e(TAG, "Sign-in required", throwable);
    } else {
      Log.e(TAG, "Sign-in failed", throwable);
    }
    authenticationState.postValue(AuthenticationState.ERROR);
  }

  public enum AuthenticationState {
    UNAUTHENTICATED,
    AUTHENTICATED,
    ERROR
  }
}
