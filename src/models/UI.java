package models;

import java.awt.*;
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public int commandNum = 0;
    public String currentDialogue;


    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PAUSE
        if (gp.gameState == gp.playState){
        } else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // GAME OVER
        if (gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }

        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen(){

        g2.setColor(new Color(0,0,0));             // FILL BACKGROUND BLACK
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Escape UADE\n";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // BASE POSITION
        int centerX = gp.screenWidth / 2;   // centro horizontal de la pantalla
        int baseY = gp.screenHeight / 3  ;    // posición vertical de referencia (antes 2)

        int spacing = gp.tileSize;          // espacio entre ambos personajes (1 tile)
        int spriteWidth = gp.tileSize * 2;  // tamaño de cada sprite (ya estás usando *2)

        // BOY
        int boyX = centerX - spriteWidth - spacing / 2;
        int boyY = baseY;

        g2.drawImage(gp.player.bdown1, boyX, boyY, spriteWidth, spriteWidth, null);

        // GIRL
        int girlX = centerX + spacing / 2;
        int girlY = baseY;

        g2.drawImage(gp.player.gdown1, girlX, girlY, spriteWidth, spriteWidth, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4.5;
        g2.drawString(text,x,y);
        if(commandNum == 0)
        {
            g2.drawString(">",x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1)
        {
            g2.drawString(">",x - gp.tileSize, y);
        }


    }
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
        text = "Game Over";

        //Shadow
        g2.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text,x,y);

        //Text
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //RETRY
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text,x,y);
        if(commandNum == 0)
        {
            g2.drawString(">", x-40, y);
        }

        //BACK TO THE TITLE SCREEN
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text,x,y);
        if(commandNum == 1)
        {
            g2.drawString(">", x-40, y);
        }

    }

    public void drawDialogueScreen() {
        // Dimensiones de la ventana de diálogo
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 3;
        int x = (gp.screenWidth - width) / 2;
        int y = gp.tileSize * 2;

        // Dibujar la ventana
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        // Si hay texto, dividirlo por salto de línea
        if (currentDialogue != null) {
            String[] lines = currentDialogue.split("\n");
            for (String line : lines) {
                g2.drawString(line, x, y);
                y += 40; // Espaciado entre líneas
            }
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color (0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,25,25);

        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,15,15);


    }


    public int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return  x;
    }
}
