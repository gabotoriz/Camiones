package org.example.prueba;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Switch {
    private static final Map<Views, Parent> cache = new HashMap<>();
    private static Scene scene;

    public static void switchTo(Views view) {
        if (scene == null) {
            System.out.println("No hay escenas");
            return;
        }
        try {
            Parent root;
            if (cache.containsKey(view)) {
                root = cache.get(view);
            } else {
                root = FXMLLoader.<Parent>load(Objects.requireNonNull(Switch.class.getResource(view.getFileName())));
                cache.put(view, root);
            }
            scene.setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setScene(Scene scene) {
        Switch.scene = scene;
    }
}
