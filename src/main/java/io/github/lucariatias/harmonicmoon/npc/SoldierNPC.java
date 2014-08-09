package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.enemy.Soldier;
import io.github.lucariatias.harmonicmoon.event.collision.CollisionEvent;
import io.github.lucariatias.harmonicmoon.event.collision.CollisionListener;
import io.github.lucariatias.harmonicmoon.event.messagebox.MessageBoxCloseEvent;
import io.github.lucariatias.harmonicmoon.event.messagebox.MessageBoxCloseListener;
import io.github.lucariatias.harmonicmoon.fight.Fight;
import io.github.lucariatias.harmonicmoon.fight.FightArea;
import io.github.lucariatias.harmonicmoon.fight.party.EnemyFightParty;
import io.github.lucariatias.harmonicmoon.npc.path.FollowingPath;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

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
