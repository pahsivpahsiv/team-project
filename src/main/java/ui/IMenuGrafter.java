package ui;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * An approach to grafting UI structures (Menu Grafters // MGs) onto parent components.
 * This is useful for complex reused structures with some flexible implementation.
 * Implements a public <b>graft</b> method and an <b>addMargin</b> method
 * @param <T>
 */
public interface IMenuGrafter<T> {
    /**
     * Grafts, with margins, this MG onto the parent.
     * @param parent The JComponent to graft this MG's structure onto
     * @param params MG-specific params that can be implemented in various ways
     * @param border Optional built-in margin parameter; creates a child panel, sets its border, and grafts into it
     */
    public default void graft(JComponent parent, T params, Border border) {
        parent = addBorder(parent, params, border);
        graft(parent, params);
    }

    /**
     * Grafts this MG onto the parent.
     * @param parent The JComponent to graft this MG's structure onto
     * @param params MG-specific params that can be implemented in various ways
     */
    public void graft(JComponent parent, T params);

    /**
     * Adds a child margin panel to the input parent, sets its border, and returns it. Implementation-only.<br>
     * (Unfortunately Java is not a good language and interface methods cannot be non-public,
     * so <b>DO NOT CALL THIS METHOD IF YOU ARE A CONSUMER OF IMenuGrafter!!! 🙈</b>)
     * @param parent The parent to which the panel is grafted.
     * @param params MG-specific params that can be implemented in various ways.
     * @param border The specification for the panel returned by this method.
     * @return A replacement for the "parent" JComponent in the graft method.
     */
    default JPanel addBorder(JComponent parent, T params, Border border) {
        JPanel panel = new JPanel();
        parent.add(panel);
        panel.setComponentOrientation(parent.getComponentOrientation());
        panel.setBorder(border);
        return panel;
    }
}
