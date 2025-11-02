package exceptions;

/**
 * Excepción personalizada para errores relacionados con el estado del juego.
 * Aplica el principio GRASP de Information Expert.
 */
public class GameStateException extends Exception {
    
    private final int invalidState;
    
    public GameStateException(int invalidState) {
        super("Estado de juego inválido: " + invalidState);
        this.invalidState = invalidState;
    }
    
    public GameStateException(int invalidState, String message) {
        super(message + " (Estado: " + invalidState + ")");
        this.invalidState = invalidState;
    }
    
    public GameStateException(int invalidState, String message, Throwable cause) {
        super(message + " (Estado: " + invalidState + ")", cause);
        this.invalidState = invalidState;
    }
    
    public int getInvalidState() {
        return invalidState;
    }
}
