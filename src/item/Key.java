package item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends Item {
    public Key (GamePanel gp, int id) {
        this.gp = gp;
        this.name = "Key";
        this.soundIndex = 1;
        this.id = id;

        setupImage();
    }

    private void setupImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/key.png"));
            ut.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
