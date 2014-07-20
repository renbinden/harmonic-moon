package io.github.lucariatias.harmonicmoon;

import io.github.lucariatias.harmonicmoon.character.CharacterManager;
import io.github.lucariatias.harmonicmoon.currency.Currency;
import io.github.lucariatias.harmonicmoon.enemy.EnemyManager;
import io.github.lucariatias.harmonicmoon.event.EventManager;
import io.github.lucariatias.harmonicmoon.event.tick.TickEvent;
import io.github.lucariatias.harmonicmoon.fight.FightPanel;
import io.github.lucariatias.harmonicmoon.inventory.item.Item;
import io.github.lucariatias.harmonicmoon.inventory.item.consumable.Consumable;
import io.github.lucariatias.harmonicmoon.job.JobManager;
import io.github.lucariatias.harmonicmoon.menu.MainMenu;
import io.github.lucariatias.harmonicmoon.message.MessageBox;
import io.github.lucariatias.harmonicmoon.message.MessageKeyListener;
import io.github.lucariatias.harmonicmoon.music.MusicPlayer;
import io.github.lucariatias.harmonicmoon.particle.ParticleManager;
import io.github.lucariatias.harmonicmoon.player.Camera;
import io.github.lucariatias.harmonicmoon.player.KeyboardPlayerController;
import io.github.lucariatias.harmonicmoon.player.Player;
import io.github.lucariatias.harmonicmoon.shop.Shop;
import io.github.lucariatias.harmonicmoon.shop.ShopPanel;
import io.github.lucariatias.harmonicmoon.skill.SkillManager;
import io.github.lucariatias.harmonicmoon.world.World;
import io.github.lucariatias.harmonicmoon.world.WorldPanel;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

public class HarmonicMoon extends JPanel implements Runnable {

    private HarmonicMoonFrame frame;

    private static final int HEIGHT = 480;
    private static final int WIDTH = 640;

    private static final long DELAY = 25L;

    private BufferedImage logo;
    private Font titleFont;
    private Font messageFont;

    private Thread thread;

    private Logger logger = Logger.getLogger("HarmonicMoon");
    private boolean debug;

    private WorldPanel worldPanel;
    private Map<String, WorldPanel> worldPanels = new HashMap<>();
    private FightPanel fightPanel;
    private ShopPanel shopPanel;
    private Map<String, ShopPanel> shopPanels = new HashMap<>();

    private Map<String, Shop> shops = new HashMap<>();

    private CharacterManager characterManager;
    private EnemyManager enemyManager;
    private EventManager eventManager;
    private JobManager jobManager;
    private ParticleManager particleManager;
    private SkillManager skillManager;

    private ScriptEngine scriptEngine;

    private MusicPlayer musicPlayer;
    private MessageBox messageBox;

    private KeyboardPlayerController playerController;
    private Player player;
    private Camera camera;

    public HarmonicMoon(HarmonicMoonFrame frame, String[] args) {
        List<String> argsList = Arrays.asList(args);
        this.frame = frame;
        CardLayout layout = new CardLayout();
        setLayout(layout);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
        try {
            long startTime = System.currentTimeMillis();
            this.titleFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/AftaSerifThin-Regular.otf"));
            this.messageFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/AftaSerifThin-Regular.otf")).deriveFont(12F);
            getLogger().info("Loaded fonts (" + (System.currentTimeMillis() - startTime) + "ms)");
        } catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
        }
        long startTime = System.currentTimeMillis();
        characterManager = new CharacterManager(this);
        getLogger().info("Loaded characters (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        enemyManager = new EnemyManager(this);
        getLogger().info("Loaded enemies (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        eventManager = new EventManager(this);
        getLogger().info("Set up event manager (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        jobManager = new JobManager();
        getLogger().info("Loaded jobs (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        particleManager = new ParticleManager(this);
        getLogger().info("Set up particle systems (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        skillManager = new SkillManager(this);
        getLogger().info("Set up skills (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        getLogger().info("Set up script engine (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        try {
            logo = ImageIO.read(getClass().getResourceAsStream("/logo.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        add(new MainMenu(this, logo), "menu");
        getLogger().info("Set up main menu (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        player = new Player(this);
        getLogger().info("Created player (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        playerController = new KeyboardPlayerController(player);
        playerController.setActive(true);
        getFrame().addKeyListener(playerController);
        getFrame().addKeyListener(new DebugKeyListener(this));
        getLogger().info("Set up key listeners (" + (System.currentTimeMillis() - startTime) + "ms)");
        String map = "palace_1_2f";
        worldPanel = new WorldPanel(this, map);
        worldPanels.put(map, worldPanel);
        add(worldPanel, "map_" + map);
        player.setCharacter(characterManager.getCharacter("lonyre"));
        startTime = System.currentTimeMillis();
        camera = new Camera(player);
        getLogger().info("Set up camera (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        fightPanel = new FightPanel(this);
        add(fightPanel, "fight");
        getLogger().info("Set up fight panel (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        shops = new HashMap<>();
        LinkedHashMap<Item, Integer> avirnyrShopItems = new LinkedHashMap<>();
        avirnyrShopItems.put(Consumable.POTION, 30);
        avirnyrShopItems.put(Consumable.ETHER, 250);
        Shop avirnyrShop = new Shop(Currency.MOLLIR, avirnyrShopItems);
        shops.put("avirnyr", avirnyrShop);
        getLogger().info("Set up shops (" + (System.currentTimeMillis() - startTime) + "ms)");
        setPanel("menu");
        startTime = System.currentTimeMillis();
        musicPlayer = new MusicPlayer(!argsList.contains("--no-music"));
        musicPlayer.loop("/music/prelude_to_adventure.ogg");
        getLogger().info("Set up thread for music (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        messageBox = new MessageBox(this);
        frame.addKeyListener(new MessageKeyListener(messageBox));
        getLogger().info("Set up message box (" + (System.currentTimeMillis() - startTime) + "ms)");
        if (argsList.contains("--debug")) {
            setDebug(true);
        }
    }

    public HarmonicMoonFrame getFrame() {
        return frame;
    }

    public Logger getLogger() {
        return logger;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public Font getMessageFont() {
        return messageFont;
    }

    public void setTitleFont(Font font) {
        this.titleFont = font;
    }

    public void setPanel(String panel) {
        CardLayout layout = (CardLayout) getLayout();
        layout.show(this, panel);
    }

    public void showWorld(String map) {
        if (worldPanel != null) worldPanel.setActive(false);
        if (fightPanel != null) fightPanel.setActive(false);
        if (shopPanel != null) shopPanel.setActive(false);
        if (!worldPanels.containsKey(map)) {
            long startTime = System.currentTimeMillis();
            worldPanel = new WorldPanel(this, map);
            worldPanels.put(map, worldPanel);
            add(worldPanel, "map_" + map);
            getLogger().info("Loaded world '" + map + "' (" + (System.currentTimeMillis() - startTime) + "ms)");
        } else {
            worldPanel = worldPanels.get(map);
        }
        setPanel("map_" + map);
        getCamera().setLocation(getPlayer().getCharacter().world().getLocation());
        worldPanel.getWorld().playMusic();
        worldPanel.setActive(true);
    }

    public void showShop(String name) {
        if (worldPanel != null) worldPanel.setActive(false);
        if (fightPanel != null) fightPanel.setActive(false);
        if (shopPanel != null) shopPanel.setActive(false);
        if (!shopPanels.containsKey(name)) {
            long startTime = System.currentTimeMillis();
            shopPanel = new ShopPanel(this, shops.get(name));
            shopPanels.put(name, shopPanel);
            add(shopPanel, "shop_" + name);
            getLogger().info("Loaded shop '" + name + "' (" + (System.currentTimeMillis() - startTime) + "ms)");
        } else {
            shopPanel = shopPanels.get(name);
        }
        setPanel("shop_" + name);
        shopPanel.setActive(true);
    }

    public World getWorld(String name) {
        if (!worldPanels.containsKey(name)) {
            long startTime = System.currentTimeMillis();
            worldPanel = new WorldPanel(this, name);
            worldPanels.put(name, worldPanel);
            add(worldPanel, "map_" + name);
            getLogger().info("Loaded world " + name + " (" + (System.currentTimeMillis() - startTime) + "ms)");
        }
        return worldPanels.get(name).getWorld();
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void debug(String message) {
        if (debug) {
            logger.info("[debug] " + message);
        }
    }

    public WorldPanel getWorldPanel() {
        return worldPanel;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public JobManager getJobManager() {
        return jobManager;
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        thread = new Thread(this);
        thread.start();
    }

    private void doTick() {
        getEventManager().dispatchEvent(new TickEvent());
        if (worldPanel != null) worldPanel.onTick();
        if (fightPanel != null) fightPanel.onTick();
        if (shopPanel != null) shopPanel.onTick();
        particleManager.update();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true) {
            doTick();
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            beforeTime = System.currentTimeMillis();
        }
    }

    public FightPanel getFightPanel() {
        return fightPanel;
    }

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

    public KeyboardPlayerController getPlayerController() {
        return playerController;
    }

    public Player getPlayer() {
        return player;
    }

    public Camera getCamera() {
        return camera;
    }
}
