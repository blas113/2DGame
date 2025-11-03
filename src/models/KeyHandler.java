package models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean enterPressed;

    private GamePanel gp;
    
    // Getters
    public boolean isUpPressed() {
        return upPressed;
    }
    
    public boolean isDownPressed() {
        return downPressed;
    }
    
    public boolean isLeftPressed() {
        return leftPressed;
    }
    
    public boolean isRightPressed() {
        return rightPressed;
    }
    
    public boolean isEnterPressed() {
        return enterPressed;
    }

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
        if(gp.getGameState() == GamePanel.titleState){
            if(code == KeyEvent.VK_W){
                int newCommandNum = gp.getGameUI().getCommandNum() - 1;
                if(newCommandNum < 0){
                    gp.getGameUI().setCommandNum(1);
                } else {
                    gp.getGameUI().setCommandNum(newCommandNum);
                }
            }
            if(code == KeyEvent.VK_S){
                int newCommandNum = gp.getGameUI().getCommandNum() + 1;
                if(newCommandNum > 1){
                    gp.getGameUI().setCommandNum(0);
                } else {
                    gp.getGameUI().setCommandNum(newCommandNum);
                }
            }
            if (code == KeyEvent.VK_ENTER){
                // NEW GAME
                if(gp.getGameUI().getCommandNum() == 0) {
                    gp.setGameState(GamePanel.playState);
                }
                //EXIT GAME
                if (gp.getGameUI().getCommandNum() == 1) {
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        if(gp.getGameState() == GamePanel.playState || gp.getGameState() == GamePanel.pauseState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
                if(gp.getGameState() == GamePanel.playState){
                    gp.setGameState(GamePanel.pauseState);
                } else if (gp.getGameState() == GamePanel.pauseState){
                    gp.setGameState(GamePanel.playState);
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.getGameState() == GamePanel.playState){
                    enterPressed = true;
                }
            }
        }


        // GAME OVER STATE
        if (gp.getGameState() == GamePanel.gameOverState || gp.getGameState() == GamePanel.gameEndState) {
            if(code == KeyEvent.VK_W){
                int newCommandNum = gp.getGameUI().getCommandNum() - 1;
                if(newCommandNum < 0){
                    gp.getGameUI().setCommandNum(1);
                } else {
                    gp.getGameUI().setCommandNum(newCommandNum);
                }
            }
            if(code == KeyEvent.VK_S){
                int newCommandNum = gp.getGameUI().getCommandNum() + 1;
                if(newCommandNum > 1){
                    gp.getGameUI().setCommandNum(0);
                } else {
                    gp.getGameUI().setCommandNum(newCommandNum);
                }
            }
            if (code == KeyEvent.VK_ENTER){
                // RETRY
                if(gp.getGameUI().getCommandNum() == 0) {
                    gp.setGameState(GamePanel.playState);
                    gp.retry();
                }
                //EXIT GAME
                if (gp.getGameUI().getCommandNum() == 1) {
                    System.exit(0);
                }
            }
        }

        // Dialogue State
        if (gp.getGameState() == GamePanel.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.setGameState(GamePanel.playState);
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
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }

    }
}
