package interfaces;

/**
 * Interfaz para objetos que pueden escuchar y reaccionar a eventos del juego.
 * Aplica el principio GRASP de Controller y Indirection.
 */
public interface GameEventListener {
    /**
     * Método llamado cuando ocurre un evento del juego.
     * 
     * @param eventType El tipo de evento que ocurrió
     * @param eventData Datos adicionales del evento (puede ser null)
     */
    void onGameEvent(String eventType, Object eventData);
}
