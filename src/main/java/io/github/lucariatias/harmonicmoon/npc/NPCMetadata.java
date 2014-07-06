package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.world.WorldObjectMetadata;

public class NPCMetadata extends WorldObjectMetadata {

    private String[] chatLines;

    public NPCMetadata(NPCMetadata original) {
        this.chatLines = original.chatLines;
    }

    public NPCMetadata() {}

    public String[] getChatLines() {
        return chatLines;
    }
}
