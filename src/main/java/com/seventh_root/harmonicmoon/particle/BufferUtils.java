package com.seventh_root.harmonicmoon.particle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferUtils {

    public static BufferedImage loadImage(String i, int x, int imageWidth, int imageHeight) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            BufferedImage tempBuff = ImageIO.read(new File(i));
            BufferedImage a = gc.createCompatibleImage(imageWidth, imageHeight, Transparency.TRANSLUCENT);
            Graphics tempG = a.getGraphics();
            tempG.drawImage(tempBuff.getSubimage(x, 0, imageWidth, imageHeight), 0, 0, null);
            tempG.dispose();
            return a;

        } catch (IOException ioexception) {
            return null;
        }

    }

    public static BufferedImage[] loadImageStrip(String path, int imageWidth, int imageHeight, int numImages) {
        int x = 0;
        BufferedImage[] temp = new BufferedImage[numImages];
        for (int i = 0; i < numImages; i++) {
            temp[i] = loadImage(path, x, imageWidth, imageHeight);
            x += imageWidth;
        }
        return temp;
    }

}
