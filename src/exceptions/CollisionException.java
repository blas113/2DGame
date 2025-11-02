package exceptions;

/**
 * Excepción personalizada para errores relacionados con colisiones.
 * Aplica el principio GRASP de Information Expert.
 */
public class CollisionException extends Exception {
    
    public CollisionException(String message) {
        super("Error de colisión: " + message);
    }
    
    public CollisionException(String message, Throwable cause) {
        super("Error de colisión: " + message, cause);
    }
}
