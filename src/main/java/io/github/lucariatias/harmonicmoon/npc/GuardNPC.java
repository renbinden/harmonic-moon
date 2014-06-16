package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.character.WorldCharacter;
import io.github.lucariatias.harmonicmoon.enemy.Guard;
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

public class GuardNPC extends NPC {

    private HarmonicMoon harmonicMoon;
    private boolean hasSpoken;

    public GuardNPC(HarmonicMoon harmonicMoon) {
        super(harmonicMoon, new SpriteSheet("/npcs/guard.png", 32, 16));
        this.harmonicMoon = harmonicMoon;
        setPath(new FollowingPath(this, harmonicMoon.getPlayer(), 64));
        harmonicMoon.getEventManager().registerListener(new CollisionListener() {
            @Override
            public void onCollision(CollisionEvent event) {
                if (event.getObjects()[0] == GuardNPC.this && event.getObjects()[1] instanceof WorldCharacter ||
                        event.getObjects()[1] == GuardNPC.this && event.getObjects()[0] instanceof WorldCharacter) {
                    interact();
                    getPath().setFrozen(true);
                }
            }
        });
        harmonicMoon.getEventManager().registerListener(new MessageBoxCloseListener() {
            @Override
            public void onMessageBoxClose(MessageBoxCloseEvent event) {
                if (event.getMessageBox().getMessage().getNpc() != null) {
                    if (event.getMessageBox().getMessage().getNpc() == GuardNPC.this && event.getMessageBox().getMessage().getText().equals("This castle has been taken over by His Mightiness, Yirnor, no prisoners are to escape.")) {
                        GuardNPC.this.harmonicMoon.getWorldPanel().setActive(false);
                        Fight fight = new Fight(FightArea.PALACE, new CharacterParty(GuardNPC.this.harmonicMoon.getPlayer().getCharacter().fight()), new EnemyParty(new Guard(GuardNPC.this.harmonicMoon)));
                        GuardNPC.this.harmonicMoon.getFightPanel().prepareFight(fight);
                        GuardNPC.this.harmonicMoon.getFightPanel().startFight();
                        GuardNPC.this.harmonicMoon.setPanel("fight");
                        getLocation().getWorld().removeObject(GuardNPC.this);
                    }
                }
            }
        });
    }

    @Override
    public void interact() {
        if (!hasSpoken) {
            hasSpoken = true;
            say("Hey! You there! Stop!", "This castle has been taken over by His Mightiness, Yirnor, no prisoners are to escape.");
        }
    }

}
