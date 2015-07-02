package com.seventh_root.harmonicmoon.npc;

import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.character.Character;
import com.seventh_root.harmonicmoon.event.collision.CollisionEvent;
import com.seventh_root.harmonicmoon.event.collision.CollisionListener;
import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxCloseEvent;
import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxCloseListener;
import com.seventh_root.harmonicmoon.fight.Fight;
import com.seventh_root.harmonicmoon.fight.FightArea;
import com.seventh_root.harmonicmoon.fight.party.EnemyFightParty;
import com.seventh_root.harmonicmoon.npc.path.FollowingPath;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;
import com.seventh_root.harmonicmoon.enemy.Soldier;

public class SoldierNPC extends NPC {

    private HarmonicMoon harmonicMoon;

    public SoldierNPC(HarmonicMoon harmonicMoon, NPCMetadata metadata) {
        super(harmonicMoon, new SpriteSheet(harmonicMoon, "/npcs/soldier.png", 32, 16), metadata);
        this.harmonicMoon = harmonicMoon;
        setPath(new FollowingPath(this, harmonicMoon.getPlayer(), 64));
        harmonicMoon.getEventManager().registerListener(new CollisionListener() {
            @Override
            public void onCollision(CollisionEvent event) {
                if (event.getObjects()[0] == SoldierNPC.this && event.getObjects()[1] instanceof Character.World ||
                        event.getObjects()[1] == SoldierNPC.this && event.getObjects()[0] instanceof Character.World) {
                    interact();
                    getPath().setFrozen(true);
                }
            }
        });
        harmonicMoon.getEventManager().registerListener(new MessageBoxCloseListener() {
            @Override
            public void onMessageBoxClose(MessageBoxCloseEvent event) {
                if (event.getMessageBox().getMessage().getNPC() != null) {
                    if (event.getMessageBox().getMessage().getNPC() == SoldierNPC.this) {
                        HarmonicMoon harmonicMoon = SoldierNPC.this.harmonicMoon;
                        harmonicMoon.getWorldPanel().setActive(false);
                        Fight fight = new Fight(harmonicMoon, FightArea.PALACE, harmonicMoon.getCharacterManager().getParty(harmonicMoon.getCharacterManager().getCharacter("lonyre")).asFightParty(), new EnemyFightParty(new Soldier(harmonicMoon)));
                        harmonicMoon.getFightPanel().prepareFight(fight);
                        harmonicMoon.getFightPanel().startFight();
                        harmonicMoon.setPanel("fight");
                        harmonicMoon.getFightPanel().setActive(true);
                        getLocation().getWorld().removeObject(SoldierNPC.this);
                    }
                }
            }
        });
    }

}
