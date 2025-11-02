package models;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x=23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultY = eventRect.y;
        eventRectDefaultX = eventRect.x;

    }

    public void checkEvent(){

        if(hit(15,36,"right") == true){
            teleport(2,3, gp.dialogueState, 2);
        }

        if(hit(28, 3, "right") == true){
            teleport(4, 19, gp.dialogueState, 3);
        }

        if(hit(6, 2, "up") == true) {
            disableCamera(gp.dialogueState, 1);
        }
        if(hit(15, 8, "down") == true) {
            disableCamera(gp.dialogueState, 2);
        }
        if(hit(23, 2, "up") == true) {
            disableCamera(gp.dialogueState, 3);
        }
    }

    public boolean hit(int eventCol,int eventRow, String reqDirection) {
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;
        if(gp.player.solidArea.intersects (eventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void teleport(int coordsX, int coordsY, int gameState, int level){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Felicidades!\nPasaste al nivel  "+level;
        // Dar hints segun nivel
        gp.player.worldX = gp.tileSize*coordsX;
        gp.player.worldY = gp.tileSize*coordsY;
    }

    public void disableCamera(int gameState, int cameraNumber) {
        if (gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Desactivaste la camara!";

            // Logica
            if (cameraNumber == 1) {
                for (int i = 4;i <= 36;i++){
                    gp.obj[i]=null;
                }
            }
            if (cameraNumber == 2) {
                for (int i = 198;i <=226;i++){
                    gp.obj[i]=null;
                }
            }
            if (cameraNumber == 3) {
                for (int i = 227;i <=253;i++){
                    gp.obj[i]=null;
                }
            }

        }
    }
}
