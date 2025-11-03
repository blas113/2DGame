package tiles;

import models.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[100];
        // Stores numbers from res.maps
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        getTileImage();
        loadMap("/res/maps/world.txt");
    }

    public void getTileImage(){
        try {
            // VOID
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/void.png"));
            tile[0].collision = true;

            // WALLL
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
            tile[1].collision = true;

            // FLOOR
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/floor.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/floorDamaged.png"));

            // TABLE
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tableCorner.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tableCenter.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tableEnd.png"));
            tile[6].collision = true;

            // TEACHER TABLE

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/teacherTableLeft.png"));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/teacherTableRight.png"));
            tile[16].collision = true;

            // TEACHER CHAIR
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/teacherChairLeft.png"));
            tile[17].collision = true;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/teacherChairRight.png"));
            tile[18].collision = true;


            // CHAIR FOLDED
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/chairCornerFolded.png"));
            tile[7].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/chairCenterFolded.png"));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/chairEndFolded.png"));
            tile[11].collision = true;

            // CHAIR UNFOLDED
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/chairCornerUnfolded.png"));
            tile[8].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/chairCenterUnfolded.png"));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/chairEndUnfolded.png"));
            tile[13].collision = true;

            // HALLWAY
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/hallway.png"));

            // EXIT
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/exitFloor.png"));

            // TURNSTILE EMPTY
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/emptyWindmill.png"));
            tile[20].collision = true;

            // TURNSTILE ON
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/positiveWindmill.png"));
            tile[21].collision = true;

            // TURNSTILE OFF
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/negativeWindmill.png"));
            tile[22].collision = true;

            // TURNSTILE OFF
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/windmillDisabled.png"));
            tile[23].collision = true;

            // WARNING PANEL
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wallBoard.png"));
            tile[24].collision = true;

            // UPSIDE DONW WARNING PANEL
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wallBoardInverted.png"));
            tile[25].collision = true;

            // EXPENDING MACHINE
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/expendingMachine.png"));
            tile[26].collision = true;

            // EXPENDING MACHINE INVERTED
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/expendingMachineInverted.png"));
            tile[27].collision = true;

            // HOT WATER DISPENSER
            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/hotWaterDispenser.png"));
            tile[28].collision = true;

            // HOT WATER DISPENSER INVERTED
            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/hotWaterDispenserInverted.png"));
            tile[29].collision = true;

            // LOCKERS
            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/lockersUp.png"));
            tile[30].collision = true;

            // LOCKERS INVERTED
            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/lockersInverted.png"));
            tile[31].collision = true;

            // SMOKE SENSOR
            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/smokeSensor.png"));
            tile[32].collision = true;

            // SMOKE SENSOR INVERTED
            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/smokeSensorInverted.png"));
            tile[33].collision = true;

            // WATCH
            tile[34] = new Tile();
            tile[34].image= ImageIO.read(getClass().getResourceAsStream("/res/tiles/watchUp.png"));
            tile[34].collision = true;

            // WATCH INVERTED
            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/watchInverted.png"));
            tile[35].collision = true;

            // PANEL
            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/panelUp.png"));
            tile[36].collision = true;

            // PANEL INVERTED
            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/panelInverted.png"));
            tile[37].collision = true;

            // WINDOW
            tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/windowDown.png"));
            tile[38].collision = true;

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/windowUp.png"));
            tile[39].collision = true;

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/windowMid.png"));
            tile[40].collision = true;

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/window.png"));
            tile[41].collision = true;

            // BOARD
            tile[42] = new Tile();
            tile[42].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/boardLeft.png"));
            tile[42].collision = true;

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/boardRight.png"));
            tile[43].collision = true;

            tile[44] = new Tile();
            tile[44].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/boardMid.png"));
            tile[44].collision = true;

            tile[45] = new Tile();
            tile[45].image = ImageIO.read(getClass().getResourceAsStream("/res/objects/doorExitPositive.png"));
            tile[45].collision = true;

            tile[46] = new Tile();
            tile[46].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/plantUp.png"));
            tile[46].collision = true;

            tile[47] = new Tile();
            tile[47].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/plantDown.png"));
            tile[47].collision = true;

        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()){
                String line = br.readLine();
                while (col < gp.getMaxWorldCol()){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]); // Casts string to int
                    mapTileNum [col][row] = num;
                    col++;
                }

                if(col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();


        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()){
            int tileNum = mapTileNum[worldCol][worldRow];


            int worldX = worldCol * gp.getTileSize(); // Is the position on the map
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX(); // Where we draw the tiles on the screen
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            // We do not need to draw tiles that are 500 pixels away from the player, this cost resources, so we need
            // to optimize it.

            // AS long as the tiles are in the boundary, we draw it.
            if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }

            worldCol++;
            if (worldCol == gp.getMaxWorldCol()){
                worldCol = 0;
                worldRow ++;
            }
        }

    }

}
