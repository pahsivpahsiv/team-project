package app.scene_builders;

import javax.swing.*;
import java.lang.reflect.Type; // I swear I'm not using reflection okay no reflection here nope no sir not me

public class SceneLoader {
    public static JFrame activeApplication;
    public static JPanel activeMainPanel;

    // TODO: either move this method to a different class or properly create the application in this method
    public static void EnterApplication(IAppBuilder builder) {
        activeApplication = builder.getApplication();
    }

    public static void EnterScene(ISceneBuilder builder) {
        if(activeMainPanel != null)
            activeApplication.remove(activeMainPanel);
        activeMainPanel = builder.builderSequence();
        activeApplication.add(activeMainPanel);

        activeApplication.revalidate();
        activeApplication.repaint();
    }
}
