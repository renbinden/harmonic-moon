package io.github.lucariatias.harmonicmoon.door;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.world.WorldLocation;
import io.github.lucariatias.harmonicmoon.world.WorldObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Door extends WorldObject {

    private HarmonicMoon harmonicMoon;

    public Door(HarmonicMoon harmonicMoon) {
        this.harmonicMoon = harmonicMoon;
        setSolid(true);
    }

    private Node getChildNode(Node parent, String childName) {
        for (int i = 0; i < parent.getChildNodes().getLength(); i++) {
            Node child = parent.getChildNodes().item(i);
            if (child.getNodeName().equals(childName)) {
                return child;
            }
        }
        return null;
    }

    @Override
    public void onTick() {}

    @Override
    public void render(Graphics graphics) {}

    @Override
    public Rectangle getBoundsAtPosition(WorldLocation location) {
        return new Rectangle(location.getX(), location.getY(), 16, 16);
    }

    @Override
    public void interact() {
        WorldLocation entryLocation = null;
        InputStream metadata = getClass().getResourceAsStream("/maps/" + getLocation().getWorld().getName() + "/metadata/doors/" + getLocation().getX() / 16 + "_" + getLocation().getY() / 16 + ".xml");
        if (metadata != null) {
            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = builder.parse(metadata);
                NodeList nodes = document.getElementsByTagName("door");
                Node entryLocationNode = getChildNode(nodes.item(0), "entryLocation");
                entryLocation = new WorldLocation(harmonicMoon.getWorld(getChildNode(getChildNode(entryLocationNode, "world"), "#text").getNodeValue()), Integer.parseInt(getChildNode(getChildNode(entryLocationNode, "x"), "#text").getNodeValue()) * 16, Integer.parseInt(getChildNode(getChildNode(entryLocationNode, "y"), "#text").getNodeValue()) * 16);
            } catch (ParserConfigurationException | SAXException | IOException exception) {
                exception.printStackTrace();
            }
        }
        if (entryLocation != null) {
            harmonicMoon.getWorldPanel().getWorld().removeObject(harmonicMoon.getWorldPanel().getPlayer().getCharacter().world());
            harmonicMoon.showWorld(entryLocation.getWorld().getName());
            harmonicMoon.getWorldPanel().getPlayer().getCharacter().world().setLocation(entryLocation);
            entryLocation.getWorld().addObject(harmonicMoon.getPlayer().getCharacter().world());
        }
    }

}
