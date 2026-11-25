package app.scene_builders;

import ui.components.MGSetCard;
import ui.layout.UIPartition;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GameBuilder implements ISceneBuilder {

    private final AppBuilder parent;
    @Override
    public IAppBuilder getParent() { return parent; }

    public GameBuilder(AppBuilder parent) {
        this.parent = parent;
    }

    private final JPanel mainPanel = new JPanel();

    @Override
    public JPanel getMainPanel() { return mainPanel; }

    private Map<Integer, JPanel> screenRegions;

    public GameBuilder addScreenRegions(){
        var partition = new UIPartition(mainPanel, new int[][]{ // TODO: replace magic numbers
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
        }, UIPartition.ImageType.Default);
        screenRegions = partition.map;

        for (int i : screenRegions.keySet()){
            screenRegions.get(i).setBorder(BorderFactory.createDashedBorder(new RadialGradientPaint(0, 0, 1, new float[] { 0, 1 }, new Color[]{ Color.GRAY, Color.GRAY })));
        }

        return this;
    }

    public GameBuilder addCardArea() {
        var partition = new UIPartition(screenRegions.get(4), new int[][]{
                { 0,  1,  2,  3,  4},
                { 5,  6,  7,  8,  9},
                {10, 11, 12, 13, 14}
        }, UIPartition.ImageType.Default);
        for(int i : partition.map.keySet()) {
            new MGSetCard().graft(partition.map.get(i), null);
        }

        return this;
    }

    @Override
    public JPanel build() {
        return mainPanel;
    }

    @Override
    public JPanel builderSequence() {
        return this
                .addScreenRegions()
                .addCardArea()
                .build();
    }
}
