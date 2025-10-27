package models;

import models.entity.NPC_Profe;
import models.entity.Entity;
import models.entity.Player;
import models.object.SuperObject;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 * 48 Tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 Pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 Pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;



    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public EventHandler eHandler = new EventHandler(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread; // Redraw 60 times per secods (60 FPS)
    public CollisionChecker cChecker = new CollisionChecker(this);

    public Player player = new Player(this, keyH);

    public AssetSetter aSetter = new AssetSetter(this);

    // Displays up to 10 objects at the same time
    public SuperObject obj[] = new SuperObject[1000];

    public Entity entity[] = new Entity[10];
    public Entity npc[] = new Entity[10];

    public UI ui = new UI(this);


    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;
    public final int cutsceneState = 11;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improve Rendering performance
        this.addKeyListener(keyH); // recognise key imput
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        // gameState = playState;
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public int getTileSize(){
        return tileSize;
    }

    public void retry(){
        player.setDefaultValues();
        aSetter.setObject();
    }

    //UI


    // Este método implementa el bucle principal (game loop) del juego utilizando el método "Delta".
// El objetivo del método Delta es controlar la velocidad de actualización (frames por segundo, FPS)
// de forma precisa y estable, independientemente del rendimiento del procesador.
//
// 🔹 Concepto clave:
// "Delta" representa el tiempo acumulado entre un frame y el siguiente.
// Cuando este tiempo (deltaTime) alcanza el equivalente a un frame (1/FPS),
// se ejecutan las acciones del juego: actualización de la lógica (update) y renderizado (repaint).
//
// 🔹 Paso a paso:
//
// 1️⃣ drawInterval = 1000000000 / FPS;
//     - Calcula cuántos nanosegundos deben pasar entre cada frame.
//     - Por ejemplo, si FPS = 60 → drawInterval = 16.666.666 nanosegundos.
//
// 2️⃣ lastTime = System.nanoTime();
//     - Guarda el tiempo en nanosegundos de la iteración anterior.
//
// 3️⃣ En cada ciclo del while:
//     - currentTime = System.nanoTime();
//       Obtiene el tiempo actual.
//
//     - deltaTime += (currentTime - lastTime) / drawInterval;
//       Calcula cuánto tiempo ha pasado desde el último frame,
//       y lo acumula en deltaTime (en unidades de "frames").
//
//     - lastTime = currentTime;
//       Actualiza el tiempo de referencia para la siguiente iteración.
//
// 4️⃣ if (deltaTime >= 1):
//     - Cuando ha pasado el tiempo suficiente para al menos un frame completo:
//         • update(): actualiza la lógica del juego (movimiento, colisiones, IA, etc.)
//         • repaint(): redibuja la pantalla con los cambios realizados.
//       Luego, deltaTime se reduce en 1, ya que un frame fue procesado.
//
// 🔹 Beneficio:
// Este método permite que el juego se ejecute de forma fluida y estable a la velocidad deseada,
// sin depender directamente de la velocidad de la CPU o del hardware del sistema.
// Además, mantiene el consumo eficiente de recursos al evitar actualizaciones excesivas.
//
    @Override // game loop
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) { // As long as a gameThread exists

            currentTime = System.nanoTime();

            deltaTime += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (deltaTime >= 1) {
                // Update: Info like character position
                update();

                // Draw: Draw the screen with the updated position
                repaint();
                deltaTime--;
            }


        }

    }


    // In Java the uppper right corner is X:0 Y:0
    // X values increases to the right
    // Y values increses as they go down
    public void update(){

        if(gameState == playState){
            player.update();
            
            // Actualizar NPCs
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
            
            // Verificar contacto entre jugador y NPCs
            cChecker.checkPlayerNPCContact();
        } else if (gameState == pauseState) {


           
        }

    }

    public void paintComponent(Graphics g){ // repaint
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g; 

        // TITLE SCREEN
        if (gameState == titleState){

            ui.draw(g2d);
        } else {
            // TILE
            tileM.draw(g2d);

            //OBJECT
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null) {
                    obj[i].draw(g2d, this);
                }
            }

            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null) {
                    npc[i].draw(g2d);
                }
            }

            // PLAYER
            player.draw(g2d);

            ui.draw(g2d);

            g2d.dispose();
        }

    }

}
