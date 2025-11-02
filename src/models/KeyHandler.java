package models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPress, rightPressed, enterPressed;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Dont use
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState==gp.titleState){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                // NEW GAME
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                }
                //EXIT GAME
                if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        if(gp.gameState == gp.gameState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPress = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
                if(gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
                } else if (gp.gameState == gp.pauseState){
                    gp.gameState = gp.playState;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.gameState == gp.playState){
                    enterPressed = true;
                }
            }
        }

        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                // RETRY
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.retry();
                }
                //EXIT GAME
                if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        }

        // Dialogue State
        if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }


    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPress = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }

    }
}
