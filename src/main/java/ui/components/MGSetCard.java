package ui.components;

import app.ImageService;
import ui.IMenuGrafter;
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
        JPanel card = new JPanel();
        UIPartition cardPartition = new UIPartition(card, new int[][]{
                { 0, 1, 2 },
                { 3, 4, 5 },
                { 6, 7, 8 }
        });

        cardPartition.map.get(4).add(
            new JLabel(
                    new ImageIcon(ImageService.getCardImage(0))
            )
        );
    }
}
