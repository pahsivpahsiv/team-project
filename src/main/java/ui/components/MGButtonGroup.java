package ui.components;

import ui.IMenuGrafter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MGButtonGroup implements IMenuGrafter<MGButtonGroup.Params> {
    public static class Params {
        public Map<String, ActionListener> buttons;
        public int pxWidth, pxHeight;
    }

    @Override
    public void graft(JComponent parent, Params params) {
        for(var x : params.buttons.keySet()) {
            JButton button = new JButton(x);
            button.setPreferredSize(new Dimension(params.pxWidth, params.pxHeight));
            button.addActionListener(params.buttons.get(x));
            parent.add(button);
        }
    }
}
