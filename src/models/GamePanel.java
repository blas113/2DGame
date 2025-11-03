package models;

import models.entity.Entity;
import models.entity.Player;
import models.object.SuperObject;
import tiles.TileManager;
import interfaces.Updatable;
import interfaces.Drawable;
import interfaces.DrawableWithContext;
import exceptions.GameStateException;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale; // 48 * 48 Tile
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol; // 768 Pixels
    private final int screenHeight = tileSize * maxScreenRow; // 576 Pixels

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    // FPS
    private int FPS = 60;

    private TileManager tileM = new TileManager(this);
    private EventHandler eHandler = new EventHandler(this);
    private KeyHandler keyH = new KeyHandler(this);
    private Thread gameThread; // Redraw 60 times per secods (60 FPS)
    private CollisionChecker cChecker = new CollisionChecker(this);

    private Player player = new Player(this, keyH);

    private AssetSetter aSetter = new AssetSetter(this);

    // Displays up to 10 objects at the same time
    private SuperObject obj[] = new SuperObject[1000];
    private Entity npc[] = new Entity[10];

    private UI ui = new UI(this);

    //GAME STATE
    private int gameState;
    public static final int titleState = 0;
    public static final int playState = 1;
    public static final int pauseState = 2;
    public static final int dialogueState = 3;
    public static final int gameOverState = 6;
    public static final int gameEndState = 7;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Improve Rendering performance
        this.addKeyListener(keyH); // recognise key imput
        this.setFocusable(true);
    }

    /**
     * Configura el juego inicial.
     * Aplica el principio GRASP de Creator (AssetSetter crea los objetos).
     */
    public void setupGame() {
        try {
            aSetter.setObject();
            aSetter.setNPC();
            setGameStateStrict(titleState);
        } catch (GameStateException e) {
            System.err.println("Error crítico al configurar el juego: " + e.getMessage());
            e.printStackTrace();
            // Intentar establecer un estado por defecto
            this.gameState = titleState;
        } catch (Exception e) {
            System.err.println("Error inesperado al configurar el juego: " + e.getMessage());
            e.printStackTrace();
            this.gameState = titleState;
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Getters y Setters
    public int getTileSize(){
        return tileSize;
    }

    public int getDialogueState(){
        return dialogueState;
    }
    
    public int getScreenWidth() {
        return screenWidth;
    }
    
    public int getScreenHeight() {
        return screenHeight;
    }
    
    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    
    public int getMaxWorldRow() {
        return maxWorldRow;
    }
    
    public TileManager getTileManager() {
        return tileM;
    }
    
    public EventHandler getEventHandler() {
        return eHandler;
    }
    
    public KeyHandler getKeyHandler() {
        return keyH;
    }
    
    public CollisionChecker getCollisionChecker() {
        return cChecker;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public AssetSetter getAssetSetter() {
        return aSetter;
    }
    
    public SuperObject[] getObjects() {
        return obj;
    }
    
    public Entity[] getNPCs() {
        return npc;
    }
    
    public UI getGameUI() {
        return ui;
    }
    
    public int getGameState() {
        return gameState;
    }
    
    /**
     * Establece el estado del juego.
     * Aplica el principio GRASP de Controller.
     * 
     * @param gameState El nuevo estado del juego
     */
    public void setGameState(int gameState) {
        // Validación interna sin lanzar excepción para evitar interrumpir el juego
        if (gameState < 0 || (gameState > 3 && (gameState != 7 && gameState != 6))) {
            System.err.println("Advertencia: Intento de establecer estado de juego inválido: " + gameState);
            return;
        }
        this.gameState = gameState;
    }
    
    /**
     * Valida y establece el estado del juego con validación estricta.
     * Útil para inicialización donde los errores son críticos.
     * 
     * @param gameState El nuevo estado del juego
     * @throws GameStateException Si el estado es inválido
     */
    public void setGameStateStrict(int gameState) throws GameStateException {
        if (gameState < 0 || (gameState > 3 && gameState != 6)) {
            throw new GameStateException(gameState, "Estado de juego inválido");
        }
        this.gameState = gameState;
    }

    public void retry(){
        player.setDefaultValues();
        aSetter.setNPC();
        aSetter.setObject();
        player.setHasKey(0);

    }


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

        if(getGameState() == playState){
            player.update();

            // Actualizar NPCs - Usando interfaz Updatable (GRASP: Polymorphism, Low Coupling)
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null && npc[i] instanceof Updatable) {
                    ((Updatable) npc[i]).update();
                }
            }

            // Verificar contacto entre jugador y NPCs
            cChecker.checkPlayerNPCContact();
        } else if (getGameState() == pauseState) {
            // Nothing
        }

    }

    public void paintComponent(Graphics g){ // repaint
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g; // More sofisticated control, its a subclass of Graphics

        // TITLE SCREEN
        if (getGameState() == titleState){

            ui.draw(g2d);
        } else {
            // TILE
            tileM.draw(g2d);

            //OBJECT - Usando interfaz DrawableWithContext (GRASP: Polymorphism, Low Coupling)
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null && obj[i] instanceof DrawableWithContext) {
                    ((DrawableWithContext) obj[i]).draw(g2d, this);
                }
            }
            // NPC FOR LOOP - Usando interfaz Drawable (GRASP: Polymorphism, Low Coupling)
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null && npc[i] instanceof Drawable) {
                    ((Drawable) npc[i]).draw(g2d);
                }
            }

            // PLAYER
            player.draw(g2d);

            ui.draw(g2d);

            g2d.dispose();
        }

    }

}
