package interfaces;

/**
 * Interfaz para objetos que pueden ser actualizados cada frame.
 * Aplica el principio GRASP de Information Expert.
 */
public interface Updatable {
    /**
     * Actualiza el estado del objeto. Se llama cada frame del juego.
     */
    void update();
}
