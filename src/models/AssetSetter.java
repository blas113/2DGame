package models;

import models.entity.NPC_Police;
import models.entity.NPC_Profe;
import models.object.*;

import java.util.function.BiConsumer;

public class AssetSetter {
    private GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject() {

        // ACCESS CARD
        gp.getObjects()[0] = new OBJ_AccessCard();
        gp.getObjects()[0].setWorldX(11 * gp.getTileSize());
        gp.getObjects()[0].setWorldY(46 * gp.getTileSize());

        // ROOM Door
        gp.getObjects()[1] = new OBJ_Door();
        gp.getObjects()[1].setWorldX(15 * gp.getTileSize());
        gp.getObjects()[1].setWorldY(36 * gp.getTileSize());

        // HALLWAY ENTRY DOOR
        gp.getObjects()[2] = new OBJ_Door();
        gp.getObjects()[2].setWorldX(1 * gp.getTileSize());
        gp.getObjects()[2].setWorldY(3 * gp.getTileSize());

        // QR CODE
        gp.getObjects()[40] = new OBJ_QrCode();
        gp.getObjects()[40].setWorldX(10 * gp.getTileSize());
        gp.getObjects()[40].setWorldY(27 * gp.getTileSize());




        // CAMERA HALLWAY 1
        gp.getObjects()[3] = new OBJ_Camera();
        gp.getObjects()[3].setWorldX(7 * gp.getTileSize());
        gp.getObjects()[3].setWorldY(1 * gp.getTileSize());

        // DETECTION HALLWAY 1
        for(int i = 0; i <= 32; i++) {
            gp.getObjects()[4+i] = new OBJ_HallwayWarning();
            if (i<5) {
                gp.getObjects()[4+i].setWorldX((7+i) * gp.getTileSize());
                gp.getObjects()[4+i].setWorldY((2) * gp.getTileSize());
            } else if (i<13) {
                gp.getObjects()[4 + i].setWorldX((i) * gp.getTileSize());
                gp.getObjects()[4 + i].setWorldY((3) * gp.getTileSize());
            } else if (i<22) {
                gp.getObjects()[4 + i].setWorldX((i-8) * gp.getTileSize());
                gp.getObjects()[4 + i].setWorldY((4) * gp.getTileSize());
            } else {
                gp.getObjects()[4 + i].setWorldX((i - 17) * gp.getTileSize());
                gp.getObjects()[4 + i].setWorldY((5) * gp.getTileSize());
            }
        }

        // CAMERA HALLWAY 2
        gp.getObjects()[254] = new OBJ_Camera();
        gp.getObjects()[254].setWorldX(16 * gp.getTileSize());
        gp.getObjects()[254].setWorldY(9 * gp.getTileSize());



        // DETECTION HALLWAY 2
        int aux = 0;
        int aux2  = 0;
        for(int i = 0; i <= 28; i++) {
            if (i==6 || i== 14 || i == 22 ){
                aux = 0;
            }
            if (i<=6) {
                gp.getObjects()[198+i] = new OBJ_HallwayWarning();
                gp.getObjects()[198+i].setWorldX((16+aux) * gp.getTileSize());
                gp.getObjects()[198+i].setWorldY((8) * gp.getTileSize());
                aux++;
            }
            if (i>=6 && i<=14) {
                gp.getObjects()[198+i] = new OBJ_HallwayWarning();
                gp.getObjects()[198+i].setWorldX((13+aux) * gp.getTileSize());
                gp.getObjects()[198+i].setWorldY((7) * gp.getTileSize());
                aux++;
            }
            if (i>=14 && i<=22) {
                gp.getObjects()[198+i] = new OBJ_HallwayWarning();
                gp.getObjects()[198+i].setWorldX((13+aux) * gp.getTileSize());
                gp.getObjects()[198+i].setWorldY((6) * gp.getTileSize());
                aux++;
            }
            if (i>=22 && i<=27) {
                gp.getObjects()[198+i] = new OBJ_HallwayWarning();
                gp.getObjects()[198+i].setWorldX((16+aux2) * gp.getTileSize());
                gp.getObjects()[198+i].setWorldY((5) * gp.getTileSize());
                aux2++;
            }

        }

        // CAMERA HALLWAY 3
        gp.getObjects()[255] = new OBJ_Camera();
        gp.getObjects()[255].setWorldX(24 * gp.getTileSize());
        gp.getObjects()[255].setWorldY(1 * gp.getTileSize());

        // DETECTION HALLWAY 3
        aux = 0;
        aux2  = 0;
        for(int i = 0; i <= 26; i++) {
            if (i==4 || i== 12 || i == 20 ){
                aux = 0;
            }
            if (i<=4) {
                gp.getObjects()[227+i] = new OBJ_HallwayWarning();
                gp.getObjects()[227+i].setWorldX((24+aux) * gp.getTileSize());
                gp.getObjects()[227+i].setWorldY((2) * gp.getTileSize());
                aux++;
            }
            if (i>=4 && i<=12) {
                gp.getObjects()[227+i] = new OBJ_HallwayWarning();
                gp.getObjects()[227+i].setWorldX((19+aux) * gp.getTileSize());
                gp.getObjects()[227+i].setWorldY((3) * gp.getTileSize());
                aux++;
            }
            if (i>=12 && i<=20) {
                gp.getObjects()[227+i] = new OBJ_HallwayWarning();
                gp.getObjects()[227+i].setWorldX((19+aux) * gp.getTileSize());
                gp.getObjects()[227+i].setWorldY((4) * gp.getTileSize());
                aux++;
            }
            if (i>=20 && i<=25) {
                gp.getObjects()[227+i] = new OBJ_HallwayWarning();
                gp.getObjects()[227+i].setWorldX((22+aux2) * gp.getTileSize());
                gp.getObjects()[227+i].setWorldY((5) * gp.getTileSize());
                aux2++;
            }

        }

        // HALLWAY EXIT DOOR
        gp.getObjects()[256] = new OBJ_Door();
        gp.getObjects()[256].setWorldX(28 * gp.getTileSize());
        gp.getObjects()[256].setWorldY(3 * gp.getTileSize());

        // ACCESS CARD HALLWAY
        gp.getObjects()[257] = new OBJ_AccessCard();
        gp.getObjects()[257].setWorldX(18 * gp.getTileSize());
        gp.getObjects()[257].setWorldY(7 * gp.getTileSize());


//        // TURNSTILE 1
//        gp.getObjects()[] = new OBJ_Turnstile();
//        gp.getObjects()[258].setWorldX(26 * gp.getTileSize());
//        gp.getObjects()[258].setWorldY(18 * gp.getTileSize());
//
//
//        // TURNSTILE 2
//        gp.getObjects()[259] = new OBJ_Turnstile();
//        gp.getObjects()[259].setWorldX(26 * gp.getTileSize());
//        gp.getObjects()[259].setWorldY(21 * gp.getTileSize());

        // TURNSTILE 3
        gp.getObjects()[260] = new OBJ_Turnstile();
        gp.getObjects()[260].setWorldX(26 * gp.getTileSize());
        gp.getObjects()[260].setWorldY(24 * gp.getTileSize());


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

            gp.getObjects()[startIndex + i] = new OBJ_ExitWarning();
            gp.getObjects()[startIndex + i].setWorldX((baseWorldX + col) * gp.getTileSize());
            gp.getObjects()[startIndex + i].setWorldY((baseWorldY + row) * gp.getTileSize());
        }

        // DETECTION EXIT DEBUG
        //        gp.obj[42] = new OBJ_ExitWarning();
        //        gp.obj[42].worldX = 12 * gp.tileSize;
        //        gp.obj[42].worldY = (16) * gp.tileSize;


    }

    public void setNPC(){
        gp.getNPCs()[0] = new NPC_Profe(gp);
        gp.getNPCs()[0].setWorldX(10 * gp.getTileSize());  // 10 40
        gp.getNPCs()[0].setWorldY(40 * gp.getTileSize());

        gp.getNPCs()[1] = new NPC_Police(gp);
        gp.getNPCs()[1].setWorldX(2 * gp.getTileSize());
        gp.getNPCs()[1].setWorldY(16 * gp.getTileSize());
    }


}
