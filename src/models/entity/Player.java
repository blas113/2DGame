package models.entity;

import models.GamePanel;
import models.KeyHandler;
import exceptions.ResourceLoadException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    private GamePanel gp;
    private KeyHandler keyH;
    private final int screenX;
    private final int screenY;

    private int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        // half point of the screen
        this.screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2); // Subtract a half tile length from both screenX and screenY
        this.screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

        // Coliision detection (not the whole character)
        setSolidArea(new Rectangle()); // 8 16 32 32
        getSolidArea().x = 8;
        getSolidArea().y = 16;
        getSolidArea().width = 32;
        getSolidArea().height = 32;
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);


        setDefaultValues();
        try {
            getPlayerImage();
        } catch (ResourceLoadException e) {
            System.err.println("Error fatal: No se pudieron cargar las imágenes del jugador");
            e.printStackTrace();
        }

    }
    
    /**
     * Implementación del método abstracto speak().
     * El jugador puede tener diálogos específicos.
     */
    @Override
    public void speak() {
        // El jugador no habla, pero puede interactuar con NPCs
        gp.getGameUI().setCurrentDialogue("...");
    }
    
    // Getters y Setters
    public int getScreenX() {
        return screenX;
    }
    
    public int getScreenY() {
        return screenY;
    }
    
    public int getHasKey() {
        return hasKey;
    }
    
    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }
    
    public KeyHandler getKeyHandler() {
        return keyH;
    }

    public void setDefaultValues(){
        // "Respawn"
        setWorldX(gp.getTileSize() * 10);
        setWorldY(gp.getTileSize() * 44);
        setSpeed(4);
        setDirection("down");
    }

    /**
     * Carga las imágenes del jugador.
     * Aplica el principio GRASP de Information Expert.
     * 
     * @throws ResourceLoadException Si no se pueden cargar las imágenes
     */
    public void getPlayerImage() throws ResourceLoadException {
        try{
            // BOY
            setBup1(loadPlayerImage("/res/player/Walking_sprites/boy_up_1.png"));
            setBup2(loadPlayerImage("/res/player/Walking_sprites/boy_up_2.png"));
            setBdown1(loadPlayerImage("/res/player/Walking_sprites/boy_down_1.png"));
            setBdown2(loadPlayerImage("/res/player/Walking_sprites/boy_down_2.png"));
            setBleft1(loadPlayerImage("/res/player/Walking_sprites/boy_left_1.png"));
            setBleft2(loadPlayerImage("/res/player/Walking_sprites/boy_left_2.png"));
            setBright1(loadPlayerImage("/res/player/Walking_sprites/boy_right_1.png"));
            setBright2(loadPlayerImage("/res/player/Walking_sprites/boy_right_2.png"));

            // GIRL
            setGup1(loadPlayerImage("/res/player/Walking_sprites/girl_up_1.png"));
            setGup2(loadPlayerImage("/res/player/Walking_sprites/girl_up_2.png"));
            setGdown1(loadPlayerImage("/res/player/Walking_sprites/girl_down_1.png"));
            setGdown2(loadPlayerImage("/res/player/Walking_sprites/girl_down_2.png"));
            setGleft1(loadPlayerImage("/res/player/Walking_sprites/girl_left_1.png"));
            setGleft2(loadPlayerImage("/res/player/Walking_sprites/girl_left_2.png"));
            setGright1(loadPlayerImage("/res/player/Walking_sprites/girl_right_1.png"));
            setGright2(loadPlayerImage("/res/player/Walking_sprites/girl_right_2.png"));

        } catch (ResourceLoadException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceLoadException("/res/player/Walking_sprites/", e);
        }
    }
    
    /**
     * Método helper para cargar una imagen del jugador.
     * 
     * @param path Ruta de la imagen
     * @return La imagen cargada
     * @throws ResourceLoadException Si la imagen no se puede cargar
     */
    private BufferedImage loadPlayerImage(String path) throws ResourceLoadException {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            if (image == null) {
                throw new ResourceLoadException(path);
            }
            return image;
        } catch (IOException e) {
            throw new ResourceLoadException(path, e);
        }
    }


    public void update(){

        if (keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed()){
            if (keyH.isUpPressed()) {
                setDirection("up");
            } else if (keyH.isDownPressed()) {
                setDirection("down");
            } else if (keyH.isRightPressed()) {
                setDirection("right");
            } else if  (keyH.isLeftPressed()){
                setDirection("left");
            }

            // CHECK TILE COLLISION
            setCollisionOn(false);
            gp.getCollisionChecker().checkTile(this);

            // CHECK OBJ COLLISION
            int objIndex = gp.getCollisionChecker().checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            gp.getCollisionChecker().checkNPC(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!isCollisionOn()){
                switch (getDirection()){
                    case "up":
                        setWorldY(getWorldY() - getSpeed());
                        break;
                    case "down":
                        setWorldY(getWorldY() + getSpeed());
                        break;
                    case "left":
                        setWorldX(getWorldX() - getSpeed());
                        break;
                    case "right":
                        setWorldX(getWorldX() + getSpeed());
                        break;
                }
            }

            gp.getEventHandler().checkEvent();

            // Update is executed 60 times per second
            incrementSpriteCounter();
            if(getSpriteCounter() > 12) {
                if(getSpriteNum() == 1){
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }

    }

    public void pickUpObject(int i){
        if(i != 999) {
            String objectName = gp.getObjects()[i].getName();

            switch(objectName){
                case "accesscard":
                    setHasKey(getHasKey() + 1);
                    gp.getObjects()[i] = null;
                    gp.getGameUI().setCurrentDialogue("Has encontrado una tarjeta!");
                    gp.setGameState(GamePanel.dialogueState);
                    break;
                case "door", "molinete":
                    if (getHasKey() > 0) {
                        gp.getObjects()[i] = null;
                        setHasKey(getHasKey() - 1);
                        gp.getGameUI().setCurrentDialogue("Usaste la tarjeta y abriste \nla puerta!");
                    } else {
                        gp.getGameUI().setCurrentDialogue("La puerta está \ncerrada con llave.");
                    }
                    gp.setGameState(GamePanel.dialogueState);
                    break;
                case "hallwaywarning":
                    gp.setGameState(GamePanel.gameOverState);
                    break;
                case "qrcode":
                    setHasKey(getHasKey() + 1);
                    gp.getObjects()[i] = null;
                    gp.getGameUI().setCurrentDialogue("Has encontrado un QR \nUsalo para escapar!");
                    gp.getNPCs()[1].setSpeed(2);
                    gp.setGameState(GamePanel.dialogueState);
                    break;
                case "exitwarning":
                    gp.setGameState(GamePanel.gameOverState);
                    break;

            }
        }

    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        switch (getDirection()){
            case "up":
                if(getSpriteNum() == 1){
                    image = getBup1();
                } else {
                    image = getBup2();
                }
                break;
            case "down":
                if(getSpriteNum() == 1){
                    image = getBdown1();
                } else {
                    image = getBdown2();
                }
                break;
            case "left":
                if(getSpriteNum() == 1){
                    image = getBleft1();
                } else {
                    image = getBleft2();
                }
                break;
            case "right":
                if(getSpriteNum() == 1){
                    image = getBright1();
                } else {
                    image = getBright2();
                }
                break;
        }

        g2d.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
    }
}
