package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HallwayWarning extends SuperObject{
    public OBJ_HallwayWarning() {
        setName("hallwaywarning");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/hallwaywarning.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCollision(false);
    }
}
