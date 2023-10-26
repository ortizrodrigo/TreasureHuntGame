package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNumbers[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tiles = new Tile[50];
        mapTileNumbers = new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
        getTileImage();
        loadMap("/maps/worldV2.txt");
    }

    public void getTileImage() {
        setupImage(0, "grass00", false);
        setupImage(1, "grass01", false);

        setupImage(2, "water00", true);
        setupImage(3, "water01", true);
        setupImage(4, "water02", true);
        setupImage(5, "water03", true);
        setupImage(6, "water04", true);
        setupImage(7, "water05", true);
        setupImage(8, "water06", true);
        setupImage(9, "water07", true);
        setupImage(10, "water08", true);
        setupImage(11, "water09", true);
        setupImage(12, "water10", true);
        setupImage(13, "water11", true);
        setupImage(14, "water12", true);
        setupImage(15, "water13", true);

        setupImage(16, "road00", false);
        setupImage(17, "road01", false);
        setupImage(18, "road02", false);
        setupImage(19, "road03", false);
        setupImage(20, "road04", false);
        setupImage(21, "road05", false);
        setupImage(22, "road06", false);
        setupImage(23, "road07", false);
        setupImage(24, "road08", false);
        setupImage(25, "road09", false);
        setupImage(26, "road10", false);
        setupImage(27, "road11", false);
        setupImage(28, "road12", false);

        setupImage(29, "earth", false);

        setupImage(30, "wall", true);

        setupImage(31, "tree", true);
    }

    private void setupImage(int i, String fileName, boolean collision) {
        UtilityTool ut = new UtilityTool();
        try {
            tiles[i] = new Tile();
            tiles[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + fileName + ".png"));
            tiles[i].image = ut.scaleImage(tiles[i].image, gp.TILE_SIZE, gp.TILE_SIZE);
            tiles[i].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.MAX_WORLD_COL && row < gp.MAX_WORLD_ROW) {
                String line = br.readLine();
                line = line.replaceFirst("^\\s+", "");
                while (col < gp.MAX_WORLD_COL) {
                    String[] numbers = line.split("\\s+");
                    mapTileNumbers[col][row] = Integer.parseInt(numbers[col]);
                    col++;
                }
                if (col == gp.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.MAX_WORLD_COL && worldRow < gp.MAX_WORLD_ROW) {

            int tileNum = mapTileNumbers[worldCol][worldRow];

            int worldX = worldCol * gp.TILE_SIZE;
            int worldY = worldRow * gp.TILE_SIZE;
            int screenX = worldX - gp.player.worldX + gp.player.SCREEN_X;
            int screenY = worldY - gp.player.worldY + gp.player.SCREEN_Y;

            if (worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.SCREEN_X &&
                worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.SCREEN_X &&
                worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.SCREEN_Y &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.SCREEN_Y) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
            }

            worldCol++;
            if (worldCol == gp.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
