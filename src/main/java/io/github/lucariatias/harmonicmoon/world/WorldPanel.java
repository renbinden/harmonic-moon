package io.github.lucariatias.harmonicmoon.world;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.player.Camera;
import io.github.lucariatias.harmonicmoon.player.Player;
import io.github.lucariatias.harmonicmoon.player.PlayerController;
import io.github.lucariatias.harmonicmoon.tile.TileLayer;
import io.github.lucariatias.harmonicmoon.tile.TileSheet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class WorldPanel extends JPanel {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private HarmonicMoon harmonicMoon;

    private boolean active;

    private World world;

    public WorldPanel(HarmonicMoon harmonicMoon, String map) {
        this.harmonicMoon = harmonicMoon;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);
        long startTime = System.currentTimeMillis();
        try {
            Map<TileLayer, BufferedImage> tileMaps = new EnumMap<>(TileLayer.class);
            tileMaps.put(TileLayer.BACK, ImageIO.read(getClass().getResourceAsStream("/maps/" + map + "/tiles-back.png")));
            tileMaps.put(TileLayer.BACK_TOP, ImageIO.read(getClass().getResourceAsStream("/maps/" + map + "/tiles-back-top.png")));
            tileMaps.put(TileLayer.FRONT, ImageIO.read(getClass().getResourceAsStream("/maps/" + map + "/tiles-front.png")));
            tileMaps.put(TileLayer.FRONT_TOP, ImageIO.read(getClass().getResourceAsStream("/maps/" + map + "/tiles-front-top.png")));
            world = new World(harmonicMoon, map, tileMaps, ImageIO.read(getClass().getResourceAsStream("/maps/" + map + "/objects.png")), new TileSheet(this, ImageIO.read(getClass().getResourceAsStream("/maps/" + map + "/tilesheet.png")), 16, 16));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        harmonicMoon.getLogger().info("Created world '" + map + "' (" + (System.currentTimeMillis() - startTime) + "ms)");
        startTime = System.currentTimeMillis();
        world.populate();
        harmonicMoon.getLogger().info("Populated world '" + map + "' (" + (System.currentTimeMillis() - startTime) + "ms)");
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

    public PlayerController getPlayerController() {
        return harmonicMoon.getPlayerController();
    }

    public Player getPlayer() {
        return harmonicMoon.getPlayer();
    }

    public Camera getCamera() {
        return harmonicMoon.getCamera();
    }

    public void reset() {
    }

    public void onTick() {
        if (active) {
            world.onTick();
            getPlayerController().onTick();
            harmonicMoon.getMessageBox().onTick();
            if (getPlayer().getCharacter() != null) getCamera().onTick();
        }
    }

    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.translate(-getCamera().getLocation().getX(), -getCamera().getLocation().getY());
        world.render(graphics);
        harmonicMoon.getParticleManager().render(graphics);
        graphics2D.translate(getCamera().getLocation().getX(), getCamera().getLocation().getY());
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
