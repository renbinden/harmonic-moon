package io.github.lucariatias.harmonicmoon.tile;

import io.github.lucariatias.harmonicmoon.world.WorldPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileSheet {

    private WorldPanel worldPanel;

    private BufferedImage image;
    private int tileHeight;
    private int tileWidth;

    private Tile[][] tileCache;

    public TileSheet(WorldPanel worldPanel, BufferedImage image, int tileHeight, int tileWidth) {
        this.worldPanel = worldPanel;
        this.image = image;
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        tileCache = new Tile[image.getWidth() / tileWidth][image.getHeight() / tileHeight];
    }

    public TileSheet(String path, int tileHeight, int tileWidth) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
    }

    public Tile getTile(int col, int row) {
        Tile tile;
        if (tileCache[col][row] == null) {
            tile = new Tile(worldPanel, image.getSubimage(tileWidth * col, tileHeight * row, tileWidth, tileHeight));
            tileCache[col][row] = tile;
        } else {
            tile = tileCache[col][row];
        }
        return tile;
    }

}
