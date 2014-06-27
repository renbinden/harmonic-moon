package io.github.lucariatias.harmonicmoon.sprite;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private HarmonicMoon harmonicMoon;

    private BufferedImage image;
    private int tileHeight;
    private int tileWidth;

    private BufferedImage[][] imageCache;

    public SpriteSheet(HarmonicMoon harmonicMoon, String path, int tileHeight, int tileWidth) {
        this.harmonicMoon = harmonicMoon;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        imageCache = new BufferedImage[image.getWidth() / tileWidth][image.getHeight() / tileHeight];
    }

    public SpriteSheet(HarmonicMoon harmonicMoon, BufferedImage image, int tileHeight, int tileWidth) {
        this.harmonicMoon = harmonicMoon;
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
        return getSprite(x, y, images, 10);
    }

    public Sprite getSprite(int x, int y, int images, int frameDelay) {
        BufferedImage[] frames = new BufferedImage[images + 1];
        for (int i = 0; i < images; i++) {
            frames[i] = getImage(x + i, y);
        }
        return new Sprite(harmonicMoon, frameDelay, frames);
    }

}
