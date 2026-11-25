package app.scene_builders;

import ui.layout.UIPartition;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AppBuilder implements IAppBuilder {
    private JFrame application = null;
    @Override
    public JFrame getApplication() {
        return application;
    }

    public AppBuilder createApplication(){
        application = new JFrame("Set");
        application.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        return this;
    }

    /**
     * Adds basic size parameters to the application
     * @return Builder method
     */
    public AppBuilder setupApplication() {
        application.setPreferredSize(new Dimension(800, 600)); // TODO: make actual size, add fullscreen

        return this;
    }

    public void addRelationalHarms() {
        // TODO: implement sexism?
        // TODO: HR violations
        // TODO: remove this before the presentation
    }

    public JFrame build() {
        return application;
    }


    public void startGame(){
        System.out.println("Started game!");
        GameBuilder gameBuilder = new GameBuilder(this);
        SceneLoader.EnterScene(gameBuilder);
    }


    public void exitGame(){
        System.out.println("Ended game!");
        application.dispose();
    }
}
