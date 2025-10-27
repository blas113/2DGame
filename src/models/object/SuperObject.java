//package models.object;
//
//import models.GamePanel;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//
//public class SuperObject {
//    public BufferedImage image;
//    public String name;
//    public boolean collision = false;
//    public int worldX, worldY;
//
//    // The entire tile
//    public Rectangle solidArea = new Rectangle(0,0,48,48);
//    public int solidAreDefaultX= 0;
//    public int solidAreDefaultY= 0;
//
//    public void draw(Graphics2D g2, GamePanel gp){
//        int screenX = worldX - gp.player.worldX + gp.player.screenX; // Where we draw the tiles on the screen
//        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
//            g2.drawImage(this.image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
//        }
//
//    }
//
//}
package models.object;

import models.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX= 0;
    public int solidAreaDefaultY= 0;

    // 🔹 Nuevo: sistema de diálogo propio
    public String[] dialogues = new String[10];
    public int dialogueIndex = 0;

    public void speak(GamePanel gp) {
        if (dialogues[dialogueIndex] != null) {
            gp.ui.currentDialogue = dialogues[dialogueIndex];
        }
    }

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(this.image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }
}
