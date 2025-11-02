package models.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Servicio para escribir información en archivos de texto.
 * Aplica el principio GRASP de Information Expert.
 */
public class FileWriterService {
    
    private static final String LOG_DIRECTORY = "logs/";
    private static final String DATA_DIRECTORY = "data/";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Escribe una línea en un archivo.
     * 
     * @param filename Nombre del archivo (con o sin ruta)
     * @param content Contenido a escribir
     * @param append Si es true, agrega al final del archivo; si es false, sobrescribe
     * @throws IOException Si ocurre un error al escribir
     */
    public void writeToFile(String filename, String content, boolean append) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, append))) {
            writer.println(content);
        }
    }
    
    /**
     * Escribe múltiples líneas en un archivo.
     * 
     * @param filename Nombre del archivo
     * @param lines Lista de líneas a escribir
     * @param append Si es true, agrega al final; si es false, sobrescribe
     * @throws IOException Si ocurre un error al escribir
     */
    public void writeLinesToFile(String filename, List<String> lines, boolean append) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, append))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }
    
    /**
     * Escribe un log con timestamp en el archivo de log.
     * 
     * @param message Mensaje del log
     * @param logLevel Nivel del log (INFO, WARNING, ERROR, etc.)
     * @throws IOException Si ocurre un error al escribir
     */
    public void writeLog(String message, String logLevel) throws IOException {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String logEntry = String.format("[%s] [%s] %s", timestamp, logLevel, message);
        
        String logFile = LOG_DIRECTORY + "game_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".log";
        writeToFile(logFile, logEntry, true);
    }
    
    /**
     * Escribe el estado del juego en un archivo.
     * 
     * @param gameState Estado del juego a guardar
     * @throws IOException Si ocurre un error al escribir
     */
    public void saveGameState(String gameState) throws IOException {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String filename = DATA_DIRECTORY + "game_state_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt";
        writeToFile(filename, "Game State - " + timestamp, false);
        writeToFile(filename, "====================", true);
        writeToFile(filename, gameState, true);
    }
    
    /**
     * Escribe información de creación de objetos/entidades en un archivo.
     * 
     * @param creationInfo Información sobre la creación
     * @throws IOException Si ocurre un error al escribir
     */
    public void logCreation(String creationInfo) throws IOException {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String logEntry = String.format("[%s] CREATION: %s", timestamp, creationInfo);
        
        String logFile = DATA_DIRECTORY + "creation_log.txt";
        writeToFile(logFile, logEntry, true);
    }
    
    /**
     * Escribe estadísticas del juego en un archivo.
     * 
     * @param stats Estadísticas formateadas como texto
     * @throws IOException Si ocurre un error al escribir
     */
    public void saveGameStats(String stats) throws IOException {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String filename = DATA_DIRECTORY + "game_stats_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt";
        writeToFile(filename, "Game Statistics - " + timestamp, false);
        writeToFile(filename, "====================", true);
        writeToFile(filename, stats, true);
    }
    
    /**
     * Crea los directorios necesarios si no existen.
     */
    public void ensureDirectoriesExist() {
        java.io.File logDir = new java.io.File(LOG_DIRECTORY);
        java.io.File dataDir = new java.io.File(DATA_DIRECTORY);
        
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
}
