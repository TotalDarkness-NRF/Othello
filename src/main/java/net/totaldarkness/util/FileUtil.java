package net.totaldarkness.util;

import net.totaldarkness.othello.model.OthelloGame;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

/**
 * File util allowing file operations for choosing files, opening and saving othello files.
 */
public class FileUtil {
    /**
     * Choose a file by opening the default file browser.
     * @param stage The stage to show file data.
     * @param save Whether to save or just open the file
     * @return An optional of file containing a file, or an empty optional.
     */
    public static Optional<File> chooseFile(Stage stage, boolean save) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle((save ? "Save" : "Open") + " Othello Board");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Othello Files", "*.othello"));
        File file = save ? fileChooser.showSaveDialog(stage) : fileChooser.showOpenDialog(stage);
        return Optional.ofNullable(file);
    }
    /**
     * Load an Othello File and load its information from disk to memory.
     * @param file The file to load
     * @return An optional of an OthelloGame containing the othello game information
     * if it exists, empty optional otherwise.
     */
    public static Optional<OthelloGame> loadOthelloFile(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            OthelloGame othelloGame = (OthelloGame) in.readObject();
            return Optional.ofNullable(othelloGame);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Othello game failed to load!");
        }
        return Optional.empty();
    }

    /**
     * Save an Othello File and save its information from memory to disk.
     * @param file The file to save to
     * @param game The OthelloGame to save
     */
    public static void saveOthelloToFile(File file, OthelloGame game) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
        } catch (IOException e) {
            System.out.println("Othello game failed to save to file!");
        }
    }
}