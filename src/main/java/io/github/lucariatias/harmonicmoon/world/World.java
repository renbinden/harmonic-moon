package io.github.lucariatias.harmonicmoon.world;

import com.google.gson.Gson;
import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.block.Block;
import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.door.Door;
import io.github.lucariatias.harmonicmoon.door.DoorMetadata;
import io.github.lucariatias.harmonicmoon.npc.NPCMetadata;
import io.github.lucariatias.harmonicmoon.npc.SoldierNPC;
import io.github.lucariatias.harmonicmoon.tile.Tile;
import io.github.lucariatias.harmonicmoon.tile.TileLayer;
import io.github.lucariatias.harmonicmoon.tile.TileSheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class World {

    private HarmonicMoon harmonicMoon;
    private String name;
    private WorldMetadata worldMetadata;
    private EnumMap<TileLayer, Set<Tile>> tiles;
    private Set<WorldObject> objects;
    private Map<Integer, TileSheet> tileSheets;

    private World(HarmonicMoon harmonicMoon, String name) {
        this.harmonicMoon = harmonicMoon;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void onTick() {
        for (WorldObject object : objects) {
            object.onTick();
        }
    }

    public Set<Tile> getTiles(TileLayer layer) {
        if (tiles.get(layer) == null) {
            tiles.put(layer, new HashSet<Tile>());
        }
        return tiles.get(layer);
    }

    public void render(Graphics graphics) {
        for (Tile tile : getTiles(TileLayer.BACK)) {
            tile.render(graphics, TileLayer.BACK);
        }
        for (Tile tile : getTiles(TileLayer.BACK_TOP)) {
            tile.render(graphics, TileLayer.BACK_TOP);
        }
        WorldObject[] sortedObjects = objects.toArray(new WorldObject[objects.size()]);
        quickSort(sortedObjects);
        for (WorldObject object : sortedObjects) {
            if (harmonicMoon.getCamera().getLocation().distanceSquared(object.getLocation()) <= 640000 && object.getLocation().getX() >= harmonicMoon.getCamera().getLocation().getX() - 32 && object.getLocation().getY() >= harmonicMoon.getCamera().getLocation().getY() - 32) {
                object.render(graphics);
            }
        }
        for (Tile tile : getTiles(TileLayer.FRONT)) {
            tile.render(graphics, TileLayer.FRONT);
        }
        for (Tile tile : getTiles(TileLayer.FRONT_TOP)) {
            tile.render(graphics, TileLayer.FRONT_TOP);
        }
    }

    public Set<WorldObject> getObjects() {
        return objects;
    }

    public void addObject(WorldObject object) {
        objects.add(object);
        if (object instanceof Character.World) harmonicMoon.debug(((Character.World) object).getCharacter().getName() + " added to " + getName());
    }

    public void removeObject(WorldObject object) {
        objects.remove(object);
        if (object instanceof Character.World) harmonicMoon.debug(((Character.World) object).getCharacter().getName() + " removed from " + getName());
    }

    private int partition(WorldObject[] objects, int left, int right) {
        int i = left, j = right;
        WorldObject tmp;
        WorldObject pivot = objects[(left + right) / 2];
        while (i <= j) {
            while (objects[i].getLocation().getY() < pivot.getLocation().getY()) i++;
            while (objects[j].getLocation().getY() > pivot.getLocation().getY()) j--;
            if (i <= j) {
                tmp = objects[i];
                objects[i] = objects[j];
                objects[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    private void quickSort(WorldObject[] objects, int left, int right) {
        int index = partition(objects, left, right);
        if (left < index - 1) quickSort(objects, left, index - 1);
        if (index < right) quickSort(objects, index, right);
    }

    private void quickSort(WorldObject[] objects) {
        quickSort(objects, 0, objects.length - 1);
    }

    private WorldObject getObject(Color colour, WorldObjectMetadata metadata) {
        switch (colour.getRed()) {
            case 0:
                switch (colour.getGreen()) {
                    case 0:
                        switch (colour.getBlue()) {
                            case 0: return null;
                            case 1: return new Block();
                            case 2: return new Door(harmonicMoon, (DoorMetadata) metadata);
                            default: return null;
                        }
                    case 1:
                        switch (colour.getBlue()) {
                            case 0: return harmonicMoon.getCharacterManager().getCharacter("lonyre").world();
                            case 1: return harmonicMoon.getCharacterManager().getCharacter("tivor").world();
                            case 2: return harmonicMoon.getCharacterManager().getCharacter("kesowa").world();
                            case 3: return harmonicMoon.getCharacterManager().getCharacter("namapo").world();
                            case 4: return harmonicMoon.getCharacterManager().getCharacter("syalae").world();
                            case 5: return harmonicMoon.getCharacterManager().getCharacter("anaria").world();
                            case 6: return harmonicMoon.getCharacterManager().getCharacter("idain").world();
                            case 7: return harmonicMoon.getCharacterManager().getCharacter("seuri").world();
                            case 8: return new SoldierNPC(harmonicMoon, (NPCMetadata) metadata);
                        }
                    default: return null;
                }
            default: return null;
        }
    }

    private Class<? extends WorldObjectMetadata> getMetadataClass(Color colour) {
        switch (colour.getRed()) {
            case 0:
                switch (colour.getGreen()) {
                    case 0:
                        switch (colour.getBlue()) {
                            case 2: return DoorMetadata.class;
                            default: return WorldObjectMetadata.class;
                        }
                    case 1:
                        switch (colour.getBlue()) {
                            case 8: return NPCMetadata.class;
                            default: return WorldObjectMetadata.class;
                        }
                    default: return WorldObjectMetadata.class;
                }
            default: return WorldObjectMetadata.class;
        }
    }

    public static World load(HarmonicMoon harmonicMoon, String name) throws MalformedWorldSaveException {
        Gson gson = new Gson();
        World world = new World(harmonicMoon, name);
        InputStream worldMetadataInputStream = World.class.getResourceAsStream("/maps/" + name + "/metadata.json");
        if (worldMetadataInputStream == null) {
            throw new MalformedWorldSaveException("World metadata file does not exist.");
        } else {
            Scanner scanner = new Scanner(worldMetadataInputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine()).append("\n");
            }
            String json = builder.toString();
            world.worldMetadata = gson.fromJson(json, WorldMetadata.class);
        }
        world.tileSheets = new HashMap<>();
        world.tiles = new EnumMap<>(TileLayer.class);
        for (TileLayer layer : TileLayer.values()) {
            InputStream tilesInputStream = World.class.getResourceAsStream("/maps/" + name + "/tiles/" + layer.toString().toLowerCase() + ".png");
            if (tilesInputStream == null)
                throw new MalformedWorldSaveException("Tile map does not exist for layer " + layer.toString().toLowerCase() + ".");
            try {
                BufferedImage tiles = ImageIO.read(tilesInputStream);
                for (int x = 0; x < world.getWidth(); x++) {
                    for (int y = 0; y < world.getHeight(); y++) {
                        int colour = tiles.getRGB(x, y);
                        int r = (colour >> 16) & 0xff;
                        int g = (colour >> 8) & 0xff;
                        int b = colour & 0xff;
                        TileSheet tileSheet;
                        if (!world.getTileSheets().containsKey(b)) {
                            tileSheet = new TileSheet(harmonicMoon, ImageIO.read(World.class.getResourceAsStream("/maps/" + name + "/tilesheets/" + b + "/tilesheet.png")), 16, 16);
                            world.getTileSheets().put(b, tileSheet);
                        } else {
                            tileSheet = world.getTileSheets().get(b);
                        }
                        if (layer == TileLayer.BACK || r != 0 || g != 0 || b != 0) {
                            Tile tile = tileSheet.getTile(r, g);
                            tile.getLocations(layer).add(new WorldLocation(world, x * tileSheet.getTileWidth(), y * tileSheet.getTileHeight()));
                            world.getTiles(layer).add(tile);
                        }
                    }
                }
                tiles.flush();
            } catch (IOException exception) {
                throw new MalformedWorldSaveException("Failed to load tile map for layer " + layer.toString().toLowerCase() + ".", exception);
            }
        }
        world.objects = new HashSet<>();
        InputStream objectsInputStream = World.class.getResourceAsStream("/maps/" + name + "/objects/objects.png");
        if (objectsInputStream == null) throw new MalformedWorldSaveException("Objects file does not exist.");
        try {
            BufferedImage objects = ImageIO.read(objectsInputStream);
            for (int x = 0; x < world.getWidth(); x++) {
                for (int y = 0; y < world.getHeight(); y++) {
                    int colour = objects.getRGB(x, y);
                    int r = (colour >> 16) & 0xff;
                    int g = (colour >> 8) & 0xff;
                    int b = colour & 0xff;
                    if (r != 0 || g != 0 || b != 0) {
                        WorldObjectMetadata metadata = null;
                        InputStream objectMetadataInputStream = World.class.getResourceAsStream("/maps/" + name + "/objects/metadata/" + x + "/" + y + "/metadata.json");
                        if (objectMetadataInputStream != null) {
                            Scanner scanner = new Scanner(objectMetadataInputStream);
                            StringBuilder builder = new StringBuilder();
                            while (scanner.hasNextLine()) {
                                builder.append(scanner.nextLine()).append("\n");
                            }
                            String json = builder.toString();
                            metadata = gson.fromJson(json, world.getMetadataClass(new Color(r, g, b)));
                        }
                        WorldObject object = world.getObject(new Color(r, g, b), metadata);
                        object.setLocation(new WorldLocation(world, x * world.getTileSheets().get(0).getTileWidth(), y * world.getTileSheets().get(0).getTileHeight()));
                        world.addObject(object);
                    }
                }
            }
            objects.flush();
        } catch (IOException exception) {
            throw new MalformedWorldSaveException("Failed to load objects.", exception);
        }
        return world;
    }

    public Map<Integer, TileSheet> getTileSheets() {
        return tileSheets;
    }

    public WorldMetadata getMetadata() {
        return worldMetadata;
    }

    public int getWidth() {
        return getMetadata().getWidth();
    }

    public int getHeight() {
        return getMetadata().getHeight();
    }

    public void playMusic() {
        harmonicMoon.getMusicPlayer().loop("/music/" + getMetadata().getMusic() + ".ogg");
    }

}
