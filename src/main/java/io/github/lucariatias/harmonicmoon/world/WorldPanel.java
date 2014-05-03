package io.github.lucariatias.harmonicmoon.world;

import io.github.lucariatias.harmonicmoon.DebugKeyListener;
import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.player.Camera;
import io.github.lucariatias.harmonicmoon.player.KeyboardPlayerController;
import io.github.lucariatias.harmonicmoon.player.Player;
import io.github.lucariatias.harmonicmoon.tile.TileSheet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WorldPanel extends JPanel {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private HarmonicMoon harmonicMoon;

    private boolean active;

    private World world;

    private KeyboardPlayerController playerController;
    private Player player;
    private Camera camera;

    public WorldPanel(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
        long startTime = System.currentTimeMillis();
        try {
            world = new World(harmonicMoon, ImageIO.read(getClass().getResourceAsStream("/maps/world/tiles-back.png")), ImageIO.read(getClass().getResourceAsStream("/maps/world/tiles-back-top.png")), ImageIO.read(getClass().getResourceAsStream("/maps/world/objects.png")), ImageIO.read(getClass().getResourceAsStream("/maps/world/tiles-front.png")), new TileSheet(this, ImageIO.read(getClass().getResourceAsStream("/tiles.png")), 16, 16));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        harmonicMoon.getLogger().info("Created world (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        player = new Player(harmonicMoon);
        harmonicMoon.getLogger().info("Created player (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        world.populate();
        harmonicMoon.getLogger().info("Populated world (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        camera = new Camera(player);
        harmonicMoon.getLogger().info("Set up camera (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        playerController = new KeyboardPlayerController(player);
        playerController.setActive(true);
        harmonicMoon.getFrame().addKeyListener(playerController);
        harmonicMoon.getFrame().addKeyListener(new DebugKeyListener(harmonicMoon));
        harmonicMoon.getLogger().info("Set up key listeners (" + (System.currentTimeMillis() - startTime) + "ms)");
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public Camera getCamera() {
        return camera;
    }

    public void reset() {
    }

    public void onTick() {
        if (active) {
            world.onTick();
            playerController.onTick();
            if (player.getCharacter() != null) camera.onTick();
            harmonicMoon.getMessageBox().onTick();
        }
    }

    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.translate( - camera.getLocation().getX(),  - camera.getLocation().getY());
        world.render(graphics);
        harmonicMoon.getParticleManager().render(graphics);
        graphics2D.translate(camera.getLocation().getX(), camera.getLocation().getY());
        harmonicMoon.getMessageBox().render(graphics);
        harmonicMoon.getParticleManager().renderHUD(graphics);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        render(graphics);
    }

    public HarmonicMoon getHarmonicMoon() {
        return harmonicMoon;
    }
}
