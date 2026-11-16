package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class MGHelpers {
    public static Action asAction(Runnable r){
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r.run();
            }
        };
    }

    public static <T> T construct(T target, Consumer<T> construction){
        construction.accept(target);
        return target;
    }
}
