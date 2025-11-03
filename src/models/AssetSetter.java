package models;

import models.factory.EntityFactory;
import models.factory.ObjectFactory;
import models.service.FileWriterService;
import exceptions.GameConfigurationException;

import java.io.IOException;
import java.util.function.BiConsumer;

/**
 * Clase responsable de configurar los objetos y NPCs del juego.
 * Aplica el principio GRASP de Creator.
 * Utiliza el patrón Factory para la creación de objetos y entidades.
 */
public class AssetSetter {
    private GamePanel gp;
    private EntityFactory entityFactory;
    private ObjectFactory objectFactory;
    private FileWriterService fileWriterService;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
        this.entityFactory = new EntityFactory(gp);
        this.objectFactory = new ObjectFactory();
        this.fileWriterService = new FileWriterService();
        
        // Asegurar que los directorios existan
        try {
            fileWriterService.ensureDirectoriesExist();
            fileWriterService.writeLog("AssetSetter inicializado", "INFO");
        } catch (IOException e) {
            System.err.println("Error al inicializar FileWriterService: " + e.getMessage());
        }
    }

    /**
     * Configura los objetos del juego usando ObjectFactory.
     * Registra la creación en archivos de log.
     */
    public void setObject() {
        try {
            // ACCESS CARD
            gp.getObjects()[0] = objectFactory.createObject(ObjectFactory.ACCESS_CARD);
            gp.getObjects()[0].setWorldX(11 * gp.getTileSize());
            gp.getObjects()[0].setWorldY(46 * gp.getTileSize());
            logObjectCreation(0, ObjectFactory.ACCESS_CARD, 11, 46);

            // ROOM Door
            gp.getObjects()[1] = objectFactory.createObject(ObjectFactory.DOOR);
            gp.getObjects()[1].setWorldX(15 * gp.getTileSize());
            gp.getObjects()[1].setWorldY(36 * gp.getTileSize());
            logObjectCreation(1, ObjectFactory.DOOR, 15, 36);

            // HALLWAY ENTRY DOOR
            gp.getObjects()[2] = objectFactory.createObject(ObjectFactory.DOOR);
            gp.getObjects()[2].setWorldX(1 * gp.getTileSize());
            gp.getObjects()[2].setWorldY(3 * gp.getTileSize());
            logObjectCreation(2, ObjectFactory.DOOR, 1, 3);

            // QR CODE
            gp.getObjects()[40] = objectFactory.createObject(ObjectFactory.QR_CODE);
            gp.getObjects()[40].setWorldX(10 * gp.getTileSize());
            gp.getObjects()[40].setWorldY(27 * gp.getTileSize());
            logObjectCreation(40, ObjectFactory.QR_CODE, 10, 27);




            // CAMERA HALLWAY 1
            gp.getObjects()[3] = objectFactory.createObject(ObjectFactory.CAMERA);
            gp.getObjects()[3].setWorldX(7 * gp.getTileSize());
            gp.getObjects()[3].setWorldY(1 * gp.getTileSize());
            logObjectCreation(3, ObjectFactory.CAMERA, 7, 1);

            // DETECTION HALLWAY 1
            for(int i = 0; i <= 32; i++) {
                gp.getObjects()[4+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
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
            gp.getObjects()[254] = objectFactory.createObject(ObjectFactory.CAMERA);
            gp.getObjects()[254].setWorldX(16 * gp.getTileSize());
            gp.getObjects()[254].setWorldY(9 * gp.getTileSize());
            logObjectCreation(254, ObjectFactory.CAMERA, 16, 9);



        // DETECTION HALLWAY 2
        int aux = 0;
        int aux2  = 0;
        for(int i = 0; i <= 28; i++) {
            if (i==6 || i== 14 || i == 22 ){
                aux = 0;
            }
                if (i<=6) {
                    gp.getObjects()[198+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
                gp.getObjects()[198+i].setWorldX((16+aux) * gp.getTileSize());
                gp.getObjects()[198+i].setWorldY((8) * gp.getTileSize());
                aux++;
            }
                if (i>=6 && i<=14) {
                    gp.getObjects()[198+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
                gp.getObjects()[198+i].setWorldX((13+aux) * gp.getTileSize());
                gp.getObjects()[198+i].setWorldY((7) * gp.getTileSize());
                aux++;
            }
                if (i>=14 && i<=22) {
                    gp.getObjects()[198+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
                gp.getObjects()[198+i].setWorldX((13+aux) * gp.getTileSize());
                gp.getObjects()[198+i].setWorldY((6) * gp.getTileSize());
                aux++;
            }
                if (i>=22 && i<=27) {
                    gp.getObjects()[198+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
                gp.getObjects()[198+i].setWorldX((16+aux2) * gp.getTileSize());
                gp.getObjects()[198+i].setWorldY((5) * gp.getTileSize());
                aux2++;
            }

        }

            // CAMERA HALLWAY 3
            gp.getObjects()[255] = objectFactory.createObject(ObjectFactory.CAMERA);
            gp.getObjects()[255].setWorldX(24 * gp.getTileSize());
            gp.getObjects()[255].setWorldY(1 * gp.getTileSize());
            logObjectCreation(255, ObjectFactory.CAMERA, 24, 1);

            // CAMERA HALLWAY 3
            gp.getObjects()[255] = objectFactory.createObject(ObjectFactory.CAMERA);
            gp.getObjects()[255].setWorldX(24 * gp.getTileSize());
            gp.getObjects()[255].setWorldY(1 * gp.getTileSize());
            logObjectCreation(255, ObjectFactory.CAMERA, 24, 1);

            // DETECTION HALLWAY 3
            aux = 0;
            aux2  = 0;
            for(int i = 0; i <= 26; i++) {
                if (i==4 || i== 12 || i == 20 ){
                    aux = 0;
                }
                if (i<=4) {
                    gp.getObjects()[227+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
                gp.getObjects()[227+i].setWorldX((24+aux) * gp.getTileSize());
                gp.getObjects()[227+i].setWorldY((2) * gp.getTileSize());
                aux++;
            }
                if (i>=4 && i<=12) {
                    gp.getObjects()[227+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
                gp.getObjects()[227+i].setWorldX((19+aux) * gp.getTileSize());
                gp.getObjects()[227+i].setWorldY((3) * gp.getTileSize());
                aux++;
            }
                if (i>=12 && i<=20) {
                    gp.getObjects()[227+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
                gp.getObjects()[227+i].setWorldX((19+aux) * gp.getTileSize());
                gp.getObjects()[227+i].setWorldY((4) * gp.getTileSize());
                aux++;
            }
                if (i>=20 && i<=25) {
                    gp.getObjects()[227+i] = objectFactory.createObject(ObjectFactory.HALLWAY_WARNING);
                gp.getObjects()[227+i].setWorldX((22+aux2) * gp.getTileSize());
                gp.getObjects()[227+i].setWorldY((5) * gp.getTileSize());
                aux2++;
            }

        }

            // HALLWAY EXIT DOOR
            gp.getObjects()[256] = objectFactory.createObject(ObjectFactory.DOOR);
            gp.getObjects()[256].setWorldX(28 * gp.getTileSize());
            gp.getObjects()[256].setWorldY(3 * gp.getTileSize());
            logObjectCreation(256, ObjectFactory.DOOR, 28, 3);

            // ACCESS CARD HALLWAY
            gp.getObjects()[257] = objectFactory.createObject(ObjectFactory.ACCESS_CARD);
            gp.getObjects()[257].setWorldX(18 * gp.getTileSize());
            gp.getObjects()[257].setWorldY(7 * gp.getTileSize());
            logObjectCreation(257, ObjectFactory.ACCESS_CARD, 18, 7);


            // CAMERA END 1
            gp.getObjects()[300] = objectFactory.createObject(ObjectFactory.CAMERA);
            gp.getObjects()[300].setWorldX(16 * gp.getTileSize());
            gp.getObjects()[300].setWorldY(28 * gp.getTileSize());
            logObjectCreation(300, ObjectFactory.CAMERA, 16, 28);

            // CAMERA END 2
            gp.getObjects()[301] = objectFactory.createObject(ObjectFactory.CAMERA);
            gp.getObjects()[301].setWorldX(24 * gp.getTileSize());
            gp.getObjects()[301].setWorldY(28 * gp.getTileSize());
            logObjectCreation(301, ObjectFactory.CAMERA, 24, 28);

            // CAMERA END 3
            gp.getObjects()[302] = objectFactory.createObject(ObjectFactory.CAMERA);
            gp.getObjects()[302].setWorldX(16 * gp.getTileSize());
            gp.getObjects()[302].setWorldY(15 * gp.getTileSize());
            logObjectCreation(302, ObjectFactory.CAMERA, 16, 15);

            // CAMERA END 4
            gp.getObjects()[303] = objectFactory.createObject(ObjectFactory.CAMERA);
            gp.getObjects()[303].setWorldX(24 * gp.getTileSize());
            gp.getObjects()[303].setWorldY(15 * gp.getTileSize());
            logObjectCreation(303, ObjectFactory.CAMERA, 24, 15);

            // TURNSTILE 3
            gp.getObjects()[260] = objectFactory.createObject(ObjectFactory.TURNSTILE);
            gp.getObjects()[260].setWorldX(26 * gp.getTileSize());
            gp.getObjects()[260].setWorldY(24 * gp.getTileSize());
            logObjectCreation(260, ObjectFactory.TURNSTILE, 26, 24);


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

            gp.getObjects()[startIndex + i] = objectFactory.createObject(ObjectFactory.EXIT_WARNING);
            gp.getObjects()[startIndex + i].setWorldX((baseWorldX + col) * gp.getTileSize());
            gp.getObjects()[startIndex + i].setWorldY((baseWorldY + row) * gp.getTileSize());
        }

        } catch (GameConfigurationException e) {
            System.err.println("Error al crear objeto: " + e.getMessage());
            try {
                fileWriterService.writeLog("Error al crear objeto: " + e.getMessage(), "ERROR");
            } catch (IOException ioException) {
                System.err.println("Error al escribir log: " + ioException.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error inesperado al configurar objetos: " + e.getMessage());
            try {
                fileWriterService.writeLog("Error inesperado al configurar objetos: " + e.getMessage(), "ERROR");
            } catch (IOException ioException) {
                System.err.println("Error al escribir log: " + ioException.getMessage());
            }
        }


    }
    
    /**
     * Registra la creación de un objeto en el log.
     * 
     * @param index Índice del objeto en el array
     * @param objectType Tipo de objeto creado
     * @param worldX Coordenada X en el mundo
     * @param worldY Coordenada Y en el mundo
     */
    private void logObjectCreation(int index, String objectType, int worldX, int worldY) {
        try {
            String logMessage = String.format("Objeto creado: Index=%d, Type=%s, WorldX=%d, WorldY=%d", 
                index, objectType, worldX, worldY);
            fileWriterService.logCreation(logMessage);
        } catch (IOException e) {
            System.err.println("Error al escribir log de creación: " + e.getMessage());
        }
    }

    /**
     * Configura los NPCs del juego usando EntityFactory.
     * Registra la creación en archivos de log.
     */
    public void setNPC(){
        try {
            // NPC Profe
            gp.getNPCs()[0] = entityFactory.createEntity(EntityFactory.NPC_PROFE);
            gp.getNPCs()[0].setWorldX(10 * gp.getTileSize());  // 10 40
            gp.getNPCs()[0].setWorldY(40 * gp.getTileSize());
            logEntityCreation(0, EntityFactory.NPC_PROFE, 10, 40);

            // NPC Police
            gp.getNPCs()[1] = entityFactory.createEntity(EntityFactory.NPC_POLICE);
            gp.getNPCs()[1].setWorldX(2 * gp.getTileSize());
            gp.getNPCs()[1].setWorldY(16 * gp.getTileSize());
            logEntityCreation(1, EntityFactory.NPC_POLICE, 2, 16);
            
            fileWriterService.writeLog("NPCs configurados correctamente", "INFO");
        } catch (GameConfigurationException e) {
            System.err.println("Error al crear NPC: " + e.getMessage());
            try {
                fileWriterService.writeLog("Error al crear NPC: " + e.getMessage(), "ERROR");
            } catch (IOException ioException) {
                System.err.println("Error al escribir log: " + ioException.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error inesperado al configurar NPCs: " + e.getMessage());
            try {
                fileWriterService.writeLog("Error inesperado al configurar NPCs: " + e.getMessage(), "ERROR");
            } catch (IOException ioException) {
                System.err.println("Error al escribir log: " + ioException.getMessage());
            }
        }
    }
    
    /**
     * Registra la creación de una entidad en el log.
     * 
     * @param index Índice de la entidad en el array
     * @param entityType Tipo de entidad creada
     * @param worldX Coordenada X en el mundo
     * @param worldY Coordenada Y en el mundo
     */
    private void logEntityCreation(int index, String entityType, int worldX, int worldY) {
        try {
            String logMessage = String.format("Entidad creada: Index=%d, Type=%s, WorldX=%d, WorldY=%d", 
                index, entityType, worldX, worldY);
            fileWriterService.logCreation(logMessage);
        } catch (IOException e) {
            System.err.println("Error al escribir log de creación: " + e.getMessage());
        }
    }


}
