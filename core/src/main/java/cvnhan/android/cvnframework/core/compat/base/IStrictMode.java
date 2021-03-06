package cvnhan.android.cvnframework.core.compat.base;

/**
 * This Interface definition allows you to create OS version-specific
 * implementations that offer the full Strict Mode functionality
 * available in each platform release.
 */
public interface IStrictMode {
    /**
     * Enable {@link android.os.StrictMode} using whichever platform-specific flags you wish.
     */
    public void enableStrictMode();
}
