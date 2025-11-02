package exceptions;

/**
 * Excepción personalizada para errores de configuración del juego.
 * Aplica el principio GRASP de Information Expert.
 */
public class GameConfigurationException extends Exception {
    
    private final String configurationKey;
    
    public GameConfigurationException(String configurationKey) {
        super("Error en configuración del juego: " + configurationKey);
        this.configurationKey = configurationKey;
    }
    
    public GameConfigurationException(String configurationKey, String message) {
        super("Error en configuración del juego (" + configurationKey + "): " + message);
        this.configurationKey = configurationKey;
    }
    
    public GameConfigurationException(String configurationKey, String message, Throwable cause) {
        super("Error en configuración del juego (" + configurationKey + "): " + message, cause);
        this.configurationKey = configurationKey;
    }
    
    public String getConfigurationKey() {
        return configurationKey;
    }
}
