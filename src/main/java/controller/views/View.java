package controller.views;

import javax.swing.*;

public abstract class View {
    protected JFrame frame;

    public void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        reset();
        frame.setVisible(true);
    }

    public void show() {
        reset();
        frame.setVisible(true);
    }

    public abstract void reset();
}
