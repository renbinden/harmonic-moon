package io.github.lucariatias.harmonicmoon.tile;

import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tile {

    private WorldPanel worldPanel;

    private Map<TileLayer, List<WorldLocation>> locations = new HashMap<>();
    private Image image;

    public Tile(WorldPanel worldPanel, Image image) {
        this.worldPanel = worldPanel;
        this.image = image;
    }

    public void render(Graphics graphics, TileLayer layer) {
        for (WorldLocation location : getLocations(layer)) {
            if (worldPanel.getCamera().getLocation().distanceSquared(location) > 640000) continue;
            if (!(location.getX() >= worldPanel.getCamera().getLocation().getX() - 16 && location.getY() >= worldPanel.getCamera().getLocation().getY() - 16)) continue;
            graphics.drawImage(image, location.getX(), location.getY(), null);
        }
    }

    public List<WorldLocation> getLocations(TileLayer layer) {
        if (locations.get(layer) == null) {
            locations.put(layer, new ArrayList<WorldLocation>());
        }
        return locations.get(layer);
    }

    public Image getImage() {
        return image;
    }

}
