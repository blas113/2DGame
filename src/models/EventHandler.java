package models;

import java.awt.*;

public class EventHandler {

    private GamePanel gp;
    private Rectangle eventRect;
    private int eventRectDefaultX;
    private int eventRectDefaultY;

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
            teleport(2,3, GamePanel.dialogueState, 2);
        }

        if(hit(28, 3, "right") == true){
            teleport(4, 19, GamePanel.dialogueState, 3);
        }

        if(hit(6, 2, "up") == true) {
            disableCamera(GamePanel.dialogueState, 1);
        }
        if(hit(15, 8, "down") == true) {
            disableCamera(GamePanel.dialogueState, 2);
        }
        if(hit(23, 2, "up") == true) {
            disableCamera(GamePanel.dialogueState, 3);
        }
    }

    public boolean hit(int eventCol,int eventRow, String reqDirection) {
        boolean hit = false;
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidArea().x;
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidArea().y;
        eventRect.x = eventCol * gp.getTileSize() + eventRect.x;
        eventRect.y = eventRow * gp.getTileSize() + eventRect.y;
        if(gp.getPlayer().getSolidArea().intersects (eventRect)) {
            if(gp.getPlayer().getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void teleport(int coordsX, int coordsY, int gameState, int level){
        gp.setGameState(gameState);
        gp.getGameUI().setCurrentDialogue("Felicidades!\nPasaste al nivel  "+level);
        // Dar hints segun nivel
        gp.getPlayer().setWorldX(gp.getTileSize() * coordsX);
        gp.getPlayer().setWorldY(gp.getTileSize() * coordsY);
    }

    public void disableCamera(int gameState, int cameraNumber) {
        if (gp.getKeyHandler().isEnterPressed() == true){
            gp.setGameState(gameState);
            gp.getGameUI().setCurrentDialogue("Desactivaste la camara!");

            // Logica
            if (cameraNumber == 1) {
                for (int i = 4;i <= 36;i++){
                    gp.getObjects()[i]=null;
                }
            }
            if (cameraNumber == 2) {
                for (int i = 198;i <=226;i++){
                    gp.getObjects()[i]=null;
                }
            }
            if (cameraNumber == 3) {
                for (int i = 227;i <=253;i++){
                    gp.getObjects()[i]=null;
                }
            }

        }
    }
}
