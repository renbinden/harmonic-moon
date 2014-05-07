package io.github.lucariatias.harmonicmoon.world;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.block.Block;
import io.github.lucariatias.harmonicmoon.tile.Tile;
import io.github.lucariatias.harmonicmoon.tile.TileSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class World {

    private HarmonicMoon harmonicMoon;
    private BufferedImage backTileMap;
    private BufferedImage backTopTileMap;
    private BufferedImage objectMap;
    private BufferedImage frontTileMap;
    private BufferedImage frontTopTileMap;
    private TileSheet tileSheet;

    private Set<Tile> backTiles = new HashSet<>();
    private Set<Tile> backTopTiles = new HashSet<>();
    private Set<WorldObject> objects = new HashSet<>();
    private Set<Tile> frontTiles = new HashSet<>();
    private Set<Tile> frontTopTiles = new HashSet<>();

    public World(HarmonicMoon harmonicMoon, BufferedImage backTileMap, BufferedImage backTopTileMap, BufferedImage objectMap, BufferedImage frontTileMap, BufferedImage frontTopTileMap, TileSheet tileSheet) {
        this.harmonicMoon = harmonicMoon;
        this.backTileMap = backTileMap;
        this.backTopTileMap = backTopTileMap;
        this.objectMap = objectMap;
        this.frontTileMap = frontTileMap;
        this.frontTopTileMap = frontTopTileMap;
        this.tileSheet = tileSheet;
    }

    public void onTick() {
        for (WorldObject object : objects) {
            object.onTick();
        }
    }

    public void render(Graphics graphics) {
        for (Tile tile : backTiles) {
            tile.renderBack(graphics);
        }
        for (Tile tile : backTopTiles) {
            tile.renderBackTop(graphics);
        }
        for (WorldObject object : objects) {
            object.render(graphics);
        }
        for (Tile tile : frontTiles) {
            tile.renderFront(graphics);
        }
        for (Tile tile : frontTopTiles) {
            tile.renderFrontTop(graphics);
        }
    }

    public Set<WorldObject> getObjects() {
        return objects;
    }

    public void addObject(WorldObject object) {
        objects.add(object);
    }

    public void removeObject(WorldObject object) {
        objects.remove(object);
    }

    public void populate() {
        populateBackTiles();
        populateBackTopTiles();
        populateObjects();
        populateFrontTiles();
        populateFrontTopTiles();
    }

    private void populateBackTiles() {
        int width = backTileMap.getWidth();
        int height = backTileMap.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = backTileMap.getRGB(x, y);
                Color colour = new Color((pixel >> 16) & 0xff, (pixel >> 8) & 0xff, pixel & 0xff);
                Tile tile = tileSheet.getTile(colour.getRed(), colour.getGreen());
                tile.addBackLocation(new WorldLocation(this, x * 16, y * 16));
                backTiles.add(tile);
            }
        }
        backTileMap.flush();
    }

    private void populateBackTopTiles() {
        int width = backTopTileMap.getWidth();
        int height = backTopTileMap.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = backTopTileMap.getRGB(x, y);
                Color colour = new Color((pixel >> 16) & 0xff, (pixel >> 8) & 0xff, pixel & 0xff);
                if (!colour.equals(Color.BLACK)) {
                    Tile tile = tileSheet.getTile(colour.getRed(), colour.getGreen());
                    tile.addBackTopLocation(new WorldLocation(this, x * 16, y * 16));
                    backTopTiles.add(tile);
                }
            }
        }
        backTopTileMap.flush();
    }

    private void populateFrontTiles() {
        int width = frontTileMap.getWidth();
        int height = frontTileMap.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = frontTileMap.getRGB(x, y);
                Color colour = new Color((pixel >> 16) & 0xff, (pixel >> 8) & 0xff, pixel & 0xff);
                if (!colour.equals(Color.BLACK)) {
                    Tile tile = tileSheet.getTile(colour.getRed(), colour.getGreen());
                    tile.addFrontLocation(new WorldLocation(this, x * 16, y * 16));
                    frontTiles.add(tile);
                }
            }
        }
        frontTileMap.flush();
    }

    private void populateFrontTopTiles() {
        int width = frontTopTileMap.getWidth();
        int height = frontTopTileMap.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = frontTileMap.getRGB(x, y);
                Color colour = new Color((pixel >> 16) & 0xff, (pixel >> 8) & 0xff, pixel & 0xff);
                if (!colour.equals(Color.BLACK)) {
                    Tile tile = tileSheet.getTile(colour.getRed(), colour.getGreen());
                    tile.addFrontTopLocation(new WorldLocation(this, x * 16, y * 16));
                    frontTopTiles.add(tile);
                }
            }
        }
        frontTopTileMap.flush();
    }

    private void populateObjects() {
        int width = objectMap.getWidth();
        int height = objectMap.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = objectMap.getRGB(x, y);
                Color colour = new Color((pixel >> 16) & 0xff, (pixel >> 8) & 0xff, pixel & 0xff);
                WorldObject object = getObjectFromColour(colour);
                if (object != null) {
                    object.setLocation(new WorldLocation(this, x * 16, y * 16));
                    addObject(object);
                }
            }
        }
        objectMap.flush();
    }

    private WorldObject getObjectFromColour(Color colour) {
        switch (colour.getRed()) {
            case 0:
                switch (colour.getGreen()) {
                    case 0:
                        switch (colour.getBlue()) {
                            case 0: return null;
                            case 1: return new Block();
                            case 2: return harmonicMoon.getCharacterManager().getCharacter("lonyre").getWorldInfo();
                            default: return null;
                        }
                    default: return null;
                }
            default: return null;
        }
    }

}
