package io.github.lucariatias.harmonicmoon.world;

import io.github.lucariatias.harmonicmoon.metadata.Metadata;

public class WorldMetadata extends Metadata {

    private int width;
    private int height;
    private String music;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

}
