package models.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Objeto Tarjeta de Acceso.
 * Aplica el principio GRASP de Information Expert.
 */
public class OBJ_AccessCard extends SuperObject {
    public OBJ_AccessCard() {
        setName("accesscard");
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/res/objects/accessCard.png"));
            if (image == null) {
                System.err.println("Error: No se pudo cargar la imagen accessCard.png (null)");
            } else {
                setImage(image);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagen de AccessCard: " + e.getMessage());
            e.printStackTrace();
            // Continuar sin imagen en lugar de fallar
        }

        setCollision(true);
    }

}
