package com.seventh_root.harmonicmoon.tile;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.world.WorldLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tile {

    private HarmonicMoon harmonicMoon;

    private Map<TileLayer, List<WorldLocation>> locations = new HashMap<>();
    private Image image;

    public Tile(HarmonicMoon harmonicMoon, Image image) {
        this.harmonicMoon = harmonicMoon;
        this.image = image;
    }

    public void render(Graphics graphics, TileLayer layer) {
        for (WorldLocation location : getLocations(layer)) {
            if (harmonicMoon.getWorldPanel().getCamera().getLocation().distanceSquared(location) > 713728) continue;
            if (!(location.getX() >= harmonicMoon.getWorldPanel().getCamera().getLocation().getX() - 32 && location.getY() >= harmonicMoon.getWorldPanel().getCamera().getLocation().getY() - 32)) continue;
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
