package models.factory;

import models.object.*;
import exceptions.GameConfigurationException;

/**
 * Factory para crear objetos del juego.
 * Aplica el patrón de diseño Factory Method.
 * Aplica el principio GRASP de Creator.
 */
public class ObjectFactory {
    
    // Constantes para tipos de objetos
    public static final String ACCESS_CARD = "ACCESS_CARD";
    public static final String DOOR = "DOOR";
    public static final String CAMERA = "CAMERA";
    public static final String QR_CODE = "QR_CODE";
    public static final String HALLWAY_WARNING = "HALLWAY_WARNING";
    public static final String EXIT_WARNING = "EXIT_WARNING";
    public static final String TURNSTILE = "TURNSTILE";
    
    /**
     * Crea un objeto según el tipo especificado.
     * 
     * @param objectType El tipo de objeto a crear
     * @return El objeto creado
     * @throws GameConfigurationException Si el tipo de objeto es inválido
     */
    public SuperObject createObject(String objectType) throws GameConfigurationException {
        if (objectType == null || objectType.trim().isEmpty()) {
            throw new GameConfigurationException("objectType", "El tipo de objeto no puede ser null o vacío");
        }
        
        switch (objectType.toUpperCase()) {
            case ACCESS_CARD:
                return createAccessCard();
            case DOOR:
                return createDoor();
            case CAMERA:
                return createCamera();
            case QR_CODE:
                return createQRCode();
            case HALLWAY_WARNING:
                return createHallwayWarning();
            case EXIT_WARNING:
                return createExitWarning();
            case TURNSTILE:
                return createTurnstile();
            default:
                throw new GameConfigurationException("objectType", 
                    "Tipo de objeto desconocido: " + objectType);
        }
    }
    
    /**
     * Crea una tarjeta de acceso.
     * 
     * @return Un OBJ_AccessCard
     */
    public OBJ_AccessCard createAccessCard() {
        return new OBJ_AccessCard();
    }
    
    /**
     * Crea una puerta.
     * 
     * @return Un OBJ_Door
     */
    public OBJ_Door createDoor() {
        return new OBJ_Door();
    }
    
    /**
     * Crea una cámara.
     * 
     * @return Un OBJ_Camera
     */
    public OBJ_Camera createCamera() {
        return new OBJ_Camera();
    }
    
    /**
     * Crea un código QR.
     * 
     * @return Un OBJ_QrCode
     */
    public OBJ_QrCode createQRCode() {
        return new OBJ_QrCode();
    }
    
    /**
     * Crea una advertencia de pasillo.
     * 
     * @return Un OBJ_HallwayWarning
     */
    public OBJ_HallwayWarning createHallwayWarning() {
        return new OBJ_HallwayWarning();
    }
    
    /**
     * Crea una advertencia de salida.
     * 
     * @return Un OBJ_ExitWarning
     */
    public OBJ_ExitWarning createExitWarning() {
        return new OBJ_ExitWarning();
    }
    
    /**
     * Crea un torniquete.
     * 
     * @return Un OBJ_Turnstile
     */
    public OBJ_Turnstile createTurnstile() {
        return new OBJ_Turnstile();
    }
    
    /**
     * Verifica si un tipo de objeto es válido.
     * 
     * @param objectType El tipo a verificar
     * @return true si es válido, false en caso contrario
     */
    public static boolean isValidObjectType(String objectType) {
        if (objectType == null) {
            return false;
        }
        String upperType = objectType.toUpperCase();
        return upperType.equals(ACCESS_CARD) ||
               upperType.equals(DOOR) ||
               upperType.equals(CAMERA) ||
               upperType.equals(QR_CODE) ||
               upperType.equals(HALLWAY_WARNING) ||
               upperType.equals(EXIT_WARNING) ||
               upperType.equals(TURNSTILE);
    }
}
