package io.github.lucariatias.harmonicmoon.tile;

import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tile {

    private WorldPanel worldPanel;

    private List<WorldLocation> backLocations = new ArrayList<>();
    private List<WorldLocation> backTopLocations = new ArrayList<>();
    private List<WorldLocation> frontLocations = new ArrayList<>();
    private List<WorldLocation> frontTopLocations = new ArrayList<>();
    private Image image;

    public Tile(WorldPanel worldPanel, Image image) {
        this.worldPanel = worldPanel;
        this.image = image;
    }

    public void renderBack(Graphics graphics) {
        for (WorldLocation location : backLocations) {
            if (worldPanel.getCamera().getLocation().distanceSquared(location) > 640000) continue;
            if (!(location.getX() >= worldPanel.getCamera().getLocation().getX() - 16 && location.getY() >= worldPanel.getCamera().getLocation().getY() - 16)) continue;
            graphics.drawImage(image, location.getX(), location.getY(), null);
        }
    }

    public void renderBackTop(Graphics graphics) {
        for (WorldLocation location : backTopLocations) {
            if (worldPanel.getCamera().getLocation().distanceSquared(location) > 640000) continue;
            if (!(location.getX() >= worldPanel.getCamera().getLocation().getX() - 16 && location.getY() >= worldPanel.getCamera().getLocation().getY() - 16)) continue;
            graphics.drawImage(image, location.getX(), location.getY(), null);
        }
    }

    public void renderFront(Graphics graphics) {
        for (WorldLocation location : frontLocations) {
            if (worldPanel.getCamera().getLocation().distanceSquared(location) > 640000) continue;
            if (!(location.getX() >= worldPanel.getCamera().getLocation().getX() - 16 && location.getY() >= worldPanel.getCamera().getLocation().getY() - 16)) continue;
            graphics.drawImage(image, location.getX(), location.getY(), null);
        }
    }

    public void renderFrontTop(Graphics graphics) {
        for (WorldLocation location : frontTopLocations) {
            if (worldPanel.getCamera().getLocation().distanceSquared(location) > 640000) continue;
            if (!(location.getX() >= worldPanel.getCamera().getLocation().getX() - 16 && location.getY() >= worldPanel.getCamera().getLocation().getY() - 16)) continue;
            graphics.drawImage(image, location.getX(), location.getY(), null);
        }
    }

    public void addBackLocation(WorldLocation location) {
        backLocations.add(location);
    }

    public void addBackTopLocation(WorldLocation location) {
        backTopLocations.add(location);
    }

    public void addFrontLocation(WorldLocation location) {
        frontLocations.add(location);
    }

    public void addFrontTopLocation(WorldLocation location) {
        frontTopLocations.add(location);
    }

}
