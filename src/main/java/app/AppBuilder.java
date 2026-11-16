package app;

import ui.IMenuGrafter;
import ui.MGHelpers;
import ui.components.MGButtonGroup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppBuilder {
    private JFrame application = null;
    private final JPanel mainPanel = new JPanel();

    public ArrayList<IMenuGrafter> activeMenuComponents;
    public void addMenuComponent(IMenuGrafter menuComp) {
        activeMenuComponents.add(menuComp);
    }
    public void removeMenuComponent(IMenuGrafter menuComp){
        if(!activeMenuComponents.remove(menuComp))
            System.out.println("Attempted to remove a menu component that is not on the UI");
    }

    public AppBuilder() {
        mainPanel.setLayout(new FlowLayout());
    }

    public JFrame build() {
        application = new JFrame("Set");
        application.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        MGButtonGroup buttonGroup = new MGButtonGroup();
        buttonGroup.graft(mainPanel, MGHelpers.construct(new MGButtonGroup.Params(), p ->
        {
            p.buttons = Map.of(
                    "Start Game", MGHelpers.asAction(this::startGame),
                    "Exit", MGHelpers.asAction(this::exitGame)
            );

            p.pxWidth = 240;
            p.pxHeight = 80;
        }));

        application.add(mainPanel);

        return application;
    }

    private void startGame(){
        System.out.println("Started game!");
    }


    private void exitGame(){
        System.out.println("Ended game!");
        application.dispose();
    }
}
