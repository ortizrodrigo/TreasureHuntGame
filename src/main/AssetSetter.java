package main;

import entity.OldMan;
import item.Boots;
import item.Chest;
import item.Door;
import item.Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setItems() {
        ///*
        gp.items[0] = new Key(gp, 0);
        gp.items[0].worldX = 23 * gp.TILE_SIZE;
        gp.items[0].worldY = 7 * gp.TILE_SIZE;

        gp.items[1] = new Key(gp, 1);
        gp.items[1].worldX = 23 * gp.TILE_SIZE;
        gp.items[1].worldY = 40 * gp.TILE_SIZE;

        gp.items[2] = new Key(gp, 2);
        gp.items[2].worldX = 38 * gp.TILE_SIZE;
        gp.items[2].worldY = 8 * gp.TILE_SIZE;

        gp.items[3] = new Door(gp, 0);
        gp.items[3].worldX = 10 * gp.TILE_SIZE;
        gp.items[3].worldY = 12 * gp.TILE_SIZE;

        gp.items[4] = new Door(gp, 1);
        gp.items[4].worldX = 8 * gp.TILE_SIZE;
        gp.items[4].worldY = 28 * gp.TILE_SIZE;

        gp.items[5] = new Door(gp, 2);
        gp.items[5].worldX = 12 * gp.TILE_SIZE;
        gp.items[5].worldY = 23 * gp.TILE_SIZE;

        gp.items[6] = new Chest(gp);
        gp.items[6].worldX = 10 * gp.TILE_SIZE;
        gp.items[6].worldY = 8 * gp.TILE_SIZE;

        gp.items[7] = new Boots(gp);
        gp.items[7].worldX = 37 * gp.TILE_SIZE;
        gp.items[7].worldY = 42 * gp.TILE_SIZE;

        //*/
    }

    public void setNPCs() {
        gp.NPCs[0] = new OldMan(gp);
        gp.NPCs[0].worldX = gp.TILE_SIZE * 21;
        gp.NPCs[0].worldY = gp.TILE_SIZE * 21;
    }

}
