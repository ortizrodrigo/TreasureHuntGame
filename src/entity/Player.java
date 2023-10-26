package entity;

import item.Item;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Player extends Entity {
    KeyHandler kh;

    public final int SCREEN_X;
    public final int SCREEN_Y;
    public Set<Integer> keyIds; // EDIT

    public Player(GamePanel gp, KeyHandler kh) {
        super(gp);

        this.kh = kh;
        this.SCREEN_X = (gp.SCREEN_WIDTH / 2) - (gp.TILE_SIZE / 2);
        this.SCREEN_Y = (gp.SCREEN_HEIGHT / 2) - (gp.TILE_SIZE / 2);
        this.hitBox = new Rectangle();
        this.hitBox.x = 8;
        this.hitBox.y = 16;
        this.hitBoxDefaultX = this.hitBox.x;
        this.hitBoxDefaultY = this.hitBox.y;
        this.hitBox.width = 24; // 32
        this.hitBox.height = 24; // 32
        this.keyIds = new TreeSet<Integer>(); // EDIT

        setDefaultVariables();
        getPlayerImage();
    }

    public void setDefaultVariables() {
        this.worldX = gp.TILE_SIZE * 23;
        this.worldY = gp.TILE_SIZE * 21;
        this.speed = 4;
        this.direction = "down";
    }

    public void getPlayerImage() {
        up1 = setupImage("/player/boy_up_1");
        up2 = setupImage("/player/boy_up_2");
        down1 = setupImage("/player/boy_down_1");
        down2 = setupImage("/player/boy_down_2");
        left1 = setupImage("/player/boy_left_1");
        left2 = setupImage("/player/boy_left_2");
        right1 = setupImage("/player/boy_right_1");
        right2 = setupImage("/player/boy_right_2");
    }

    @Override
    public void update() {
        if (kh.up || kh.down || kh.left || kh.right || kh.enter) {
            if (kh.up) {
                direction = "up";
            } else if (kh.down) {
                direction = "down";
            } else if (kh.left) {
                direction = "left";
            } else if (kh.right) {
                direction = "right";
            }

            // Handle Tile Collision
            collisionOn = false;
            gp.ch.checkTile(this);

            // Handle Item Collision
            int itemIndex = gp.ch.checkItem(this, true);
            pickUpItem(itemIndex);

            int npcIndex = gp.ch.checkEntity(this, gp.NPCs);
            interactWithNPC(npcIndex);

            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    private void pickUpItem(int i) {
        if (i != -1) {
            // EDIT
            ///*
            Item item = gp.items[i];
            switch (item.name) {
                case "Key":
                    keyIds.add(item.id);
                    gp.playSoundEffect(item.soundIndex);
                    gp.items[i] = null;
                    gp.ui.showMessage("Found key number " + item.id + "!");
                    break;
                case "Door":
                    boolean locked = true;
                    for (int id : keyIds) {
                        if (item.id == id) {
                            gp.playSoundEffect(item.soundIndex);
                            gp.items[i] = null;
                            keyIds.remove(id);
                            System.out.println();
                            gp.ui.showMessage("Opened door number " + item.id + "!");
                            locked = false;
                        }
                    }
                    if (locked) {
                        gp.ui.showMessage("Door number " + item.id + " is locked!");
                    }
                    break;
                case "Chest":
                    gp.ui.endGame();
                    break;
                case "Boots":
                    speed += 2;
                    gp.ui.showMessage("Found speed boots!");
                    gp.playSoundEffect(item.soundIndex);
                    gp.items[i] = null;
                    break;
            }
            //*/
        }
    }

    public void interactWithNPC(int i) {
        if (i != -1) {
            if (kh.enter) {
                gp.gameState = gp.DIALOGUE_STATE;
                gp.NPCs[i].speak();
            }
        }
        kh.enter = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                image = left1;
            } else if (spriteNum == 2) {
                image = left2;
            }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, SCREEN_X, SCREEN_Y, null);

    }

}
