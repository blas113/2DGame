package models.entity;

import models.GamePanel;
import models.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    public boolean accessCard;
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int  screenY;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        
        this.gp = gp;
        this.keyH = keyH;

        // half point of the screen
        this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // Subtract a half tile length from both screenX and screenY
        this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // Coliision detection (not the whole character)
        solidArea = new Rectangle(); // 8 16 32 32
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultValues();
        getPlayerImage();

    }



    public void setDefaultValues(){
        // "Respawn"
        this.worldX = gp.tileSize * 10;
        this.worldY = gp.tileSize * 44;
        this.speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            // BOY
            bup1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_up_1.png")));
            bup2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_up_2.png")));
            bdown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_down_1.png")));
            bdown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_down_2.png")));
            bleft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_left_1.png")));
            bleft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_left_2.png")));
            bright1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_right_1.png")));
            bright2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_right_2.png")));

            // GIRL
            gup1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/girl_up_1.png")));
            gup2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/girl_up_2.png")));
            gdown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/girl_down_1.png")));
            gdown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/girl_down_2.png")));
            gleft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/girl_left_1.png")));
            gleft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/girl_left_2.png")));
            gright1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/girl_right_1.png")));
            gright2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Walking_sprites/girl_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(){

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPress == true || keyH.rightPressed == true){
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else if  (keyH.leftPress){
                direction = "left";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJ COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            // CHECK NPC COLLISION
            gp.cChecker.checkNPC(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;

                        break;

                }
            }

            gp.eHandler.checkEvent();


            // Update is executed 60 times per second
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

//    public void pickUpObject(int i){
//        if(i != 999) {
//            String objectName = gp.obj[i].name;
//            if (gp.keyH.enterPressed == true){
//                gp.gameState = gp.dialogueState;// <-- asignación
//                gp.cChecker.speak();
//            }
//            gp.keyH.enterPressed = false;
//
//            if (objectName.equals("accesscard")) {
//                //gp.gameState = gp.dialogueState;// <-- asignación
//                //gp.cChecker.speak();
//                gp.ui.currentDialogue = "Has encontrado una tarjeta!";
//                gp.obj[i] = null;                         // quita el objeto del mapa
//                hasKey++;                                 // si aplica
//                System.out.println("IKey: " + hasKey);
//                return;                                   // si no quieres ejecutar el switch después
//            }
//
//            switch(objectName){
//                case "accesscard":
//                    gp.obj[i] = null;
//                    hasKey++;
//                    gp.ui.currentDialogue = gp.obj[i].dialogues[0]; // mensaje de tarjeta
//                    gp.gameState = gp.dialogueState;
//                    System.out.println("1Key: " + hasKey);
//                    break;
//
//                case "door":
//                    if (hasKey > 0) {
//                        gp.obj[i] = null; // quitar la puerta
//                        hasKey--;
//                        gp.ui.currentDialogue = gp.obj[i].dialogues[1]; // mensaje de puerta abierta
//                    } else {
//                        gp.ui.currentDialogue = gp.obj[i].dialogues[0]; // mensaje de puerta cerrada
//                    }
//                    gp.gameState = gp.dialogueState;
//                    System.out.println("2Key: " + hasKey);
//                    break;
//                case "hallwaywarning":
//                    System.out.println("EXIT");
//                    // System.exit(1);
//                    gp.gameState = gp.gameOverState;
//                    break;
//
//
//            }
//        }
//
//    }

    public void pickUpObject(int i) {

        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "accesscard":
                    hasKey++;
                    gp.ui.currentDialogue = "Has encontrado una tarjeta!";
                    gp.obj[i] = null; // eliminar la tarjeta del mapa
                    System.out.println("1Key: " + hasKey);
                    gp.gameState = gp.dialogueState;
                    break;

                case "door":
                    if (hasKey > 0) {
                        hasKey--;
                        gp.obj[i] = null; // eliminar la puerta del mapa
                        gp.ui.currentDialogue = "Usaste la tarjeta y abriste \nla puerta!";
                        System.out.println("2Key: " + hasKey);
                    } else {
                        gp.ui.currentDialogue = "La puerta está \ncerrada con llave.";
                    }
                    gp.gameState = gp.dialogueState;
                    break;

                case "chest":
                    gp.ui.currentDialogue = "Encontraste un cofre!";
                    gp.gameState = gp.dialogueState;
                    break;

                case "hallwaywarning":
                    gp.ui.currentDialogue = "¡Has sido detectado!";  // o el mensaje que quieras
                    gp.gameState = gp.gameOverState;                 // cambia al estado de game over
                    System.out.println("Has perdido: tocaste el hallway warning.");
                    break;

            }

            gp.keyH.enterPressed = false;
        }
    }

    public void draw(Graphics2D g2d){
//        g2d.setColor(Color.white);
//
//        g2d.fillRect(x, y, gp.getTileSize() , gp.getTileSize());

        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = bup1;
                } else {
                    image = bup2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = bdown1;
                } else {
                    image = bdown2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = bleft1;
                } else {
                    image = bleft2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = bright1;
                } else {
                    image = bright2;
                }
                break;
        }

        g2d.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
    }
}
