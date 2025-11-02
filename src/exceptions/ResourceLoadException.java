package exceptions;

/**
 * Excepción personalizada para errores de carga de recursos.
 * Aplica el principio GRASP de Information Expert.
 */
public class ResourceLoadException extends Exception {
    
    private final String resourcePath;
    
    public ResourceLoadException(String resourcePath) {
        super("Error al cargar el recurso: " + resourcePath);
        this.resourcePath = resourcePath;
    }
    
    public ResourceLoadException(String resourcePath, Throwable cause) {
        super("Error al cargar el recurso: " + resourcePath, cause);
        this.resourcePath = resourcePath;
    }
    
    public String getResourcePath() {
        return resourcePath;
    }
}
