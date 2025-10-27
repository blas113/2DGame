package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Camera extends SuperObject{
    public OBJ_Camera() {
        name = "Camera";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/camera.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
