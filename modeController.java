/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONException;

/**
 *
 * @author Wayne
 */
public class modeController implements Initializable {
    
    // every mode 
    @FXML
    TextArea ta1, ta2, erInput, erOutput;
    @FXML
    Pane pane;
    @FXML
    Button mode1, mode2, mode3;
    // StandardMode method
    @FXML
    Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    @FXML
    Button mark1, mark2, mark3, mark4, mark5, mark6, mark7, mark8, mark9, mark10;
    // ProgramDesignMode 
    @FXML
    ToggleGroup carry;
    @FXML
    ToggleButton oct, dec, hex;
    @FXML
    Label lb0, lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lb10, lb11, lb12, lb13, lb14, lb15, lb16,
        lb17, lb18, lb19, lb20, lb21, lb22, lb23, lb24, lb25, lb26, lb27, lb28, lb29, lb30, lb31, lb32,
        lb33, lb34, lb35, lb36, lb37, lb38, lb39, lb40, lb41, lb42, lb43, lb44, lb45, lb46, lb47, lb48,
        lb49, lb50, lb51, lb52, lb53, lb54, lb55, lb56, lb57, lb58, lb59, lb60, lb61, lb62, lb63;
    @FXML
    Button textNum0, textNum00, textNumFF, textNum1, textNum2, textNum3, textNum4, textNum5,
            textNum6, textNum7, textNum8, textNum9, textNumA, textNumB, textNumC, textNumD,
            textNumE, textNumF;
    @FXML
    Button logic1, logic2, logic3, logic4, logic5, logic6, logic7, logic8, logic9, logic10;
    @FXML
    Button cancel, delete, turnLeft, turnRight, comp2, comp1, divide, multiply, munus, plus, equal;
    // ExchangeRateMode button
    @FXML
    Button exchange;
    @FXML
    ChoiceBox cbInput, cbOutput;
    @FXML
    Label hint;

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
    
    //@FXML
    

    // choose input currency
    @FXML
    void choiceInput() {
        if (cbInput.isShowing() == false) {
            cbInput.setItems(FXCollections.observableArrayList("TWD", "USD", "EUR"));
            cbInput.setTooltip(new Tooltip("Select currency to input"));
            cbInput.show();
        } else {
            cbInput.show();
        }
    }

    // choose output currency
    @FXML
    void choiceOutput() {
        if (cbOutput.isShowing() == false) {
            cbOutput.setItems(FXCollections.observableArrayList("TWD", "USD", "EUR"));
            cbOutput.setTooltip(new Tooltip("Select currency to input"));
            cbOutput.show();
        } else {
            cbOutput.show();
        }
    }

    @FXML
    void exchangeCurrency() throws JSONException, IOException {
        DealJS rate = new DealJS();
        double input;
        if (erInput.getText() == null || erInput.getText() == "0") {
            hint.setText("Please input number!!");
        } else {
            input = Double.parseDouble(erInput.getText());
            //if (input == 0) {
            //hint.setText("Please only input number!!");
            //} else {
            String currencyInput = "USD" + (String) cbInput.getValue();
            String currencyOutput = "USD" + (String) cbOutput.getValue();
            rate.setCurrency(currencyInput);
            input = input / Double.parseDouble(rate.getRate());
            rate.setCurrency(currencyOutput);
            input = input * Double.parseDouble(rate.getRate());
            hint.setText("Exchange successful.");
            erOutput.setText(String.valueOf(input));
            //}
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
        stage.setTitle("StandardMode");
        stage.show();
    }

    @FXML
    private void switchPDMode(ActionEvent event) throws IOException {
        //load up OTHER FXML document
        Parent root = FXMLLoader.load(getClass().getResource("pdMode.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ProgramDesignMode");
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
        stage.setTitle("ExchangeRateMode");
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
