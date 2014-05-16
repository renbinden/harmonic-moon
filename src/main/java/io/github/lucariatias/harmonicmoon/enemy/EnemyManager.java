package io.github.lucariatias.harmonicmoon.enemy;

import io.github.lucariatias.harmonicmoon.sprite.Sprite;
import io.github.lucariatias.harmonicmoon.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnemyManager {

    private Map<Class<? extends Enemy>, SpriteSheet> enemySpriteSheets = new HashMap<>();
    private Map<Class<? extends Enemy>, Sprite> enemyWaitSprites = new HashMap<>();
    private Map<Class<? extends Enemy>, Sprite> enemyAttackSprites = new HashMap<>();
    private Map<Class<? extends Enemy>, Sprite> enemyInjuredSprites = new HashMap<>();

    public EnemyManager() {
        try {
            SpriteSheet slimeSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/enemies/slime.png")), 64, 64);
            enemySpriteSheets.put(Slime.class, slimeSpriteSheet);
            enemyWaitSprites.put(Slime.class, slimeSpriteSheet.getSprite(0, 0, 8));
            enemyAttackSprites.put(Slime.class, slimeSpriteSheet.getSprite(0, 1, 8));
            enemyInjuredSprites.put(Slime.class, slimeSpriteSheet.getSprite(0, 2, 8));
            SpriteSheet guardSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/enemies/guard.png")), 64, 64);
            enemySpriteSheets.put(Guard.class, guardSpriteSheet);
            enemyWaitSprites.put(Guard.class, guardSpriteSheet.getSprite(0, 0, 8));
            enemyAttackSprites.put(Guard.class, guardSpriteSheet.getSprite(0, 1, 8));
            enemyInjuredSprites.put(Guard.class, guardSpriteSheet.getSprite(0, 2, 8));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public SpriteSheet getSpriteSheet(Class<? extends Enemy> enemy) {
        return enemySpriteSheets.get(enemy);
    }

    public Sprite getWaitSprite(Class<? extends Enemy> enemy) {
        return enemyWaitSprites.get(enemy);
    }

    public Sprite getAttackSprite(Class<? extends Enemy> enemy) {
        return enemyAttackSprites.get(enemy);
    }

    public Sprite getInjuredSprite(Class<? extends Enemy> enemy) {
        return enemyInjuredSprites.get(enemy);
    }

}
