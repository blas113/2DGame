package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_QrCode extends SuperObject{
    public OBJ_QrCode() {
        setName("qrcode");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/objects/qrCode.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCollision(true);
    }
}
