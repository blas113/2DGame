package interfaces;

import java.awt.Rectangle;

/**
 * Interfaz para objetos que pueden colisionar con otros objetos.
 * Aplica el principio GRASP de Information Expert y Low Coupling.
 */
public interface Collidable {
    /**
     * Obtiene el área de colisión del objeto.
     * 
     * @return El rectángulo que representa el área de colisión
     */
    Rectangle getSolidArea();
    
    /**
     * Obtiene la posición X por defecto del área de colisión.
     * 
     * @return La posición X por defecto
     */
    int getSolidAreaDefaultX();
    
    /**
     * Obtiene la posición Y por defecto del área de colisión.
     * 
     * @return La posición Y por defecto
     */
    int getSolidAreaDefaultY();
    
    /**
     * Verifica si el objeto está en estado de colisión.
     * 
     * @return true si hay colisión, false en caso contrario
     */
    boolean isCollisionOn();
    
    /**
     * Establece el estado de colisión del objeto.
     * 
     * @param collisionOn true si hay colisión, false en caso contrario
     */
    void setCollisionOn(boolean collisionOn);
}
