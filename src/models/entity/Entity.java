package models.entity;

import models.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    private GamePanel gp;
    private int worldX;
    private int worldY;
    private int speed;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private BufferedImage bup1, bup2, bdown1, bdown2, bleft1, bleft2, bright1, bright2;
    private BufferedImage gup1, gup2, gdown1, gdown2, gleft1, gleft2, gright1, gright2;
    private String direction;

    private int spriteCounter = 0;
    private int spriteNum = 1;

    // Collision detection
    private Rectangle solidArea;
    private boolean collisionOn = false;

    private int solidAreaDefaultX;
    private int solidAreaDefaultY;

    private String[] dialogues = new String[20];
    private int dialogueIndex = 0;

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
    
    // Getters y Setters
    public GamePanel getGamePanel() {
        return gp;
    }
    
    public int getWorldX() {
        return worldX;
    }
    
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }
    
    public int getWorldY() {
        return worldY;
    }
    
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public BufferedImage getUp1() { return up1; }
    public void setUp1(BufferedImage up1) { this.up1 = up1; }
    
    public BufferedImage getUp2() { return up2; }
    public void setUp2(BufferedImage up2) { this.up2 = up2; }
    
    public BufferedImage getDown1() { return down1; }
    public void setDown1(BufferedImage down1) { this.down1 = down1; }
    
    public BufferedImage getDown2() { return down2; }
    public void setDown2(BufferedImage down2) { this.down2 = down2; }
    
    public BufferedImage getLeft1() { return left1; }
    public void setLeft1(BufferedImage left1) { this.left1 = left1; }
    
    public BufferedImage getLeft2() { return left2; }
    public void setLeft2(BufferedImage left2) { this.left2 = left2; }
    
    public BufferedImage getRight1() { return right1; }
    public void setRight1(BufferedImage right1) { this.right1 = right1; }
    
    public BufferedImage getRight2() { return right2; }
    public void setRight2(BufferedImage right2) { this.right2 = right2; }
    
    public BufferedImage getBup1() { return bup1; }
    public void setBup1(BufferedImage bup1) { this.bup1 = bup1; }
    
    public BufferedImage getBup2() { return bup2; }
    public void setBup2(BufferedImage bup2) { this.bup2 = bup2; }
    
    public BufferedImage getBdown1() { return bdown1; }
    public void setBdown1(BufferedImage bdown1) { this.bdown1 = bdown1; }
    
    public BufferedImage getBdown2() { return bdown2; }
    public void setBdown2(BufferedImage bdown2) { this.bdown2 = bdown2; }
    
    public BufferedImage getBleft1() { return bleft1; }
    public void setBleft1(BufferedImage bleft1) { this.bleft1 = bleft1; }
    
    public BufferedImage getBleft2() { return bleft2; }
    public void setBleft2(BufferedImage bleft2) { this.bleft2 = bleft2; }
    
    public BufferedImage getBright1() { return bright1; }
    public void setBright1(BufferedImage bright1) { this.bright1 = bright1; }
    
    public BufferedImage getBright2() { return bright2; }
    public void setBright2(BufferedImage bright2) { this.bright2 = bright2; }
    
    public BufferedImage getGup1() { return gup1; }
    public void setGup1(BufferedImage gup1) { this.gup1 = gup1; }
    
    public BufferedImage getGup2() { return gup2; }
    public void setGup2(BufferedImage gup2) { this.gup2 = gup2; }
    
    public BufferedImage getGdown1() { return gdown1; }
    public void setGdown1(BufferedImage gdown1) { this.gdown1 = gdown1; }
    
    public BufferedImage getGdown2() { return gdown2; }
    public void setGdown2(BufferedImage gdown2) { this.gdown2 = gdown2; }
    
    public BufferedImage getGleft1() { return gleft1; }
    public void setGleft1(BufferedImage gleft1) { this.gleft1 = gleft1; }
    
    public BufferedImage getGleft2() { return gleft2; }
    public void setGleft2(BufferedImage gleft2) { this.gleft2 = gleft2; }
    
    public BufferedImage getGright1() { return gright1; }
    public void setGright1(BufferedImage gright1) { this.gright1 = gright1; }
    
    public BufferedImage getGright2() { return gright2; }
    public void setGright2(BufferedImage gright2) { this.gright2 = gright2; }
    
    public String getDirection() {
        return direction;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    public int getSpriteCounter() {
        return spriteCounter;
    }
    
    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }
    
    public void incrementSpriteCounter() {
        this.spriteCounter++;
    }
    
    public int getSpriteNum() {
        return spriteNum;
    }
    
    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }
    
    public Rectangle getSolidArea() {
        return solidArea;
    }
    
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
    
    public boolean isCollisionOn() {
        return collisionOn;
    }
    
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
    
    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }
    
    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }
    
    public String[] getDialogues() {
        return dialogues;
    }
    
    public void setDialogues(String[] dialogues) {
        this.dialogues = dialogues;
    }
    
    public int getDialogueIndex() {
        return dialogueIndex;
    }
    
    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }
    
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (getWorldX() + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                getWorldX() - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                getWorldY() + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                getWorldY() - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {


            switch (getDirection()){
                case "up":
                    if(getSpriteNum() == 1){
                        image = getUp1();
                    } else {
                        image = getUp2();
                    }
                    break;
                case "down":
                    if(getSpriteNum() == 1){
                        image = getDown1();
                    } else {
                        image = getDown2();
                    }
                    break;
                case "left":
                    if(getSpriteNum() == 1){
                        image = getLeft1();
                    } else {
                        image = getLeft2();
                    }
                    break;
                case "right":
                    if(getSpriteNum() == 1){
                        image = getRight1();
                    } else {
                        image = getRight2();
                    }
                    break;
            }

            if(image != null) {
                g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
        }
    }
}
