package item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Boots extends Item {
    public Boots (GamePanel gp) {
        this.gp = gp;
        this.name = "Boots";
        this.soundIndex = 2;


        setupImage();
    }

    private void setupImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/boots.png"));
            ut.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
