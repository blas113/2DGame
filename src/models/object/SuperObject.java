package models.object;

import models.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int worldX;
    private int worldY;

    // The entire tile
    private Rectangle solidArea = new Rectangle(0,0,48,48);
    private int solidAreaDefaultX = 0;
    private int solidAreaDefaultY = 0;
    
    // Getters y Setters
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isCollision() {
        return collision;
    }
    
    public void setCollision(boolean collision) {
        this.collision = collision;
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
    
    public Rectangle getSolidArea() {
        return solidArea;
    }
    
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
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

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX(); // Where we draw the tiles on the screen
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
            g2.drawImage(this.image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }

    }

}
