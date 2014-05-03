package io.github.lucariatias.harmonicmoon;

import io.github.lucariatias.harmonicmoon.character.CharacterManager;
import io.github.lucariatias.harmonicmoon.enemy.EnemyManager;
import io.github.lucariatias.harmonicmoon.event.EventManager;
import io.github.lucariatias.harmonicmoon.event.tick.TickEvent;
import io.github.lucariatias.harmonicmoon.fight.FightPanel;
import io.github.lucariatias.harmonicmoon.job.JobManager;
import io.github.lucariatias.harmonicmoon.menu.MainMenu;
import io.github.lucariatias.harmonicmoon.message.MessageBox;
import io.github.lucariatias.harmonicmoon.message.MessageKeyListener;
import io.github.lucariatias.harmonicmoon.music.MusicPlayer;
import io.github.lucariatias.harmonicmoon.particle.ParticleManager;
import io.github.lucariatias.harmonicmoon.world.WorldPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class HarmonicMoon extends JPanel implements Runnable {

    private HarmonicMoonFrame frame;

    private static final int HEIGHT = 480;
    private static final int WIDTH = 640;

    private static final long DELAY = 25L;

    private Font titleFont;
    private Font messageFont;

    private Thread thread;

    private Logger logger = Logger.getLogger("HarmonicMoon");
    private boolean debug;

    private WorldPanel worldPanel;
    private FightPanel fightPanel;

    private CharacterManager characterManager;
    private EnemyManager enemyManager;
    private EventManager eventManager;
    private JobManager jobManager;
    private ParticleManager particleManager;

    private MusicPlayer musicPlayer;
    private MessageBox messageBox;

    public HarmonicMoon(HarmonicMoonFrame frame) {
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
        enemyManager = new EnemyManager();
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
        add(new MainMenu(this), "menu");
        getLogger().info("Set up main menu (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        worldPanel = new WorldPanel(this);
        add(worldPanel, "world");
        getLogger().info("Set up world panel (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        fightPanel = new FightPanel(this);
        add(fightPanel, "fight");
        getLogger().info("Set up fight panel (" + (System.currentTimeMillis() - startTime) + "ms)");
        setPanel("menu");
        startTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                musicPlayer = new MusicPlayer();
                musicPlayer.loop("/music/prelude_to_adventure.ogg");
            }
        }).start();
        getLogger().info("Set up thread for music (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        messageBox = new MessageBox(this);
        frame.addKeyListener(new MessageKeyListener(messageBox));
        getLogger().info("Set up message box (" + (System.currentTimeMillis() - startTime) + "ms)");
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

    @Override
    public void addNotify() {
        super.addNotify();
        thread = new Thread(this);
        thread.start();
    }

    private void doTick() {
        getEventManager().dispatchEvent(new TickEvent());
        worldPanel.onTick();
        fightPanel.onTick();
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
}
