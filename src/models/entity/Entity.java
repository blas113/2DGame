package models.entity;

import models.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
//    GamePanel gp;
//    public int worldX, worldY;
//    public int y;
//    public int speed;
//
//    public BufferedImage bup1, bup2, bdown1, bdown2, bleft1, bleft2, bright1, bright2, gup1, gup2, gdown1, gdown2, gleft1, gleft2, gright1, gright2;
//    public String direction;
//
//    public int spriteCounter = 0;
//    public int spriteNum = 1;
//
//    // Collision detection
//    public Rectangle solidArea;
//    public boolean collisionOn = false;
//
//    public int solidAreaDefaultX, solidAreaDefaultY;
    GamePanel gp;
    public int worldX, worldY;
    public int y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage bup1, bup2, bdown1, bdown2, bleft1, bleft2, bright1, bright2;
    public BufferedImage gup1, gup2, gdown1, gdown2, gleft1, gleft2, gright1, gright2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter = 0;

    // Collision detection
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int solidAreaDefaultX, solidAreaDefaultY;

    //public String dialogues[] = new String[20];
    public String[] dialogues = new String[20];
    public int dialogueIndex = 0;

    public void speak(){}

    public void update() {
        // Método vacío por defecto, las subclases pueden sobrescribirlo
    }

    public BufferedImage setup(String imagePath) {
        try {
            return ImageIO.read(getClass().getResourceAsStream("/res/" + imagePath));
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            return null;
        }
    }

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {


            switch (direction){
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    } else {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    } else {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    } else {
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    } else {
                        image = right2;
                    }
                    break;
            }

            if(image != null) {
                g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
        }
    }
}
