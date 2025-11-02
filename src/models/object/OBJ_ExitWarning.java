package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ExitWarning extends SuperObject{
    public OBJ_ExitWarning(){
        setName("exitwarning");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/exitFloorWarning.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCollision(false);
    }
}
