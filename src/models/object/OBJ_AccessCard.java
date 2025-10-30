package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_AccessCard extends SuperObject {
    public OBJ_AccessCard() {
        name = "accesscard";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/accessCard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }

}
