package io.github.lucariatias.harmonicmoon.message;

import io.github.lucariatias.harmonicmoon.npc.NPC;

public class Message {

    private NPC npc;
    private String text;

    public Message(String text) {
        this.text = text;
    }

    public Message(NPC npc, String text) {
        this.npc = npc;
        this.text = text;
    }

    public NPC getNPC() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
