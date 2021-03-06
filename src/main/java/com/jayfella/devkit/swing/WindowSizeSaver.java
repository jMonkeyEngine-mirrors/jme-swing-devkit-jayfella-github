package com.jayfella.devkit.swing;

import com.jayfella.devkit.config.DevKitConfig;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowSizeSaver implements ComponentListener {

    private final String windowId;

    public WindowSizeSaver(String windowId) {
        this.windowId = windowId;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Window window = (Window) e.getComponent();
        Dimension size = window.getSize();

        DevKitConfig.getInstance().getSdkConfig().setWindowDimensions(windowId, size);
        DevKitConfig.getInstance().save();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
