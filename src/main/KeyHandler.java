package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean up, down, left, right, enter;
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (gp.gameState == gp.PLAY_STATE) {
            if (keyCode == KeyEvent.VK_W) {
                up = true;
            } else if (keyCode == KeyEvent.VK_S) {
                down = true;
            } else if (keyCode == KeyEvent.VK_A) {
                left = true;
            } else if (keyCode == KeyEvent.VK_D) {
                right = true;
            } else if (keyCode == KeyEvent.VK_P) {
                gp.gameState = gp.PAUSE_STATE;
            } else if (keyCode == KeyEvent.VK_ENTER) {
                enter = true;
            }

            // Performance test
            else if (keyCode == KeyEvent.VK_T) {
                checkDrawTime = !checkDrawTime;
            }
        }
        else if (gp.gameState == gp.PAUSE_STATE) {
            if (keyCode == KeyEvent.VK_P) {
                gp.gameState = gp.PLAY_STATE;
            }
        }
        else if (gp.gameState == gp.DIALOGUE_STATE) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gp.gameState = gp.PLAY_STATE;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            up = false;
        } else if (keyCode == KeyEvent.VK_S) {
            down = false;
        } else if (keyCode == KeyEvent.VK_A) {
            left = false;
        } else if (keyCode == KeyEvent.VK_D) {
            right = false;
        }
    }
}
