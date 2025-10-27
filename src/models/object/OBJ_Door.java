//package models.object;
//
//import javax.imageio.ImageIO;
//import java.io.IOException;
//
//public class OBJ_Door extends SuperObject{
//    public OBJ_Door(){
//        name = "door";
//        try {
//            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/glassDoor.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        collision = true;
//    }
//
//}
package models.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {
    public OBJ_Door() {
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/glassDoor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

        // 🔹 Diálogos personalizados
        dialogues[0] = "La puerta está cerrada con llave.";
        dialogues[1] = "Usaste la tarjeta y abriste la puerta!";
    }
}
