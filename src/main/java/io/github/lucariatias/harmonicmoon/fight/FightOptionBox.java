package io.github.lucariatias.harmonicmoon.fight;

import io.github.lucariatias.harmonicmoon.HarmonicMoon;
import io.github.lucariatias.harmonicmoon.character.Character;
import io.github.lucariatias.harmonicmoon.skill.Skill;

import javax.imageio.ImageIO;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FightOptionBox {

    private HarmonicMoon harmonicMoon;
    private BufferedImage image;
    private FightOption[] options;

    private boolean mousePressed;

    private Character character;

    public FightOptionBox(final HarmonicMoon harmonicMoon, FightPanel fightPanel) {
        this.harmonicMoon = harmonicMoon;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/message.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.options = new FightOption[] {
                new FightOption("Use skill", new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            final Skill skill = character.fight().getSkills().get(i);
                            options[i] = new FightOption(skill.getName(), new Runnable() {
                                @Override
                                public void run() {
                                    character.fight().useSkill(skill);
                                }
                            });
                        }
                    }
                }),
                new FightOption("Defend", new Runnable() {
                    @Override
                    public void run() {

                    }
                }),
                new FightOption("Use item", new Runnable() {
                    @Override
                    public void run() {

                    }
                }),
                new FightOption("Run", new Runnable() {
                    @Override
                    public void run() {

                    }
                })
        };
        fightPanel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                mousePressed = false;
            }
        });
    }

    public void setOptions(FightOption... options) {
        this.options = options;
    }

    public FightOption[] getOptions() {
        return options;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(image, 0, 480 - image.getHeight(), null);
        if (options != null) {
            int x = 16, y = 496 - image.getHeight();
            for (FightOption option : options) {
                Rectangle optionBounds = new Rectangle(x, y, image.getWidth() / 2 - 32, image.getHeight() / 2 - 32);
                Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                mousePoint.translate(- (int) harmonicMoon.getLocationOnScreen().getX(), - (int) harmonicMoon.getLocationOnScreen().getY());
                if (optionBounds.contains(mousePoint)) {
                    graphics.setColor(mousePressed ? Color.DARK_GRAY : Color.LIGHT_GRAY);
                    for (int i = -4; i < 4; i++) {
                        graphics.drawRect((int) optionBounds.getX() - i, (int) optionBounds.getY() - i, (int) optionBounds.getWidth() + (2 * i), (int) optionBounds.getHeight() + (2 * i));
                    }
                }
                graphics.setColor(Color.WHITE);
                graphics.drawRect((int) optionBounds.getX(), (int) optionBounds.getY(), (int) optionBounds.getWidth(), (int) optionBounds.getHeight());
                graphics.setFont(harmonicMoon.getMessageFont());
                graphics.drawString(option.getName(), x + 16, y + 24);
                y += 48;
                if ((y - (496 - image.getHeight())) / 48 == 2) {
                    y = 496 - image.getHeight();
                    x += image.getWidth() / 2;
                }
            }
        }
    }

}
