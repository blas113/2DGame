package models.entity;

import models.GamePanel;

import java.awt.*;

public class NPC_Police extends  Entity{
    public NPC_Police(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        // área de colisión
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/npc/SeguridadArriba1.png");
        up2 = setup("/npc/SeguridadArriba2.png");
        down1 = setup("/npc/SeguridadAbajo1.png");
        down2 = setup("/npc/SeguridadAbajo2.png");
        left1 = setup("/npc/SeguridadIzquierda1.png");
        left2 = setup("/npc/SeguridadIzquierda2.png");
        right1 = setup("/npc/SeguridadDerecha1.png");
        right2 = setup("/npc/SeguridadDerecha2.png");
    }

    private int directionChangeCounter = 0;
    private int distanceInCurrentDirection = 0; // Distancia recorrida en la dirección actual
    private final int DIRECTION_CHANGE_THRESHOLD = 30; // Frames antes de cambiar dirección
    private final int MIN_DISTANCE_TO_CHANGE = 48; // Distancia mínima en píxeles antes de cambiar (2 tiles aproximadamente)

    public void update() {
        // Pathfinding hacia el jugador
        int playerWorldX = gp.player.worldX;
        int playerWorldY = gp.player.worldY;

        // Calcular distancia al jugador
        int deltaX = playerWorldX - worldX;
        int deltaY = playerWorldY - worldY;

        // Verificar si estamos en diagonal
        int absDeltaX = Math.abs(deltaX);
        int absDeltaY = Math.abs(deltaY);
        boolean isDiagonal = Math.abs(absDeltaX - absDeltaY) < gp.tileSize;

        // Determinar dirección hacia el jugador (solo vertical u horizontal)
        String targetDirection = getDirectionToPlayer(deltaX, deltaY);

        // Verificar si la dirección actual aún es válida hacia el jugador
        boolean currentDirectionValid = isDirectionValid(direction, deltaX, deltaY);

        // Sistema de estabilización mejorado: cuando está en diagonal, mantener dirección más tiempo
        if(!targetDirection.equals(direction)) {
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
                    targetDirection = direction;
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
        direction = targetDirection;
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // Si hay colisión, intentar direcciones alternativas (solo vertical u horizontal)
        if(collisionOn) {
            String[] alternativeDirections = getAlternativeDirections(targetDirection);
            for(String altDir : alternativeDirections) {
                direction = altDir;
                collisionOn = false;
                gp.cChecker.checkTile(this);
                if(!collisionOn) {
                    // Resetear contadores al encontrar nueva dirección válida
                    directionChangeCounter = 0;
                    distanceInCurrentDirection = 0;
                    break;
                }
            }
        }

        // Moverse si no hay colisión (solo en direcciones cardinales)
        if(!collisionOn) {
            switch(direction) {
                case "up":
                    worldY -= speed;
                    distanceInCurrentDirection += speed;
                    break;
                case "down":
                    worldY += speed;
                    distanceInCurrentDirection += speed;
                    break;
                case "left":
                    worldX -= speed;
                    distanceInCurrentDirection += speed;
                    break;
                case "right":
                    worldX += speed;
                    distanceInCurrentDirection += speed;
                    break;
            }
        } else {
            // Si hay colisión, resetear distancia recorrida
            distanceInCurrentDirection = 0;
        }

        // Actualizar sprite
        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            } else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    private String getDirectionToPlayer(int deltaX, int deltaY) {
        int absDeltaX = Math.abs(deltaX);
        int absDeltaY = Math.abs(deltaY);

        // Umbral para detectar cuando está en diagonal
        int threshold = gp.tileSize;
        boolean isDiagonal = Math.abs(absDeltaX - absDeltaY) < threshold;

        // PRIORIDAD: Si la dirección actual es válida, mantenerla especialmente en diagonal
        if(direction != null && isDirectionValid(direction, deltaX, deltaY)) {
            if(isDiagonal) {
                // En diagonal, mantener la dirección actual a menos que hayamos recorrido mucha distancia
                if(distanceInCurrentDirection < MIN_DISTANCE_TO_CHANGE * 2) {
                    return direction;
                }
            } else {
                // No diagonal: mantener si no hay razón fuerte para cambiar
                if(distanceInCurrentDirection < MIN_DISTANCE_TO_CHANGE) {
                    return direction;
                }
            }
        }

        // Cuando está en diagonal y necesitamos elegir nueva dirección
        if(isDiagonal) {
            // Elegir basándose en cuál distancia es mayor
            // Pero si la dirección actual es horizontal y estamos cerca en X, elegir vertical
            if(direction != null) {
                boolean isCurrentHorizontal = direction.equals("left") || direction.equals("right");
                boolean isCurrentVertical = direction.equals("up") || direction.equals("down");

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
