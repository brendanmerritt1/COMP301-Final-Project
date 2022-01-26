package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // Model
    List<Clues> cluesList = PuzzleLibrary.create();
    Model model = new ModelImpl(cluesList);

    // Controller
    Controller controller = new ControllerImpl(model);

    // View
    View view = new View(controller);

    // Scene
    Scene scene = new Scene(view.render());
    stage.setScene(scene);

    // Refresh when model changes
    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          scene.setFill(Color.WHITE);
          stage.sizeToScene();
        });

    // Stage
    stage.setTitle("Nonogram");
    stage.show();
  }
}
