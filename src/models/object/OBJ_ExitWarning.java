package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ExitWarning extends SuperObject{
    public OBJ_ExitWarning(){
        name = "exitwarning";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/exitFloorWarning.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = false;
    }
}
