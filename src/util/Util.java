package util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class Util {
    public static Optional<File> chooseFile(Stage stage, boolean save) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Othello Board");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Othello Files", "*.othello"));
        File file = save ? fileChooser.showSaveDialog(stage) : fileChooser.showOpenDialog(stage);
        return Optional.ofNullable(file);
    }
}
