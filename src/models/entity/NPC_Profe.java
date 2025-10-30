package models.entity;

import models.GamePanel;

import java.awt.*;

public class NPC_Profe extends Entity {
    public NPC_Profe(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 2; // Más rápido que el jugador 
        
        // área de colisión
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }

    public void getImage(){
        up1 = setup("/npc/oldman_up_1.png");
        up2 = setup("/npc/oldman_up_2.png");
        down1 = setup("/npc/oldman_down_1.png");
        down2 = setup("/npc/oldman_down_2.png");
        left1 = setup("/npc/oldman_left_1.png");
        left2 = setup("/npc/oldman_left_2.png");
        right1 = setup("/npc/oldman_right_1.png");
        right2 = setup("/npc/oldman_right_2.png");
    }
    
    public void update() {
        // Pathfinding hacia el jugador
        int playerWorldX = gp.player.worldX;
        int playerWorldY = gp.player.worldY;
        
        // Calcular distancia al jugador
        int deltaX = playerWorldX - worldX;
        int deltaY = playerWorldY - worldY;
        
        // Determinar dirección hacia el jugador con pathfinding 
        String targetDirection = getDirectionToPlayer(deltaX, deltaY);
        
        // Intentar moverse en la dirección objetivo
        direction = targetDirection;
        collisionOn = false;
        gp.cChecker.checkTile(this);
        
        // Si hay colisión, intentar direcciones alternativas
        if(collisionOn) {
            String[] alternativeDirections = getAlternativeDirections(targetDirection);
            for(String altDir : alternativeDirections) {
                direction = altDir;
                collisionOn = false;
                gp.cChecker.checkTile(this);
                if(!collisionOn) {
                    break; // Encontramos una dirección válida
                }
            }
        }
        
        // Si no hay colisión, moverse
        if(!collisionOn) {
            switch(direction) {
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
        
        // Actualizar sprite
        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            } else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    
    private String getDirectionToPlayer(int deltaX, int deltaY) {
        // Priorizar la dirección con mayor distancia
        if(Math.abs(deltaX) > Math.abs(deltaY)) {
            return deltaX > 0 ? "right" : "left";
        } else {
            return deltaY > 0 ? "down" : "up";
        }
    }
    
    private String[] getAlternativeDirections(String primaryDirection) {
        // Retornar direcciones alternativas basadas en la dirección primaria
        switch(primaryDirection) {
            case "up":
                return new String[]{"left", "right", "down"};
            case "down":
                return new String[]{"left", "right", "up"};
            case "left":
                return new String[]{"up", "down", "right"};
            case "right":
                return new String[]{"up", "down", "left"};
            default:
                return new String[]{"up", "down", "left", "right"};
        }
    }
    
    @Override
    public void speak() {
        super.speak();
        gp.gameState = gp.dialogueState;
        
        // Asegurar que el diálogo no esté vacío
        if(dialogues[dialogueIndex] != null && !dialogues[dialogueIndex].isEmpty()) {
            gp.ui.currentDialogue = dialogues[dialogueIndex];
        } else {
            gp.ui.currentDialogue = "¡Hola! Soy el profesor.";
        }
        
        dialogueIndex++;
        
        if(dialogueIndex >= dialogues.length) {
            dialogueIndex = 0;
        }
    }
}
