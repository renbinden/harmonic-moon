package io.github.lucariatias.harmonicmoon.npc;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.event.messagebox.MessageBoxCloseEvent;
import io.github.lucariatias.harmonicmoon.event.messagebox.MessageBoxCloseListener;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

public class AvirnyrItemMerchantNPC extends ItemMerchantNPC {

    private HarmonicMoon harmonicMoon;

    public AvirnyrItemMerchantNPC(HarmonicMoon harmonicMoon, NPCMetadata metadata) {
        super(
                harmonicMoon,
                new SpriteSheet(harmonicMoon, "/npcs/avirnyr_item_merchant.png", 32, 16),
                metadata
        );
        this.harmonicMoon = harmonicMoon;
        harmonicMoon.getEventManager().registerListener(new MessageBoxCloseListener() {
            @Override
            public void onMessageBoxClose(MessageBoxCloseEvent event) {
                if (event.getMessageBox().getMessage().getNPC() == AvirnyrItemMerchantNPC.this) {
                    AvirnyrItemMerchantNPC.this.harmonicMoon.showShop("avirnyr");
                }
            }
        });
    }

}
