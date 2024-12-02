package util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class Util {
    public static Optional<File> chooseFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Othello Board");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Othello Files", "*.othello"));
        File file = fileChooser.showSaveDialog(stage);
        return Optional.ofNullable(file);
    }
}
