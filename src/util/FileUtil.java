package util;

import othello.model.OthelloGame;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public class FileUtil {
    public static Optional<File> chooseFile(Stage stage, boolean save) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle((save ? "Save" : "Open") + " Othello Board");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Othello Files", "*.othello"));
        File file = save ? fileChooser.showSaveDialog(stage) : fileChooser.showOpenDialog(stage);
        return Optional.ofNullable(file);
    }

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
