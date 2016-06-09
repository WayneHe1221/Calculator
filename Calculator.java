/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Wayne
 */
public class Calculator extends Application {

    Parent parent;
    Scene scene;

    @Override
    public void start(Stage mystage) {
        try {
            parent = FXMLLoader.load(getClass().getResource("standardMode.fxml"));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        scene = new Scene(parent);
        mystage.setScene(scene);
        mystage.setTitle("StandardMode");
        mystage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
