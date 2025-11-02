package models.factory;

import models.GamePanel;
import models.entity.Entity;
import models.entity.NPC_Police;
import models.entity.NPC_Profe;
import exceptions.GameConfigurationException;

/**
 * Factory para crear entidades del juego.
 * Aplica el patrón de diseño Factory Method.
 * Aplica el principio GRASP de Creator.
 */
public class EntityFactory {
    
    // Constantes para tipos de entidades
    public static final String NPC_PROFE = "NPC_PROFE";
    public static final String NPC_POLICE = "NPC_POLICE";
    
    private final GamePanel gamePanel;
    
    /**
     * Constructor de la factory.
     * 
     * @param gamePanel El GamePanel necesario para crear entidades
     */
    public EntityFactory(GamePanel gamePanel) {
        if (gamePanel == null) {
            throw new IllegalArgumentException("GamePanel no puede ser null");
        }
        this.gamePanel = gamePanel;
    }
    
    /**
     * Crea una entidad según el tipo especificado.
     * 
     * @param entityType El tipo de entidad a crear (NPC_PROFE, NPC_POLICE)
     * @return La entidad creada
     * @throws GameConfigurationException Si el tipo de entidad es inválido
     */
    public Entity createEntity(String entityType) throws GameConfigurationException {
        if (entityType == null || entityType.trim().isEmpty()) {
            throw new GameConfigurationException("entityType", "El tipo de entidad no puede ser null o vacío");
        }
        
        switch (entityType.toUpperCase()) {
            case NPC_PROFE:
                return createNPCProfe();
            case NPC_POLICE:
                return createNPCPolice();
            default:
                throw new GameConfigurationException("entityType", 
                    "Tipo de entidad desconocido: " + entityType);
        }
    }
    
    /**
     * Crea un NPC Profesor.
     * 
     * @return Un NPC_Profe
     */
    public NPC_Profe createNPCProfe() {
        return new NPC_Profe(gamePanel);
    }
    
    /**
     * Crea un NPC Policía.
     * 
     * @return Un NPC_Police
     */
    public NPC_Police createNPCPolice() {
        return new NPC_Police(gamePanel);
    }
    
    /**
     * Verifica si un tipo de entidad es válido.
     * 
     * @param entityType El tipo a verificar
     * @return true si es válido, false en caso contrario
     */
    public static boolean isValidEntityType(String entityType) {
        if (entityType == null) {
            return false;
        }
        String upperType = entityType.toUpperCase();
        return upperType.equals(NPC_PROFE) || upperType.equals(NPC_POLICE);
    }
}
