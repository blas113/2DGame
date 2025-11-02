package interfaces;

import java.awt.Graphics2D;

/**
 * Interfaz para objetos que pueden ser dibujados en pantalla.
 * Aplica el principio GRASP de Polymorphism.
 */
public interface Drawable {
    /**
     * Dibuja el objeto en el contexto gráfico proporcionado.
     * 
     * @param g2 El contexto gráfico donde se dibujará
     */
    void draw(Graphics2D g2);
}
