package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_AccessCard extends SuperObject {
    public OBJ_AccessCard() {
        setName("accesscard");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/accessCard.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCollision(true);
    }

}
