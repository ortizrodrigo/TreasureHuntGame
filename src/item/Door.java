package item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends Item {
    public Door (GamePanel gp, int id) {
        this.gp = gp;
        this.name = "Door";
        this.soundIndex = 3;
        this.id = id;
        this.collision = true;
        setupImage();
    }

    private void setupImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/door.png"));
            ut.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
