package io.github.lucariatias.harmonicmoon.world;

public class NpcMetadata extends WorldObjectMetadata {

    private String[] chatLines;

    public NpcMetadata(NpcMetadata original) {
        this.chatLines = original.chatLines;
    }

    public NpcMetadata() {}

}
