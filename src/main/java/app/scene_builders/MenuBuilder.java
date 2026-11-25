package app.scene_builders;

import ui.MGHelpers;
import ui.components.MGButtonGroup;
import ui.components.MGSetCard;
import ui.layout.UIPartition;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MenuBuilder implements ISceneBuilder {
    private final AppBuilder parent;
    @Override
    public IAppBuilder getParent() { return parent; }

    public MenuBuilder(AppBuilder parent) {
        this.parent = parent;
    }

    private final JPanel mainPanel = new JPanel();

    @Override
    public JPanel getMainPanel() { return mainPanel; }

    private Map<Integer, JPanel> screenRegions;

    public MenuBuilder addScreenRegions(){
        var partition = new UIPartition(mainPanel, new int[][]{ // TODO: replace magic numbers
                {0, 1, 1, 2},
                {3, 4, 4, 4},
                {5, 5, 5, 5},
        }, UIPartition.ImageType.Default);
        screenRegions = partition.map;

        for (int i : screenRegions.keySet()){
            screenRegions.get(i).setBorder(BorderFactory.createDashedBorder(new RadialGradientPaint(0, 0, 1, new float[] { 0, 1 }, new Color[]{ Color.GRAY, Color.GRAY })));
        }

        return this;
    }

    /**
     * Adds the basic menu control flow buttons (including start/exit game)
     * @return
     */
    public MenuBuilder addControlFlow(){
        MGButtonGroup buttonGroup = new MGButtonGroup();
        screenRegions.get(3).setLayout(new FlowLayout(FlowLayout.LEFT)); // TODO: Replace "3" with a static final var
        buttonGroup.graft(screenRegions.get(3), MGHelpers.construct(new MGButtonGroup.Params(), p ->
        {
            p.buttons = new HashMap<>(Map.of(
                    "Start Game", MGHelpers.asAction(parent::startGame),
                    "Exit", MGHelpers.asAction(parent::exitGame)
            ));

            p.axis = BoxLayout.Y_AXIS;
        }));

        return this;
    }

    public MenuBuilder addTestCard(){
        MGSetCard setCard = new MGSetCard();
        setCard.graft(screenRegions.get(0), new MGSetCard.Params());

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
                .addControlFlow()
                .addTestCard()
                .build();
    }
}
