package edu.cnm.deepdive.fossilsweeper.service.respository

import android.app.Activity
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import java.util.concurrent.CompletableFuture

/**
 * Repository interface for managing Google authentication operations. Provides methods for signing
 * in, refreshing tokens, and signing out users.
 */
interface GoogleAuthRepository {

    /**
     * Attempts a quick sign-in using cached credentials without user interaction.
     *
     * @param activity The activity context for the sign-in operation.
     * @return CompletableFuture containing the Google ID token credential.
     */
    fun signInQuickly(activity: Activity): CompletableFuture<GoogleIdTokenCredential>

    /**
     * Initiates a full sign-in flow with user interaction.
     *
     * @param activity The activity context for the sign-in operation.
     * @return CompletableFuture containing the Google ID token credential.
     */
    fun signIn(activity: Activity): CompletableFuture<GoogleIdTokenCredential>

    /**
     * Refreshes an expired authentication token.
     *
     * @param activity The activity context for the refresh operation.
     * @param credential The current credential to refresh.
     * @return CompletableFuture containing the refreshed Google ID token credential.
     */
    fun refreshToken(activity: Activity, credential: GoogleIdTokenCredential): CompletableFuture<GoogleIdTokenCredential>

    /**
     * Signs out the current user and clears cached credentials.
     *
     * @return CompletableFuture that completes when sign-out is finished.
     */
    fun signOut(): CompletableFuture<Void?>

    /**
     * Gets the OAuth key for the last signed-in user.
     *
     * @return The OAuth key, or null if no user has signed in.
     */
    fun getLastOauthKey(): String?

    /**
     * Exception thrown when sign-in is required but cached credentials are unavailable.
     *
     * @property message The exception message.
     * @property cause The underlying cause of the exception.
     */
    class SignInRequiredException(message: String, cause: Throwable) : RuntimeException(message, cause)
}