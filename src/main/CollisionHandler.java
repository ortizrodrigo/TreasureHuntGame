package main;

import entity.Entity;
import item.Item;

public class CollisionHandler {
    GamePanel gp;

    public CollisionHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.hitBox.x;
        int entityRightWorldX = entityLeftWorldX + entity.hitBox.width;
        int entityTopWorldY = entity.worldY + entity.hitBox.y;
        int entityBottomWorldY = entityTopWorldY + entity.hitBox.height;

        int entityLeftCol = entityLeftWorldX / gp.TILE_SIZE;
        int entityRightCol = entityRightWorldX / gp.TILE_SIZE;
        int entityTopRow = entityTopWorldY / gp.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / gp.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tm.mapTileNumbers[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNumbers[entityRightCol][entityTopRow];
                if (gp.tm.tiles[tileNum1].collision || gp.tm.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tm.mapTileNumbers[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tm.mapTileNumbers[entityRightCol][entityBottomRow];
                if (gp.tm.tiles[tileNum1].collision || gp.tm.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tm.mapTileNumbers[entityLeftCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNumbers[entityLeftCol][entityBottomRow];
                if (gp.tm.tiles[tileNum1].collision || gp.tm.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.TILE_SIZE;
                tileNum1 = gp.tm.mapTileNumbers[entityRightCol][entityTopRow];
                tileNum2 = gp.tm.mapTileNumbers[entityRightCol][entityBottomRow];
                if (gp.tm.tiles[tileNum1].collision || gp.tm.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkItem(Entity entity, boolean player) {
        int index = -1;
        for (int i = 0; i < gp.items.length; i++) {
            if (gp.items[i] != null) {
                Item item = gp.items[i];
                // Entity hitBox position
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;
                // Item hitBox position
                item.hitBox.x += item.worldX;
                item.hitBox.y += item.worldY;

                switch (entity.direction) {
                    case "up":
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(item.hitBox)) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(item.hitBox)) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(item.hitBox)) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(item.hitBox)) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                item.hitBox.x = item.hitBoxDefaultX;
                item.hitBox.y = item.hitBoxDefaultY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] targets) {
        int index = -1;
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] != null) {
                Entity target = targets[i];
                // Entity hitBox position
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;
                // Item hitBox position
                target.hitBox.x += target.worldX;
                target.hitBox.y += target.worldY;

                switch (entity.direction) {
                    case "up":
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(target.hitBox)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(target.hitBox)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(target.hitBox)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(target.hitBox)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                target.hitBox.x = target.hitBoxDefaultX;
                target.hitBox.y = target.hitBoxDefaultY;
            }
        }
        return index;
    }

    public void checkPlayer(Entity entity) {
        Entity player = gp.player;
        // Entity hitBox position
        entity.hitBox.x += entity.worldX;
        entity.hitBox.y += entity.worldY;
        // Item hitBox position
        player.hitBox.x += player.worldX;
        player.hitBox.y += player.worldY;

        switch (entity.direction) {
            case "up":
                entity.hitBox.y -= entity.speed;
                if (entity.hitBox.intersects(player.hitBox)) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.hitBox.y += entity.speed;
                if (entity.hitBox.intersects(player.hitBox)) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity.hitBox.x -= entity.speed;
                if (entity.hitBox.intersects(player.hitBox)) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entity.hitBox.x += entity.speed;
                if (entity.hitBox.intersects(player.hitBox)) {
                    entity.collisionOn = true;
                }
                break;
        }
        entity.hitBox.x = entity.hitBoxDefaultX;
        entity.hitBox.y = entity.hitBoxDefaultY;
        player.hitBox.x = player.hitBoxDefaultX;
        player.hitBox.y = player.hitBoxDefaultY;
    }

}
