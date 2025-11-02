package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Turnstile extends SuperObject{
    public OBJ_Turnstile() {
        setName("molinete");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/turnstile.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCollision(false);
    }
}
