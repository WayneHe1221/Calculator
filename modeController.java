/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Wayne
 */
public class modeController implements Initializable {

    String a[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    @FXML
    Pane pane;
    Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    Button mark1, mark2, mark3, mark4, mark5, mark6, mark7, mark8, mark9, mark10, mark11;
    Button mode1, mode2, mode3;
    TextArea ta1;
    Stage prevStage;
    

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }

    @FXML
    private void switchSMode(ActionEvent event) throws IOException {

        //load up OTHER FXML document
        Parent root = FXMLLoader.load(getClass().getResource("standardMode.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.hide();
        stage.setScene(scene);
        stage.show();//load up OTHER FXML document
    }

    @FXML
    private void switchPDMode(ActionEvent event) throws IOException {

        //load up OTHER FXML document
        Parent root = FXMLLoader.load(getClass().getResource("pdMode.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchERMode(ActionEvent event) throws IOException {

    }

    public modeController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    // judge the num whether it got a % mark or not
    public boolean checkPercentMark(String num) {
        char[] checkMark = num.toCharArray();
        if (checkMark[num.length() - 1] == '%') {
            return true;
        } else {
            return false;
        }
    }

    // math of multiply
    public double multiplyResult(String num1, String num2, Double result, int mark, int length, TextArea ta1) {
        if (checkPercentMark(num1) && checkPercentMark(num2)) {
            num1 = ta1.getText(0, mark - 1);
            num2 = ta1.getText(mark + 1, length - 1);
            result = (Double.parseDouble(num1) / 100) * (Double.parseDouble(num2) / 100);
        } else if (checkPercentMark(num1)) {
            num1 = ta1.getText(0, mark - 1);
            result = (Double.parseDouble(num1) / 100) * Double.parseDouble(num2);
        } else if (checkPercentMark(num2)) {
            num2 = ta1.getText(mark + 1, length - 1);
            result = Double.parseDouble(num1) * (Double.parseDouble(num2) / 100);
        } else {
            result = Double.parseDouble(num1) * Double.parseDouble(num2);
        }
        return result;
    }

    // math of division
    public double divisionResult(String num1, String num2, Double result, int mark, int length, TextArea ta1) {
        if (checkPercentMark(num1) && checkPercentMark(num2)) {
            num1 = ta1.getText(0, mark - 1);
            num2 = ta1.getText(mark + 1, length - 1);
            result = Double.parseDouble(num1) / Double.parseDouble(num2);
        } else if (checkPercentMark(num1)) {
            num1 = ta1.getText(0, mark - 1);
            result = (Double.parseDouble(num1) / 100) / Double.parseDouble(num2);
        } else if (checkPercentMark(num2)) {
            num2 = ta1.getText(mark + 1, length - 1);
            result = Double.parseDouble(num1) / (Double.parseDouble(num2) / 100);
        } else {
            result = Double.parseDouble(num1) / Double.parseDouble(num2);
        }
        return result;
    }

    // math of plus
    public double plusResult(String num1, String num2, Double result, int mark, int length, TextArea ta1) {
        if (checkPercentMark(num1) && checkPercentMark(num2)) {
            num1 = ta1.getText(0, mark - 1);
            num2 = ta1.getText(mark + 1, length - 1);
            result = (Double.parseDouble(num1) / 100) + (Double.parseDouble(num2) / 100);
        } else if (checkPercentMark(num1)) {
            num1 = ta1.getText(0, mark - 1);
            result = (Double.parseDouble(num1) / 100) + Double.parseDouble(num2);
        } else if (checkPercentMark(num2)) {
            num2 = ta1.getText(mark + 1, length - 1);
            result = Double.parseDouble(num1) + (Double.parseDouble(num2) / 100);
        } else {
            result = Double.parseDouble(num1) + Double.parseDouble(num2);
        }
        return result;
    }

    // math of minus
    public double minusResult(String num1, String num2, Double result, int mark, int length, TextArea ta1) {
        if (checkPercentMark(num1) && checkPercentMark(num2)) {
            num1 = ta1.getText(0, mark - 1);
            num2 = ta1.getText(mark + 1, length - 1);
            result = (Double.parseDouble(num1) / 100) - (Double.parseDouble(num2) / 100);
        } else if (checkPercentMark(num1)) {
            num1 = ta1.getText(0, mark - 1);
            result = (Double.parseDouble(num1) / 100) - Double.parseDouble(num2);
        } else if (checkPercentMark(num2)) {
            num2 = ta1.getText(mark + 1, length - 1);
            result = Double.parseDouble(num1) - (Double.parseDouble(num2) / 100);
        } else {
            result = Double.parseDouble(num1) - Double.parseDouble(num2);
        }
        return result;
    }

    // judge whether the answer is type in double or integer
    public void getResult(double result, TextArea ta1) {
        if (result % 1 == 0) {
            ta1.clear();
            ta1.appendText(Integer.toString((int) result));
        } else {
            ta1.clear();
            ta1.appendText(Double.toString(result));
        }
    }
}
