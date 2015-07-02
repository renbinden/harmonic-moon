package com.seventh_root.harmonicmoon.npc;

import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxCloseEvent;
import com.seventh_root.harmonicmoon.HarmonicMoon;
import com.seventh_root.harmonicmoon.event.messagebox.MessageBoxCloseListener;
import com.seventh_root.harmonicmoon.sprite.SpriteSheet;

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
