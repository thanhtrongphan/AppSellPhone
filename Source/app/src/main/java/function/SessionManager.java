package function;
import android.content.Context;
import android.content.SharedPreferences;
public class SessionManager {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_SESSION_ID = "session_id";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserId(String userId) {
        editor.putString(KEY_USER_ID, userId);
        editor.commit();
    }
    public void saveSessionId(String sessionId) {
        editor.putString(KEY_SESSION_ID, sessionId);
        editor.commit();
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }
    public String getSessionId() {
        return sharedPreferences.getString(KEY_SESSION_ID, null);
    }
    public void clearSession() {
        editor.clear();
        editor.commit();
    }
}
