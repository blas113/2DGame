package models;

import models.entity.NPC_Profe;
import models.object.OBJ_AccessCard;
import models.object.OBJ_Camera;
import models.object.OBJ_Door;
import models.object.OBJ_HallwayWarning;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject() {
//        gp.obj[0] = new OBJ_Key();
//        gp.obj[0].worldX = 23 * gp.tileSize;
//        gp.obj[0].worldY = 7 * gp.tileSize;

        // ACCESS
        gp.obj[0] = new OBJ_AccessCard();
        gp.obj[0].worldX = 11 * gp.tileSize;
        gp.obj[0].worldY = 46 * gp.tileSize;

        // ROOM Door
        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 15 * gp.tileSize;
        gp.obj[1].worldY = 36 * gp.tileSize;

        // HALLWAY DOOR
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 1 * gp.tileSize;
        gp.obj[2].worldY = 3 * gp.tileSize;

        // CAMERA
        gp.obj[3] = new OBJ_Camera();
        gp.obj[3].worldX = 7 * gp.tileSize;
        gp.obj[3].worldY = 1 * gp.tileSize;

        // DETECTION
        for(int i = 0; i <= 32; i++) {
            gp.obj[4+i] = new OBJ_HallwayWarning();
            if (i<5) {
                gp.obj[4+i].worldX = (7+i) * gp.tileSize;
                gp.obj[4+i].worldY = (2) * gp.tileSize;
            } else if (i<13) {
                gp.obj[4 + i].worldX = (i) * gp.tileSize;
                gp.obj[4 + i].worldY = (3) * gp.tileSize;
            } else if (i<22) {
                gp.obj[4 + i].worldX = (i-8) * gp.tileSize;
                gp.obj[4 + i].worldY = (4) * gp.tileSize;
            } else {
                gp.obj[4 + i].worldX = (i-17) * gp.tileSize;
                gp.obj[4 + i].worldY = (5) * gp.tileSize;
            }


        }
        //gp.obj[4].worldX = 7 * gp.tileSize;
        //gp.obj[4].worldY = 2 * gp.tileSize;


    }


    public void setNPC(){
        gp.npc[0] = new NPC_Profe(gp);
        gp.npc[0].worldX = 10 * gp.tileSize;
        gp.npc[0].worldY = 40 * gp.tileSize;
    }
}
