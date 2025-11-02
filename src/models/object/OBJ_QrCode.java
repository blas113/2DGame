package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_QrCode extends SuperObject{
    public OBJ_QrCode() {
        name = "qrcode";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/qrCode.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
