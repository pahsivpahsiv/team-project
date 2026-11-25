package app;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.jthemedetecor.OsThemeDetector;

public class FlatLafSetup {
    public static void init(){
        final OsThemeDetector detector = OsThemeDetector.getDetector();
        changeTheme(detector.isDark());
        detector.registerListener(FlatLafSetup::changeTheme);
    }

    private static void changeTheme(Boolean isDark) {
        if (isDark) {
            // The OS switched to a dark theme
            FlatMacDarkLaf.setup();
        } else {
            // The OS switched to a light theme
            FlatMacLightLaf.setup();
        }
    }
}
