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
import io.github.lucariatias.harmonicmoon.npc.path.FollowingPath;
import io.github.lucariatias.harmonicmoon.party.CharacterParty;
import io.github.lucariatias.harmonicmoon.party.EnemyParty;
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
                        SoldierNPC.this.harmonicMoon.getWorldPanel().setActive(false);
                        Fight fight = new Fight(harmonicMoon, FightArea.PALACE, new CharacterParty(SoldierNPC.this.harmonicMoon.getPlayer().getCharacter()), new EnemyParty(new Soldier(SoldierNPC.this.harmonicMoon)));
                        SoldierNPC.this.harmonicMoon.getFightPanel().prepareFight(fight);
                        SoldierNPC.this.harmonicMoon.getFightPanel().startFight();
                        SoldierNPC.this.harmonicMoon.setPanel("fight");
                        getLocation().getWorld().removeObject(SoldierNPC.this);
                    }
                }
            }
        });
    }

}
