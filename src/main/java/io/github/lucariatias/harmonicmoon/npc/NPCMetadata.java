package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.world.WorldObjectMetadata;

public class NPCMetadata extends WorldObjectMetadata {

    private String initialiseScript;
    private String interactScript;

    public NPCMetadata(NPCMetadata original) {
        this.initialiseScript = original.initialiseScript;
        this.interactScript = original.interactScript;
    }

    public NPCMetadata() {}


    public String getInitialiseScript() {
        return initialiseScript;
    }

    public String getInteractScript() {
        return interactScript;
    }

}
