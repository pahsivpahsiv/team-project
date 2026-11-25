package ui.components;

import app.ImageService;
import ui.IMenuGrafter;
import ui.SVGPanel;
import ui.layout.UIPartition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class MGSetCard implements IMenuGrafter<MGSetCard.Params> {
    public static class Params {
        public int[] setParameters;
    }

    @Override
    public void graft(JComponent parent, Params params) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        parent.setLayout(layout);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 1;
        constraints.gridy = 1;

        JPanel card = new JPanel();
        UIPartition cardPartition = new UIPartition(card, new int[][]{
                { 0, 1, 2 },
                { 3, 4, 5 },
                { 6, 7, 8 }
        }, UIPartition.ImageType.SVG);
//        cardPartition.map.get(4).setBackground(Color.BLUE);

        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}) {
            ((SVGPanel)cardPartition.map.get(i)).setDocument( // TODO: avoid this cast
                ImageService.getCardImage(3)
            );
        }

        parent.add(card, constraints);
    }
}
