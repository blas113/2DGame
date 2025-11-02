package interfaces;

import java.awt.Graphics2D;

/**
 * Interfaz para objetos que pueden ser dibujados con contexto adicional.
 * Aplica el principio GRASP de Polymorphism y Low Coupling.
 */
public interface DrawableWithContext {
    /**
     * Dibuja el objeto en el contexto gráfico proporcionado.
     * 
     * @param g2 El contexto gráfico donde se dibujará
     * @param context El contexto adicional necesario para el dibujado (puede ser null)
     */
    void draw(Graphics2D g2, Object context);
}
