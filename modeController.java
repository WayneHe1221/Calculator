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
import javafx.event.EventHandler;
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

    @FXML
    Pane pane;
    @FXML
    Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    @FXML
    Button mark1, mark2, mark3, mark4, mark5, mark6, mark7, mark8, mark9, mark10, mark11;
    @FXML
    Button mode1, mode2, mode3;
    @FXML
    TextArea ta1;

    @FXML
    void textEvent0() {
        ta1.appendText("0");
    }

    @FXML
    void textEvent1() {
        ta1.appendText("1");
    }

    @FXML
    void textEvent2() {
        ta1.appendText("2");
    }

    @FXML
    void textEvent3() {
        ta1.appendText("3");
    }

    @FXML
    void textEvent4() {
        ta1.appendText("4");
    }

    @FXML
    void textEvent5() {
        ta1.appendText("5");
    }

    @FXML
    void textEvent6() {
        ta1.appendText("6");
    }

    @FXML
    void textEvent7() {
        ta1.appendText("7");
    }

    @FXML
    void textEvent8() {
        ta1.appendText("8");
    }

    @FXML
    void textEvent9() {
        ta1.appendText("9");
    }
    // action method of cancel
    @FXML
    void markEvent1() {
        ta1.clear();
    }
    // action method to inverse mark
    @FXML
    void markEvent2() {
        String store = ta1.getText();
        double num;
        int num1;
        if (Double.parseDouble(store) % 1 == 0) {
            num = Double.parseDouble(store) * -1;
            num1 = (int) num;
            ta1.clear();
            ta1.appendText(Integer.toString(num1));
        } else if (Double.parseDouble(store) % 1 != 0) {
            num = Double.parseDouble(store) * -1;
            ta1.clear();
            ta1.appendText(Double.toString(num));
        } else {
            ta1.appendText("");
        }
    }
    // action method for percent
    @FXML
    void markEvent3() {
        String store = ta1.getText();
        char[] set = store.toCharArray();
        String store2;
        int count = 0;
        for (int i = 0; i < store.length(); i++) {
            if (set[i] == '÷' || set[i] == '×' || set[i] == '-' || set[i] == '+') {
                store2 = ta1.getText(i + 1, store.length());
                char[] inStore2 = store2.toCharArray();
                count = 0;
                for (int j = 0; j < store2.length(); j++) {
                    if (inStore2[j] == '%' || inStore2[j] == '.') {
                        ta1.appendText("");
                        count++;
                    } else if (j == store2.length() - 1 && count == 0) {
                        ta1.appendText("%");
                        count++;
                    } else {
                        ta1.appendText("");
                    }
                }
            } else if (set[i] == '%' || set[i] == '.') {
                ta1.appendText("");
                count++;
            } else if (i == store.length() - 1 && count == 0) {
                ta1.appendText("%");
            } else {
                ta1.appendText("");
            }
        }
    }
    // action method to delete
    @FXML
    void markEvent4() {
        String store = ta1.getText();
        char[] set = store.toCharArray();
        store = String.valueOf(set, 0, store.length() - 1);
        ta1.clear();
        ta1.appendText(store);
    }
    // action method of divide
    @FXML
    void markEvent5() {
        String store = ta1.getText();
        char[] set = store.toCharArray();
        String num1, num2;
        double result = 0;
        for (int i = 0; i < store.length(); i++) {
            if (set[i] == '÷') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = divisionResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("÷");
                break;
            } else if (set[i] == '×') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = multiplyResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("÷");
                break;
            } else if (set[i] == '-') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = minusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("÷");
                break;
            } else if (set[i] == '+') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = plusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("÷");
                break;
            } else if (i == store.length() - 1) {
                ta1.appendText("÷");
            } else {
                ta1.appendText("");
            }
        }
    }
    // action method of multiply
    @FXML
    void markEvent6() {
        String store = ta1.getText();
        char[] set = store.toCharArray();
        String num1, num2;
        double result = 0;
        for (int i = 0; i < store.length(); i++) {
            if (set[i] == '÷') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = divisionResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("×");
                break;
            } else if (set[i] == '×') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = multiplyResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("×");
                break;
            } else if (set[i] == '-') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = minusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("×");
                break;
            } else if (set[i] == '+') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = plusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("×");
                break;
            } else if (i == store.length() - 1) {
                ta1.appendText("×");
            } else {
                ta1.appendText("");
            }
        }
    }
    // action method of minus
    @FXML
    void markEvent7() {
        String store = ta1.getText();
        char[] set = store.toCharArray();
        String num1, num2;
        double result = 0;
        for (int i = 0; i < store.length(); i++) {
            if (set[i] == '÷') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = divisionResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("-");
                break;
            } else if (set[i] == '×') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = multiplyResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("-");
                break;
            } else if (set[i] == '-') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = minusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("-");
                break;
            } else if (set[i] == '+') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = plusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("-");
                break;
            } else if (i == store.length() - 1) {
                ta1.appendText("-");
            } else {
                ta1.appendText("");
            }
        }
    }
    // action method of plus
    @FXML
    void markEvent8() {
        String store = ta1.getText();
        char[] set = store.toCharArray();
        String num1, num2;
        double result = 0;
        for (int i = 0; i < store.length(); i++) {
            if (set[i] == '÷') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = divisionResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("+");
                break;
            } else if (set[i] == '×') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = multiplyResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("+");
                break;
            } else if (set[i] == '-') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = minusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("+");
                break;
            } else if (set[i] == '+') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = plusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                ta1.appendText("+");
                break;
            } else if (i == store.length() - 1) {
                ta1.appendText("+");
            } else {
                ta1.appendText("");
            }
        }
    }
    // action method of '='
    @FXML
    void markEvent9() {
        String store = ta1.getText();
        char[] set = store.toCharArray();
        String num1, num2;
        double result = 0;
        for (int i = 0; i < store.length(); i++) {
            if (set[i] == '÷') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = divisionResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                break;
            } else if (set[i] == '×') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = multiplyResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                break;
            } else if (set[i] == '-') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = minusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                break;
            } else if (set[i] == '+') {
                num1 = ta1.getText(0, i);
                num2 = ta1.getText(i + 1, store.length());
                result = plusResult(num1, num2, result, i, store.length(), ta1);
                getResult(result, ta1);
                break;
            } else {
                ta1.appendText("");
            }
        }
    }
    // action method for text '.'
    @FXML
    void markEvent10() {
        String store = ta1.getText();
        char[] set = store.toCharArray();
        String store2;
        int count = 0;
        for (int i = 0; i < store.length(); i++) {
            if (set[i] == '÷' || set[i] == '×' || set[i] == '-' || set[i] == '+') {
                store2 = ta1.getText(i + 1, store.length());
                char[] inStore2 = store2.toCharArray();
                count = 0;
                for (int j = 0; j < store2.length(); j++) {
                    if (inStore2[j] == '.' || inStore2[j] == '%') {
                        ta1.appendText("");
                        count++;
                    } else if (j == store2.length() - 1 && count == 0) {
                        ta1.appendText(".");
                        count++;
                    } else {
                        ta1.appendText("");
                    }
                }
            } else if (set[i] == '.' || set[i] == '%') {
                ta1.appendText("");
                count++;
            } else if (i == store.length() - 1 && count == 0) {
                ta1.appendText(".");
            } else {
                ta1.appendText("");
            }
        }
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
        
        //load up OTHER FXML document
        Parent root = FXMLLoader.load(getClass().getResource("erMode.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public modeController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    // judge the num whether it got a % mark or not
    private boolean checkPercentMark(String num) {
        char[] checkMark = num.toCharArray();
        if (checkMark[num.length() - 1] == '%') {
            return true;
        } else {
            return false;
        }
    }

    // math of multiply
    private double multiplyResult(String num1, String num2, Double result, int mark, int length, TextArea ta1) {
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
    private double divisionResult(String num1, String num2, Double result, int mark, int length, TextArea ta1) {
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
    private double plusResult(String num1, String num2, Double result, int mark, int length, TextArea ta1) {
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
    private double minusResult(String num1, String num2, Double result, int mark, int length, TextArea ta1) {
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
    private void getResult(double result, TextArea ta1) {
        if (result % 1 == 0) {
            ta1.clear();
            ta1.appendText(Integer.toString((int) result));
        } else {
            ta1.clear();
            ta1.appendText(Double.toString(result));
        }
    }
}
