package main;

import entity.Entity;
import entity.Player;
import item.Item;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int DEFAULT_TILE_SIZE = 16; // 16x16
    final int SCALE = 3; // 3

    public final int TILE_SIZE = DEFAULT_TILE_SIZE * SCALE;
    public final int MAX_SCREEN_COL = 16; // 16
    public final int MAX_SCREEN_ROW = 12; // 12
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    // World Settings
    public final int MAX_WORLD_COL = 50;
    public final int MAX_WORLD_ROW = 50;

    final int FPS = 60;

    TileManager tm = new TileManager(this);
    KeyHandler kh = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffects = new Sound();
    public CollisionHandler ch = new CollisionHandler(this);
    public AssetSetter as = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    public Player player = new Player(this,  kh);
    public Item[] items = new Item[10];
    public Entity[] NPCs = new Entity[10];

    public int gameState;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int DIALOGUE_STATE = 3;


    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.kh);
        this.setFocusable(true);
    }

    public void setupGame() {
        as.setItems();
        as.setNPCs();
        playMusic(0);

        gameState = PLAY_STATE;
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval, nextDrawTime, remainingTime;
        drawInterval = 1000000000 / FPS;
        nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            this.update();
            this.repaint();
            try {
                remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                remainingTime = Double.max(remainingTime, 0.0);
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameState == PLAY_STATE) {
            player.update();
            for (Entity npc : NPCs) {
                if (npc != null) {
                    npc.update();
                }
            }
        }
        if (gameState == PAUSE_STATE) {
            // Pause
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Performance test
        long drawStart = 0;
        if (kh.checkDrawTime) {
            drawStart = System.nanoTime();
        }


        // Draw tiles
        tm.draw(g2);

        // Draw items
        for (Item item : items) {
            if (item != null) {
                item.draw(g2, this);
            }
        }

        // Draw NPCs
        for (Entity npc : NPCs) {
            if (npc != null) {
                npc.draw(g2);
            }
        }

        // Draw player
        player.draw(g2);

        // Draw User Interface
        ui.draw(g2);

        // Performance test
        if (kh.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long drawTime = drawEnd - drawStart;
            System.out.println(drawTime);
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + drawTime, 10, 400);
        }


        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffects.setFile(i);
        soundEffects.play();
    }

}
