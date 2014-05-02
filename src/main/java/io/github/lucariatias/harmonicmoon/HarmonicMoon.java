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
            this.titleFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/AftaSerifThin-Regular.otf"));
            this.messageFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/AftaSerifThin-Regular.otf")).deriveFont(12F);
        } catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
        }
        characterManager = new CharacterManager(this);
        enemyManager = new EnemyManager();
        eventManager = new EventManager(this);
        jobManager = new JobManager();
        particleManager = new ParticleManager(this);
        add(new MainMenu(this), "menu");
        worldPanel = new WorldPanel(this);
        add(worldPanel, "world");
        fightPanel = new FightPanel(this);
        add(fightPanel, "fight");
        setPanel("menu");
        new Thread(new Runnable() {
            @Override
            public void run() {
                musicPlayer = new MusicPlayer();
                musicPlayer.loop("/music/prelude_to_adventure.ogg");
            }
        }).start();
        messageBox = new MessageBox(this);
        frame.addKeyListener(new MessageKeyListener(messageBox));
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
