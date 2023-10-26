package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class OldMan extends Entity {
    public OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        this.hitBox = new Rectangle();
        this.hitBox.x = 8;
        this.hitBox.y = 16;
        this.hitBoxDefaultX = this.hitBox.x;
        this.hitBoxDefaultY = this.hitBox.y;
        this.hitBox.width = 32; // 32
        this.hitBox.height = 32; // 32

        getImage();
        setDialogues();
    }

    public void getImage() {
        up1 = setupImage("/npc/oldman_up_1");
        up2 = setupImage("/npc/oldman_up_2");
        down1 = setupImage("/npc/oldman_down_1");
        down2 = setupImage("/npc/oldman_down_2");
        left1 = setupImage("/npc/oldman_left_1");
        left2 = setupImage("/npc/oldman_left_2");
        right1 = setupImage("/npc/oldman_right_1");
        right2 = setupImage("/npc/oldman_right_2");
    }

    public void setDialogues() {
        dialogues[0] = "Hello!";
        dialogues[1] = "Find thy treasure...";
    }

    @Override
    public void setAction() {
        actionCounter++;
        Random rnd = new Random();
        if (actionCounter == 120) {
            int i = rnd.nextInt(4);
            if (i == 0) {
                direction = "up";
            } else if (i == 1) {
                direction = "down";
            } else if (i == 2) {
                direction = "left";
            } else if (i == 3) {
                direction = "right";
            }
            actionCounter = 0;
        }
    }

    @Override
    public void speak() {
        super.speak();
    }

}
