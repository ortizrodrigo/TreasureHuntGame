package item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends Item {
    public Chest (GamePanel gp) {
        this.gp = gp;
        this.name = "Chest";
        this.soundIndex = 3;

        this.collision = true;
        setupImage();
    }
    public Chest (GamePanel gp, int id) {
        this.gp = gp;
        this.name = "chest";
        this.soundIndex = 3;
        this.id = id;
        this.collision = true;
        setupImage();
    }

    private void setupImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/chest.png"));
            ut.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
