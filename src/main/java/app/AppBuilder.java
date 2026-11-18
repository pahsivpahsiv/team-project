package app;

import ui.IMenuGrafter;
import ui.MGHelpers;
import ui.components.MGButtonGroup;
import ui.layout.UIPartition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class AppBuilder {
    private JFrame application = null;
    private final JPanel mainPanel = new JPanel();
    private Map<Integer, JPanel> screenRegions;

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

    public AppBuilder setupMainPanel() {
        mainPanel.setPreferredSize(new Dimension(800, 600));

        return this;
    }

    public AppBuilder addScreenRegions() {
        var partition = new UIPartition(mainPanel, new int[][]{
                {0, 1, 2},
                {3, 4, 4},
                {5, 5, 5},
        });
        screenRegions = partition.map;

        for (int i : screenRegions.keySet()){
            screenRegions.get(i).setBorder(BorderFactory.createDashedBorder(new RadialGradientPaint(0, 0, 1, new float[] { 0, 1 }, new Color[]{ Color.GRAY, Color.GRAY })));
        }

        return this;
    }

    public AppBuilder addControlFlow(){
        MGButtonGroup buttonGroup = new MGButtonGroup();
        screenRegions.get(3).setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonGroup.graft(screenRegions.get(3), MGHelpers.construct(new MGButtonGroup.Params(), p ->
        {
            p.buttons = new HashMap<>(Map.of(
                    "Start Game", MGHelpers.asAction(this::startGame),
                    "Exit", MGHelpers.asAction(this::exitGame)
            ));

            p.axis = BoxLayout.Y_AXIS;
        }));

        return this;
    }

    public JFrame build() {
        application = new JFrame("Set");
        application.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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
