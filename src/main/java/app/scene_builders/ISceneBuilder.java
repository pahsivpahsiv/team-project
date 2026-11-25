package app.scene_builders;

import javax.swing.*;

public interface ISceneBuilder extends IAppBuilder {
    @Override
    default JFrame getApplication() {
        return getParent().getApplication();
    }
    public JPanel getMainPanel();

    public IAppBuilder getParent();

    public JPanel build();
    public JPanel builderSequence();
}
