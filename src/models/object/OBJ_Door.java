package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(){
        setName("door");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/glassDoor.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCollision(true);
    }

}
