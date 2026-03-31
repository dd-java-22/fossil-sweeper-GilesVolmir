package edu.cnm.deepdive.fossilsweeper.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.credentials.CreatePasswordRequest;
import androidx.credentials.CredentialManager;
import androidx.credentials.CustomCredential;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.NoCredentialException;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.fossilsweeper.R;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Inject;

/**
 * ViewModel for handling Google Sign-In authentication using Credential Manager API.
 */
@HiltViewModel
public class LoginViewModel extends ViewModel {

  private static final String TAG = LoginViewModel.class.getSimpleName();

  private final MutableLiveData<AuthenticationState> authenticationState;
  private final Application application;
  private final CredentialManager credentialManager;
  private final Executor executor;

  @Inject
  public LoginViewModel(@NonNull Application application, CredentialManager credentialManager) {
    this.application = application;
    this.credentialManager = credentialManager;
    this.authenticationState = new MutableLiveData<>(AuthenticationState.UNAUTHENTICATED);
    this.executor = Executors.newSingleThreadExecutor();
  }

  public LiveData<AuthenticationState> getAuthenticationState() {
    return authenticationState;
  }

  /**
   * Initiates Google Sign-In flow using Credential Manager.
   * First attempts to get credentials from authorized accounts only.
   * If no authorized accounts exist, retries without the filter.
   */
  public void signIn() {
    getCredentials(true);
  }

  private void getCredentials(boolean filterByAuthorizedAccounts) {
    GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(filterByAuthorizedAccounts)
        .setServerClientId(application.getString(R.string.client_id))
        .build();

    GetCredentialRequest request = new GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build();

    credentialManager.getCredentialAsync(
        application,
        request,
        null,
        executor,
        new androidx.credentials.CredentialManagerCallback<GetCredentialResponse, GetCredentialException>() {
          @Override
          public void onResult(GetCredentialResponse result) {
            handleSignInResult(result);
          }

          @Override
          public void onError(@NonNull GetCredentialException e) {
            handleSignInError(e, filterByAuthorizedAccounts);
          }
        }
    );
  }

  private void handleSignInResult(GetCredentialResponse response) {
    if (response.getCredential() instanceof CustomCredential) {
      CustomCredential customCredential = (CustomCredential) response.getCredential();

      if (GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL.equals(customCredential.getType())) {
        try {
          GoogleIdTokenCredential googleIdTokenCredential =
              GoogleIdTokenCredential.createFrom(customCredential.getData());

          String idToken = googleIdTokenCredential.getId();
          String displayName = googleIdTokenCredential.getDisplayName();
          String email = googleIdTokenCredential.getId();

          Log.d(TAG, "Sign-in successful: " + displayName);
          authenticationState.postValue(AuthenticationState.AUTHENTICATED);
        } catch (GoogleIdTokenParsingException e) {
          Log.e(TAG, "Invalid Google ID token response", e);
          authenticationState.postValue(AuthenticationState.ERROR);
        }
      } else {
        Log.e(TAG, "Unexpected credential type: " + customCredential.getType());
        authenticationState.postValue(AuthenticationState.ERROR);
      }
    } else {
      Log.e(TAG, "Unexpected credential type");
      authenticationState.postValue(AuthenticationState.ERROR);
    }
  }

  private void handleSignInError(GetCredentialException e, boolean wasFilteringByAuthorizedAccounts) {
    if (e instanceof NoCredentialException && wasFilteringByAuthorizedAccounts) {
      Log.d(TAG, "No authorized accounts found, retrying without filter");
      getCredentials(false);
    } else {
      Log.e(TAG, "Sign-in failed", e);
      authenticationState.postValue(AuthenticationState.ERROR);
    }
  }

  public enum AuthenticationState {
    UNAUTHENTICATED,
    AUTHENTICATED,
    ERROR
  }
}
