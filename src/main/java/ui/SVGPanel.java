package ui;

import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.view.ViewBox;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

// Taken directly from https://github.com/weisJ/jsvg#basic-swing
public class SVGPanel extends JPanel {
    private SVGDocument document;
    public void setDocument(SVGDocument document) {
        this.document = document;
    }

    public SVGPanel(SVGDocument document) {
        this.document = document;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(document == null) return;
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
        document.render(this, (Graphics2D) g, new ViewBox(0, 0, getWidth(), getHeight()));
    }
}
