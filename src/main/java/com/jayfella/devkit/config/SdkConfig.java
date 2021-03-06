package com.jayfella.devkit.config;

import com.jayfella.devkit.forms.MainPage;
import com.jayfella.devkit.service.PropertyInspectorService;
import com.jayfella.devkit.service.SceneTreeService;

import java.awt.*;
import java.beans.Transient;
import java.util.HashMap;

public class SdkConfig {

    private String theme = "com.github.weisj.darklaf.theme.DarculaTheme";
    private String defaultMaterial = "Common/MatDefs/Light/PBRLighting.j3md";

    private boolean showCamRotationWidget = true;
    private boolean showDebugLightsWindow = false;
    private boolean showRunAppStateWindow = false;

    private HashMap<String, Dimension> windowDimensions = new HashMap<>();
    private HashMap<String, Point> windowLocations = new HashMap<>();

    public SdkConfig() {

        // default values

        // Dimensions
        windowDimensions.putIfAbsent(MainPage.WINDOW_ID, new Dimension(800, 600));
        windowDimensions.putIfAbsent(SceneTreeService.WINDOW_ID, new Dimension(250, 600));
        windowDimensions.putIfAbsent(PropertyInspectorService.WINDOW_ID, new Dimension(250, 600));

        // Locations
        // The main window will be placed in the center of the screen..

        // put the scene tree in the top left if there is no value.
        windowLocations.putIfAbsent(SceneTreeService.WINDOW_ID, new Point(0, 0));

        // put the propertyInspector in the top-right if there is no value.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPropInspector = (int) (screenSize.getWidth() - 250);

        windowLocations.putIfAbsent(PropertyInspectorService.WINDOW_ID, new Point(xPropInspector, 0));
    }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public HashMap<String, Dimension> getWindowDimensions() { return windowDimensions; }
    public void setWindowDimensions(HashMap<String, Dimension> windowDimensions) { this.windowDimensions = windowDimensions; }

    public HashMap<String, Point> getWindowLocations() { return windowLocations; }
    public void setWindowLocations(HashMap<String, Point> windowLocations) { this.windowLocations = windowLocations; }

    public boolean isShowCamRotationWidget() { return showCamRotationWidget; }
    public void setShowCamRotationWidget(boolean showCamRotationWidget) { this.showCamRotationWidget = showCamRotationWidget; }

    public boolean isShowDebugLightsWindow() { return showDebugLightsWindow; }
    public void setShowDebugLightsWindow(boolean showDebugLightsWindow) { this.showDebugLightsWindow = showDebugLightsWindow; }

    public boolean isShowRunAppStateWindow() { return showRunAppStateWindow; }
    public void setShowRunAppStateWindow(boolean showRunAppStateWindow) { this.showRunAppStateWindow = showRunAppStateWindow; }

    public String getDefaultMaterial() { return defaultMaterial; }
    public void setDefaultMaterial(String defaultMaterial) { this.defaultMaterial = defaultMaterial; }

    @Transient
    public Point getWindowLocation(String name) {
        return windowLocations.get(name);
    }

    @Transient
    public void setWindowLocation(String name, Point point) {
        windowLocations.put(name, point);
    }

    @Transient
    public Dimension getWindowDimensions(String name) {
        return windowDimensions.get(name);
    }

    @Transient
    public void setWindowDimensions(String name, Dimension dimension) {
        windowDimensions.put(name, dimension);
    }



}
