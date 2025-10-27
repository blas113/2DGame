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

 //   public void titleState(int code) {
        //MAIN MENU
//        if (gp.ui.titleScreenState == 0) {
//            if (code == KeyEvent.VK_W) {
//                gp.ui.commandNum--;
//                if (gp.ui.commandNum < 0) {
//                    gp.ui.commandNum = 2;
//                }
//            }
//            if (code == KeyEvent.VK_S) {
//                gp.ui.commandNum++;
//                if (gp.ui.commandNum > 2) {
//                    gp.ui.commandNum = 0;
//                }
//            }
//            if (code == KeyEvent.VK_ENTER) {
//                if (gp.ui.commandNum == 0) {
//                    gp.ui.titleScreenState = 1; // Character class selection screen
//                    //gp.gameState = gp.playState;
//                }
//                if (gp.ui.commandNum == 1) {
//                    //LOAD GAME
//                    gp.saveLoad.load();
//                    gp.gameState = gp.playState;
//                    gp.playMusic(0);
//
//                }
//                if (gp.ui.commandNum == 2) {
//                    System.exit(0);
//                }
//            }
//        }
//        if (gp.gameState == gp.gameOverState){
//            gameOverState(code);
//        }
   // }

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
        if(gp.gameState == gp.playState){
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
                //if(gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
               // } else if (gp.gameState == gp.pauseState){
                 //   gp.gameState = gp.playState;
                //}
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
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
        //pause state
        else if (gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_W){
                gp.gameState = gp.playState;
            }

        }
        //dialogue state
        else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }

        }


    }


//    public void gameOverState(int code)
//    {
//        System.out.println("Testing");
//        if(code == KeyEvent.VK_W)
//        {
//            gp.ui.commandNum--;
//            if(gp.ui.commandNum < 0)
//            {
//                gp.ui.commandNum = 1;
//            }
//            //gp.playSE(9);
//        }
//        if(code == KeyEvent.VK_S)
//        {
//            gp.ui.commandNum++;
//            if(gp.ui.commandNum > 1)
//            {
//                gp.ui.commandNum = 0;
//            }
//            //gp.playSE(9);
//        }
//        if(code == KeyEvent.VK_ENTER)
//        {
//            if(gp.ui.commandNum == 0) //RETRY, reset position, life, mana, monsters, npcs...
//            {
//                gp.gameState = gp.playState;
//                //gp.resetGame(false);
//                //gp.playMusic(0);
//            }
//            else if(gp.ui.commandNum == 1) //QUIT, reset everything
//            {
//                //gp.ui.titleScreenState = 0;
//                gp.gameState = gp.titleState;
//                //gp.resetGame(true);
//            }
//        }
//    }

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
    }
}
