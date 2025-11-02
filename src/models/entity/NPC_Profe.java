package models.entity;

import models.GamePanel;

import java.awt.*;

public class NPC_Profe extends Entity {
    public NPC_Profe(GamePanel gp) {
        super(gp);
        
        setDirection("down");
        setSpeed(2); // Más rápido que el jugador 
        
        // área de colisión
        setSolidArea(new Rectangle(8, 16, 32, 32));
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
        
        getImage();
    }

    public void getImage(){
        setUp1(setup("/npc/ProfeArriba1.png"));
        setUp2(setup("/npc/ProfeArriba2.png"));
        setDown1(setup("/npc/ProfeAbajo1.png"));
        setDown2(setup("/npc/ProfeAbajo2.png"));
        setLeft1(setup("/npc/ProfeIzquierda1.png"));
        setLeft2(setup("/npc/ProfeIzquierda2.png"));
        setRight1(setup("/npc/ProfeDerecha1.png"));
        setRight2(setup("/npc/ProfeDerecha2.png"));
    }
    
    private int directionChangeCounter = 0;
    private int distanceInCurrentDirection = 0; // Distancia recorrida en la dirección actual
    private final int DIRECTION_CHANGE_THRESHOLD = 30; // Frames antes de cambiar dirección
    private final int MIN_DISTANCE_TO_CHANGE = 48; // Distancia mínima en píxeles antes de cambiar (2 tiles aproximadamente)
    
    public void update() {
        // Pathfinding hacia el jugador
        int playerWorldX = getGamePanel().getPlayer().getWorldX();
        int playerWorldY = getGamePanel().getPlayer().getWorldY();
        
        // Calcular distancia al jugador
        int deltaX = playerWorldX - getWorldX();
        int deltaY = playerWorldY - getWorldY();
        
        // Verificar si estamos en diagonal
        int absDeltaX = Math.abs(deltaX);
        int absDeltaY = Math.abs(deltaY);
        boolean isDiagonal = Math.abs(absDeltaX - absDeltaY) < getGamePanel().getTileSize();
        
        // Determinar dirección hacia el jugador (solo vertical u horizontal)
        String targetDirection = getDirectionToPlayer(deltaX, deltaY);
        
        // Verificar si la dirección actual aún es válida hacia el jugador
        boolean currentDirectionValid = isDirectionValid(getDirection(), deltaX, deltaY);
        
        // Sistema de estabilización mejorado: cuando está en diagonal, mantener dirección más tiempo
        if(!targetDirection.equals(getDirection())) {
            // Si la dirección actual no es válida, cambiar inmediatamente
            if(!currentDirectionValid) {
                directionChangeCounter = 0;
                distanceInCurrentDirection = 0;
            } else {
                directionChangeCounter++;
                
                // En diagonal, requerir más distancia recorrida antes de cambiar
                int requiredDistance = isDiagonal ? MIN_DISTANCE_TO_CHANGE : MIN_DISTANCE_TO_CHANGE / 2;
                int requiredFrames = isDiagonal ? DIRECTION_CHANGE_THRESHOLD * 2 : DIRECTION_CHANGE_THRESHOLD;
                
                // Cambiar solo si se cumplen ambas condiciones: tiempo Y distancia
                boolean shouldChange = (directionChangeCounter >= requiredFrames && 
                                       distanceInCurrentDirection >= requiredDistance) ||
                                       (!currentDirectionValid);
                
                if(!shouldChange) {
                    // Mantener la dirección actual
                    targetDirection = getDirection();
                } else {
                    // Resetear contadores al cambiar
                    directionChangeCounter = 0;
                    distanceInCurrentDirection = 0;
                }
            }
        } else {
            // Si vamos en la dirección correcta, resetear contadores
            directionChangeCounter = 0;
        }
        
        // Intentar moverse en la dirección objetivo
        setDirection(targetDirection);
        setCollisionOn(false);
        getGamePanel().getCollisionChecker().checkTile(this);
        
        // Si hay colisión, intentar direcciones alternativas (solo vertical u horizontal)
        if(isCollisionOn()) {
            String[] alternativeDirections = getAlternativeDirections(targetDirection);
            for(String altDir : alternativeDirections) {
                setDirection(altDir);
                setCollisionOn(false);
                getGamePanel().getCollisionChecker().checkTile(this);
                if(!isCollisionOn()) {
                    // Resetear contadores al encontrar nueva dirección válida
                    directionChangeCounter = 0;
                    distanceInCurrentDirection = 0;
                    break;
                }
            }
        }
        
        // Moverse si no hay colisión (solo en direcciones cardinales)
        if(!isCollisionOn()) {
            switch(getDirection()) {
                case "up":
                    setWorldY(getWorldY() - getSpeed());
                    distanceInCurrentDirection += getSpeed();
                    break;
                case "down":
                    setWorldY(getWorldY() + getSpeed());
                    distanceInCurrentDirection += getSpeed();
                    break;
                case "left":
                    setWorldX(getWorldX() - getSpeed());
                    distanceInCurrentDirection += getSpeed();
                    break;
                case "right":
                    setWorldX(getWorldX() + getSpeed());
                    distanceInCurrentDirection += getSpeed();
                    break;
            }
        } else {
            // Si hay colisión, resetear distancia recorrida
            distanceInCurrentDirection = 0;
        }
        
        // Actualizar sprite
        incrementSpriteCounter();
        if(getSpriteCounter() > 12) {
            if(getSpriteNum() == 1) {
                setSpriteNum(2);
            } else if(getSpriteNum() == 2) {
                setSpriteNum(1);
            }
            setSpriteCounter(0);
        }
    }
    
    private String getDirectionToPlayer(int deltaX, int deltaY) {
        int absDeltaX = Math.abs(deltaX);
        int absDeltaY = Math.abs(deltaY);
        
        // Umbral para detectar cuando está en diagonal
        int threshold = getGamePanel().getTileSize();
        boolean isDiagonal = Math.abs(absDeltaX - absDeltaY) < threshold;
        
        // PRIORIDAD: Si la dirección actual es válida, mantenerla especialmente en diagonal
        if(getDirection() != null && isDirectionValid(getDirection(), deltaX, deltaY)) {
            if(isDiagonal) {
                // En diagonal, mantener la dirección actual a menos que hayamos recorrido mucha distancia
                if(distanceInCurrentDirection < MIN_DISTANCE_TO_CHANGE * 2) {
                    return getDirection();
                }
            } else {
                // No diagonal: mantener si no hay razón fuerte para cambiar
                if(distanceInCurrentDirection < MIN_DISTANCE_TO_CHANGE) {
                    return getDirection();
                }
            }
        }
        
        // Cuando está en diagonal y necesitamos elegir nueva dirección
        if(isDiagonal) {
            // Elegir basándose en cuál distancia es mayor
            // Pero si la dirección actual es horizontal y estamos cerca en X, elegir vertical
            if(getDirection() != null) {
                boolean isCurrentHorizontal = getDirection().equals("left") || getDirection().equals("right");
                boolean isCurrentVertical = getDirection().equals("up") || getDirection().equals("down");
                
                // Si vamos horizontal y ya avanzamos suficiente, cambiar a vertical si es válido
                if(isCurrentHorizontal && distanceInCurrentDirection >= MIN_DISTANCE_TO_CHANGE && absDeltaY > 0) {
                    return deltaY > 0 ? "down" : "up";
                }
                // Si vamos vertical y ya avanzamos suficiente, cambiar a horizontal si es válido
                if(isCurrentVertical && distanceInCurrentDirection >= MIN_DISTANCE_TO_CHANGE && absDeltaX > 0) {
                    return deltaX > 0 ? "right" : "left";
                }
            }
            
            // Si no hay dirección previa o necesitamos elegir, tomar la de mayor distancia
            if(absDeltaX >= absDeltaY) {
                return deltaX > 0 ? "right" : "left";
            } else {
                return deltaY > 0 ? "down" : "up";
            }
        }
        
        // Cuando no está en diagonal, elegir la dirección con mayor distancia
        if(absDeltaX > absDeltaY) {
            return deltaX > 0 ? "right" : "left";
        } else {
            return deltaY > 0 ? "down" : "up";
        }
    }
    
    // Verifica si la dirección actual sigue acercando al NPC al jugador
    private boolean isDirectionValid(String dir, int deltaX, int deltaY) {
        switch(dir) {
            case "up":
                return deltaY < 0; // Jugador está arriba
            case "down":
                return deltaY > 0; // Jugador está abajo
            case "left":
                return deltaX < 0; // Jugador está a la izquierda
            case "right":
                return deltaX > 0; // Jugador está a la derecha
            default:
                return false;
        }
    }
    
    private String[] getAlternativeDirections(String primaryDirection) {
        
        switch(primaryDirection) {
            case "up":
                return new String[]{"left", "right", "down"};
            case "down":
                return new String[]{"left", "right", "up"};
            case "left":
                return new String[]{"up", "down", "right"};
            case "right":
                return new String[]{"up", "down", "left"};
            default:
                return new String[]{"up", "down", "left", "right"};
        }
    }

}
