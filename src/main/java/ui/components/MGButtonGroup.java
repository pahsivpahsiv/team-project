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
        // Has to be a hashmap or the buttons can be reordered arbitrarily
        public HashMap<String, ActionListener> buttons;
        public int axis = BoxLayout.Y_AXIS;
        public int spacing = 10;
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

        for(var x : params.buttons.keySet()) {
            JButton button = new JButton(x);
            button.addActionListener(params.buttons.get(x));
            parent.add(button, constraints);
            if(params.axis == BoxLayout.Y_AXIS) constraints.gridy++;
            if(params.axis == BoxLayout.X_AXIS) constraints.gridx++;
        }
    }
}
