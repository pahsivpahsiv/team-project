package app;

import javax.swing.*;

public class Main {
    /**
     * 🐐
     * @param args
     */
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .setupMainPanel()
                .addScreenRegions()
                .addControlFlow()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
