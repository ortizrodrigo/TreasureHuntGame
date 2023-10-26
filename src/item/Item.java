package item;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    public static final int DEFAULT_ID = -1;
    public int id = DEFAULT_ID;
    GamePanel gp;

    public BufferedImage image;
    public String name;
    public int soundIndex;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    public int hitBoxDefaultX = 0;
    public int hitBoxDefaultY = 0;
    UtilityTool ut = new UtilityTool();


    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X;
        int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;

        if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X &&
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.SCREEN_X &&
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.SCREEN_Y &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y) {
            g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
    }

}
