package io.github.lucariatias.harmonicmoon.sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private BufferedImage image;
    private int tileHeight;
    private int tileWidth;

    private BufferedImage[][] imageCache;

    public SpriteSheet(String path, int tileHeight, int tileWidth) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        imageCache = new BufferedImage[image.getWidth() / tileWidth][image.getHeight() / tileHeight];
    }

    public SpriteSheet(BufferedImage image, int tileHeight, int tileWidth) {
        this.image = image;
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        imageCache = new BufferedImage[image.getWidth() / tileWidth][image.getHeight() / tileHeight];
    }

    public BufferedImage getImage(int x, int y) {
        if (imageCache[x][y] == null) {
            imageCache[x][y] = image.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
        }
        return imageCache[x][y];
    }

    public Sprite getSprite(int x, int y, int images) {
        BufferedImage[] frames = new BufferedImage[images + 1];
        for (int i = 0; i < images; i++) {
            frames[i] = getImage(x + i, y);
        }
        return new Sprite(10, frames);
    }

}
