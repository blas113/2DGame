package models;

import models.entity.Entity;

public class CollisionChecker {
    private GamePanel gp;

    public CollisionChecker (GamePanel gp){
        this.gp = gp;
    }

    public void checkTile (Entity entity){

        // Coordinates of
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()){
            case "up":
                entityTopRow = ((entityTopWorldY - entity.getSpeed()) / gp.getTileSize());
                tileNum1 = gp.getTileManager().mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().mapTileNum[entityRightCol][entityTopRow];
                if(gp.getTileManager().tile[tileNum1].collision == true || gp.getTileManager().tile[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;
            case  "down":
                entityBottomRow = ((entityBottomWorldY + entity.getSpeed()) / gp.getTileSize());
                tileNum1 = gp.getTileManager().mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().mapTileNum[entityRightCol][entityBottomRow];
                if(gp.getTileManager().tile[tileNum1].collision == true || gp.getTileManager().tile[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = ((entityLeftWorldX - entity.getSpeed()) / gp.getTileSize());
                tileNum1 = gp.getTileManager().mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.getTileManager().tile[tileNum1].collision == true || gp.getTileManager().tile[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = ((entityRightWorldX + entity.getSpeed()) / gp.getTileSize());
                tileNum1 = gp.getTileManager().mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().mapTileNum[entityRightCol][entityBottomRow];
                if(gp.getTileManager().tile[tileNum1].collision == true || gp.getTileManager().tile[tileNum2].collision == true){
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for (int i = 0; i < gp.getObjects().length; i++){
            if (gp.getObjects()[i] != null){
                // Get entitys solid area position
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // Get the objtect solid area position
                gp.getObjects()[i].getSolidArea().x = gp.getObjects()[i].getWorldX() + gp.getObjects()[i].getSolidArea().x;
                gp.getObjects()[i].getSolidArea().y = gp.getObjects()[i].getWorldY() + gp.getObjects()[i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObjects()[i].getSolidArea())) {
                            if (gp.getObjects()[i].isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if(player == true){
                                index = i;
                            }

                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObjects()[i].getSolidArea())) {
                            if (gp.getObjects()[i].isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObjects()[i].getSolidArea())) {
                            if (gp.getObjects()[i].isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObjects()[i].getSolidArea())) {
                            if (gp.getObjects()[i].isCollision()){
                                entity.setCollisionOn(true);
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;

                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gp.getObjects()[i].getSolidArea().x = gp.getObjects()[i].getSolidAreaDefaultX();
                gp.getObjects()[i].getSolidArea().y = gp.getObjects()[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                // Get entity's solid area position
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;

                // Get the target's solid area position
                target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x;
                target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }

                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
                target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
            }
        }

        return index;
    }

    public void checkNPC(Entity entity) {
        int npcIndex = checkEntity(entity, gp.getNPCs());

        if (npcIndex != 999) {
            if (gp.getKeyHandler().isEnterPressed()) {
                gp.setGameState(GamePanel.dialogueState);
                gp.getNPCs()[npcIndex].speak();
            }
        }
    }

    public void checkPlayerNPCContact() {
        // Verificar si algún NPC está tocando al jugador
        for (int i = 0; i < gp.getNPCs().length; i++) {
            if (gp.getNPCs()[i] != null) {
                // Calcular distancia entre NPC y jugador
                int deltaX = Math.abs(gp.getPlayer().getWorldX() - gp.getNPCs()[i].getWorldX());
                int deltaY = Math.abs(gp.getPlayer().getWorldY() - gp.getNPCs()[i].getWorldY());

                // Si están muy cerca (dentro de un tile), game over
                if (deltaX < gp.getTileSize() && deltaY < gp.getTileSize()) {
                    gp.setGameState(GamePanel.gameOverState);
                }
            }
        }
    }
}
