package models;

import models.entity.NPC_Police;
import models.entity.NPC_Profe;
import models.object.*;

import java.util.function.BiConsumer;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject() {

        // ACCESS CARD
        gp.obj[0] = new OBJ_AccessCard();
        gp.obj[0].worldX = 11 * gp.tileSize;
        gp.obj[0].worldY = 46 * gp.tileSize;

        // ROOM Door
        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 15 * gp.tileSize;
        gp.obj[1].worldY = 36 * gp.tileSize;

        // HALLWAY ENTRY DOOR
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 1 * gp.tileSize;
        gp.obj[2].worldY = 3 * gp.tileSize;

        // QR CODE
        gp.obj[40] = new OBJ_QrCode();
        gp.obj[40].worldX = 10 * gp.tileSize;
        gp.obj[40].worldY = 27 * gp.tileSize;




        // CAMERA HALLWAY 1
        gp.obj[3] = new OBJ_Camera();
        gp.obj[3].worldX = 7 * gp.tileSize;
        gp.obj[3].worldY = 1 * gp.tileSize;

        // DETECTION HALLWAY 1
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
                gp.obj[4 + i].worldX = (i - 17) * gp.tileSize;
                gp.obj[4 + i].worldY = (5) * gp.tileSize;
            }
        }

        // CAMERA HALLWAY 2
        gp.obj[254] = new OBJ_Camera();
        gp.obj[254].worldX = 16 * gp.tileSize ;
        gp.obj[254].worldY = 9 * gp.tileSize;



        // DETECTION HALLWAY 2
        int aux = 0;
        int aux2  = 0;
        for(int i = 0; i <= 28; i++) {
            if (i==6 || i== 14 || i == 22 ){
                aux = 0;
            }
            if (i<=6) {
                gp.obj[198+i] = new OBJ_HallwayWarning();
                gp.obj[198+i].worldX = (16+aux) * gp.tileSize;
                gp.obj[198+i].worldY = (8) * gp.tileSize;
                aux++;
            }
            if (i>=6 && i<=14) {
                gp.obj[198+i] = new OBJ_HallwayWarning();
                gp.obj[198+i].worldX = (13+aux) * gp.tileSize;
                gp.obj[198+i].worldY = (7) * gp.tileSize;
                aux++;
            }
            if (i>=14 && i<=22) {
                gp.obj[198+i] = new OBJ_HallwayWarning();
                gp.obj[198+i].worldX = (13+aux) * gp.tileSize;
                gp.obj[198+i].worldY = (6) * gp.tileSize;
                aux++;
            }
            if (i>=22 && i<=27) {
                gp.obj[198+i] = new OBJ_HallwayWarning();
                gp.obj[198+i].worldX = (16+aux2) * gp.tileSize;
                gp.obj[198+i].worldY = (5) * gp.tileSize;
                aux2++;
            }

        }

        // CAMERA HALLWAY 3
        gp.obj[255] = new OBJ_Camera();
        gp.obj[255].worldX = 24 * gp.tileSize ;
        gp.obj[255].worldY = 1 * gp.tileSize;

        // DETECTION HALLWAY 3
        aux = 0;
        aux2  = 0;
        for(int i = 0; i <= 26; i++) {
            if (i==4 || i== 12 || i == 20 ){
                aux = 0;
            }
            if (i<=4) {
                gp.obj[227+i] = new OBJ_HallwayWarning();
                gp.obj[227+i].worldX = (24+aux) * gp.tileSize;
                gp.obj[227+i].worldY = (2) * gp.tileSize;
                aux++;
            }
            if (i>=4 && i<=12) {
                gp.obj[227+i] = new OBJ_HallwayWarning();
                gp.obj[227+i].worldX = (19+aux) * gp.tileSize;
                gp.obj[227+i].worldY = (3) * gp.tileSize;
                aux++;
            }
            if (i>=12 && i<=20) {
                gp.obj[227+i] = new OBJ_HallwayWarning();
                gp.obj[227+i].worldX = (19+aux) * gp.tileSize;
                gp.obj[227+i].worldY = (4) * gp.tileSize;
                aux++;
            }
            if (i>=20 && i<=25) {
                gp.obj[227+i] = new OBJ_HallwayWarning();
                gp.obj[227+i].worldX = (22+aux2) * gp.tileSize;
                gp.obj[227+i].worldY = (5) * gp.tileSize;
                aux2++;
            }

        }

        // HALLWAY EXIT DOOR
        gp.obj[256] = new OBJ_Door();
        gp.obj[256].worldX = 28 * gp.tileSize;
        gp.obj[256].worldY = 3 * gp.tileSize;

        // ACCESS CARD HALLWAY
        gp.obj[257] = new OBJ_AccessCard();
        gp.obj[257].worldX = 18 * gp.tileSize ;
        gp.obj[257].worldY = 7 * gp.tileSize;


//        // TURNSTILE 1
//        gp.obj[] = new OBJ_Turnstile();
//        gp.obj[258].worldX = 26 * gp.tileSize ;
//        gp.obj[258].worldY = 18 * gp.tileSize;
//
//
//        // TURNSTILE 2
//        gp.obj[259] = new OBJ_Turnstile();
//        gp.obj[259].worldX = 26 * gp.tileSize ;
//        gp.obj[259].worldY = 21 * gp.tileSize;

        // TURNSTILE 3
        gp.obj[260] = new OBJ_Turnstile();
        gp.obj[260].worldX = 26 * gp.tileSize ;
        gp.obj[260].worldY = 24 * gp.tileSize;


        // parámetros del mapa
        int rowsPerCol = 12;
        int columns = 13;

        // creación de la máscara (true = pintar / rojo, false = camino)
        boolean[][] paint = new boolean[columns][rowsPerCol];
        for (int c = 0; c < columns; c++)
            for (int r = 0; r < rowsPerCol; r++)
                paint[c][r] = true;

        // punto inicial: abajo a la izquierda del área roja
        int colStart = 0;                      // columna inicial (izquierda)
        int rowStart = rowsPerCol - 1;         // fila inferior (abajo)

        // lista de movimientos (dx, dy) — dx positivo = derecha, dy negativo = subir
        int[][] moves = new int[][] {
                {3, 0},   // derecha 3
                {0, -2},  // subir 2
                {1, 0},   // derecha 1
                {0, -1},  // subir 1
                {4, 0},   // derecha 4
                {0, -3},  // subir 3
                {1, 0},    // derecha 1 final
                {1,0},
                {1, 0},
                {1, 0},
                {1, 0}
        };

        // función auxiliar para marcar UNA sola celda como camino (ancho = 1)
        BiConsumer<Integer,Integer> markPathCell = (c, r) -> {
            if (c >= 0 && c < columns && r >= 0 && r < rowsPerCol) {
                paint[c][r] = false; // no pintar aquí (es camino de 1 bloque)
            }
        };

        // marcar la celda inicial
        int curC = colStart;
        int curR = rowStart;
        markPathCell.accept(curC, curR);

        // aplicar movimientos paso a paso (marca cada celda en el camino)
        for (int[] mv : moves) {
            int dx = mv[0];
            int dy = mv[1];

            // mover horizontal paso a paso
            int stepX = Integer.signum(dx);
            for (int s = 0; s < Math.abs(dx); s++) {
                curC += stepX;
                markPathCell.accept(curC, curR);
            }

            // mover vertical paso a paso (dy negativo = subir)
            int stepY = Integer.signum(dy);
            for (int s = 0; s < Math.abs(dy); s++) {
                curR += stepY;
                markPathCell.accept(curC, curR);
            }
        }

        // ahora el bucle que coloca los objetos respeta la máscara:
        int startIndex = 42;
        int baseWorldX = 12;
        int baseWorldY = 16;
        int total = rowsPerCol * columns;
        for (int i = 0; i < total; i++) {
            int col = i / rowsPerCol;
            int row = i % rowsPerCol;

            if (!paint[col][row]) {
                continue; // este es el camino: NO crear la alerta
            }

            gp.obj[startIndex + i] = new OBJ_ExitWarning();
            gp.obj[startIndex + i].worldX = (baseWorldX + col) * gp.tileSize;
            gp.obj[startIndex + i].worldY = (baseWorldY + row) * gp.tileSize;
        }

        // DETECTION EXIT DEBUG
        //        gp.obj[42] = new OBJ_ExitWarning();
        //        gp.obj[42].worldX = 12 * gp.tileSize;
        //        gp.obj[42].worldY = (16) * gp.tileSize;


    }

    public void setNPC(){
        gp.npc[0] = new NPC_Profe(gp);
        gp.npc[0].worldX = 10 * gp.tileSize;  // 10 40
        gp.npc[0].worldY = 40 * gp.tileSize;

        gp.npc[1] = new NPC_Police(gp);
        gp.npc[1].worldX = 2 * gp.tileSize;
        gp.npc[1].worldY = 16 * gp.tileSize;
    }


}
