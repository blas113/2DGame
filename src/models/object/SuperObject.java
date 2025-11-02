package models.object;

import models.GamePanel;
import interfaces.DrawableWithContext;
import interfaces.Collidable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Clase base abstracta para objetos del juego.
 * Aplica el principio GRASP de Information Expert y Low Coupling.
 */
public abstract class SuperObject implements DrawableWithContext, Collidable {
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
    
    /**
     * Implementación de Collidable.isCollisionOn().
     * SuperObject usa un campo 'collision' en lugar de 'collisionOn'.
     */
    @Override
    public boolean isCollisionOn() {
        return collision;
    }
    
    /**
     * Implementación de Collidable.setCollisionOn().
     * SuperObject usa un campo 'collision' en lugar de 'collisionOn'.
     */
    @Override
    public void setCollisionOn(boolean collisionOn) {
        this.collision = collisionOn;
    }

    /**
     * Dibuja el objeto en el contexto gráfico.
     * Implementa DrawableWithContext para reducir acoplamiento.
     * 
     * @param g2 El contexto gráfico
     * @param context El contexto (debe ser GamePanel)
     */
    @Override
    public void draw(Graphics2D g2, Object context) {
        if (!(context instanceof GamePanel)) {
            throw new IllegalArgumentException("El contexto debe ser una instancia de GamePanel");
        }
        
        GamePanel gp = (GamePanel) context;
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
