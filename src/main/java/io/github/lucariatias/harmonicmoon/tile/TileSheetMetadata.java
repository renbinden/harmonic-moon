package io.github.lucariatias.harmonicmoon.tile;

import io.github.lucariatias.harmonicmoon.metadata.Metadata;

public class TileSheetMetadata extends Metadata {

    private int tileWidth;
    private int tileHeight;

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

}
