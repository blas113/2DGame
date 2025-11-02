package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HallwayWarning extends SuperObject{
    public OBJ_HallwayWarning() {
        name = "hallwaywarning";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/hallwaywarning.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = false;
    }
}
