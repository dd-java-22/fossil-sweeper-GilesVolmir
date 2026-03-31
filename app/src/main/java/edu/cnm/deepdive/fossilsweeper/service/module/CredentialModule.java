package edu.cnm.deepdive.fossilsweeper.service.module;

import android.content.Context;
import androidx.credentials.CredentialManager;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

/**
 * Hilt module that provides credential management dependencies.
 */
@Module
@InstallIn(SingletonComponent.class)
public class CredentialModule {

  /**
   * Provides a singleton instance of {@link CredentialManager}.
   *
   * @param context The application context.
   * @return A {@link CredentialManager} instance.
   */
  @Provides
  @Singleton
  public CredentialManager provideCredentialManager(@ApplicationContext Context context) {
    return CredentialManager.create(context);
  }
}
