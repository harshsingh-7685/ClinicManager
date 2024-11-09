package com.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the Clinic Manager.
 * 
 * @author Harsh Singh
 * @author Surya Bhardwaj
 */
public class ClinicManagerMain extends Application {
    /**
     * Starts the Clinic Manager.
     * 
     * @param primaryStage the primary stage
     * @throws Exception if an error occurs
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("clinic-view.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Clinic Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main method to start the Clinic Manager.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
