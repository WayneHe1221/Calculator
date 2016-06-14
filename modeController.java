/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import java.math.BigInteger;
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

    BigInteger numberical;
    String operator = "";
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

    //**********************************for standardMode************************************
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

    //**********************************for programDesignMode************************************
    @FXML
    void turnToOct() {
        Button set[] = {textNum0, textNum00, textNumFF, textNum1, textNum2, textNum3, textNum4, textNum5,
            textNum6, textNum7, textNum8, textNum9, textNumA, textNumB, textNumC, textNumD, textNumE, textNumF};
        for (int i = 0; i < 18; i++) {
            set[i].setDisable(false);
        }
        for (int i = 10; i < 18; i++) {
            set[i].setDisable(true);
        }
        textNumFF.setDisable(true);
        ta2.setText("0");
        radixChange();
    }

    @FXML
    void turnToDec() {
        Button set[] = {textNum0, textNum00, textNumFF, textNum1, textNum2, textNum3, textNum4, textNum5,
            textNum6, textNum7, textNum8, textNum9, textNumA, textNumB, textNumC, textNumD, textNumE, textNumF};
        for (int i = 0; i < 18; i++) {
            set[i].setDisable(false);
        }
        for (int i = 12; i < 18; i++) {
            set[i].setDisable(true);
        }
        textNumFF.setDisable(true);
        ta2.setText("0");
        radixChange();
    }

    @FXML
    void turnToHex() {
        Button set[] = {textNum0, textNum00, textNumFF, textNum1, textNum2, textNum3, textNum4, textNum5,
            textNum6, textNum7, textNum8, textNum9, textNumA, textNumB, textNumC, textNumD, textNumE, textNumF};
        for (int i = 0; i < 18; i++) {
            set[i].setDisable(false);
        }
        ta2.setText("0x0");
        radixChange();
    }

    @FXML
    void clear() {
        ta2.clear();
        if (oct.isSelected() || dec.isSelected()) {
            ta2.appendText("0");
        } else if (hex.isSelected()) {
            ta2.appendText("0x0");
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void delete() {
        String store = ta2.getText();
        String finish = new String();
        if (oct.isSelected() || dec.isSelected()) {
            finish = store.substring(0, store.length() - 1);
            if (finish.isEmpty()) {
                finish = "0";
            }
        } else if (hex.isSelected()) {
            finish = store.substring(0, store.length() - 1);
            if ("0x".equals(finish)) {
                finish = "0x0";
            }
        }
        ta2.clear();
        ta2.appendText(finish);
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtnFF() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0xFF");
                } else {
                    ta2.appendText("FF");
                }
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0xFF");
            } else {
                ta2.appendText("FF");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn00() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0");
                } else if (Integer.parseInt(store) == 0) {
                    ta2.clear();
                    ta2.setText("0");
                } else {
                    ta2.appendText("00");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0");
                } else if (Integer.parseInt(store) == 0) {
                    ta2.clear();
                    ta2.setText("0");
                } else {
                    ta2.appendText("00");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x0");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x0");
                } else {
                    ta2.appendText("00");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if (ta2.getText().isEmpty()) {
                ta2.appendText("0");
            } else if (Integer.parseInt(store) == 0) {
                ta2.clear();
                ta2.setText("0");
            } else {
                ta2.appendText("00");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x0");
            } else {
                ta2.appendText("00");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn0() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0");
                } else {
                    ta2.appendText("0");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0");
                } else {
                    ta2.appendText("0");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x0");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x0");
                } else {
                    ta2.appendText("0");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if (ta2.getText().isEmpty()) {
                ta2.appendText("0");
            } else if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("0");
            } else {
                ta2.appendText("0");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x0");
            } else {
                ta2.appendText("0");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn1() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("1");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("1");
                } else {
                    ta2.appendText("1");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("1");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("1");
                } else {
                    ta2.appendText("1");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x1");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x1");
                } else {
                    ta2.appendText("1");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("1");
            } else {
                ta2.appendText("1");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x1");
            } else {
                ta2.appendText("1");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn2() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("2");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("2");
                } else {
                    ta2.appendText("2");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("2");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("2");
                } else {
                    ta2.appendText("2");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x2");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x2");
                } else {
                    ta2.appendText("2");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("2");
            } else {
                ta2.appendText("2");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x2");
            } else {
                ta2.appendText("2");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn3() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("3");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("3");
                } else {
                    ta2.appendText("3");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("3");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("3");
                } else {
                    ta2.appendText("3");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x3");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x3");
                } else {
                    ta2.appendText("3");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("3");
            } else {
                ta2.appendText("3");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x3");
            } else {
                ta2.appendText("3");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn4() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("4");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("4");
                } else {
                    ta2.appendText("4");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("4");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("4");
                } else {
                    ta2.appendText("4");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x4");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x4");
                } else {
                    ta2.appendText("4");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("4");
            } else {
                ta2.appendText("4");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x4");
            } else {
                ta2.appendText("4");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn5() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("5");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("5");
                } else {
                    ta2.appendText("5");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("5");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("5");
                } else {
                    ta2.appendText("5");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x5");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x5");
                } else {
                    ta2.appendText("5");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("5");
            } else {
                ta2.appendText("5");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x5");
            } else {
                ta2.appendText("5");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn6() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("6");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("6");
                } else {
                    ta2.appendText("6");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("6");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("6");
                } else {
                    ta2.appendText("6");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x6");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x6");
                } else {
                    ta2.appendText("6");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("6");
            } else {
                ta2.appendText("6");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x6");
            } else {
                ta2.appendText("6");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn7() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (oct.isSelected()) {
                if (store.equals(numberical.toString(8))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("7");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("7");
                } else {
                    ta2.appendText("7");
                }
            } else if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("7");
                } else if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("7");
                } else {
                    ta2.appendText("7");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x7");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x7");
                } else {
                    ta2.appendText("7");
                }
            }
        } else if (oct.isSelected() || dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("7");
            } else {
                ta2.appendText("7");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x7");
            } else {
                ta2.appendText("7");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn8() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("8");
                } else {
                    ta2.appendText("8");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x8");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x8");
                } else {
                    ta2.appendText("8");
                }
            }
        } else if (dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("8");
            } else {
                ta2.appendText("8");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x8");
            } else {
                ta2.appendText("8");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtn9() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (dec.isSelected()) {
                if (store.equals(numberical.toString())) {
                    ta2.clear();
                }
                if ("0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("9");
                } else {
                    ta2.appendText("9");
                }
            } else if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0x9");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0x9");
                } else {
                    ta2.appendText("9");
                }
            }
        } else if (dec.isSelected()) {
            if ("0".equals(store)) {
                ta2.clear();
                ta2.appendText("9");
            } else {
                ta2.appendText("9");
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0x9");
            } else {
                ta2.appendText("9");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtnA() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0xA");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0xA");
                } else {
                    ta2.appendText("A");
                }
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0xA");
            } else {
                ta2.appendText("A");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtnB() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0xB");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0xB");
                } else {
                    ta2.appendText("B");
                }
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0xB");
            } else {
                ta2.appendText("B");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtnC() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0xC");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0xC");
                } else {
                    ta2.appendText("C");
                }
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0xC");
            } else {
                ta2.appendText("C");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtnD() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0xD");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0xD");
                } else {
                    ta2.appendText("D");
                }
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0xD");
            } else {
                ta2.appendText("D");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtnE() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0xE");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0xE");
                } else {
                    ta2.appendText("E");
                }
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0xE");
            } else {
                ta2.appendText("E");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void textbtnF() {
        String store = ta2.getText();
        if (operator.isEmpty() == false) {
            if (hex.isSelected()) {
                if (store.equals("0x" + numberical.toString(16))) {
                    ta2.clear();
                }
                if (ta2.getText().isEmpty()) {
                    ta2.appendText("0xF");
                } else if ("0x0".equals(store)) {
                    ta2.clear();
                    ta2.appendText("0xF");
                } else {
                    ta2.appendText("F");
                }
            }
        } else if (hex.isSelected()) {
            if ("0x0".equals(store)) {
                ta2.clear();
                ta2.appendText("0xF");
            } else {
                ta2.appendText("F");
            }
        }
        binaryLabel(ta2.getText());
    }

    @FXML
    void divide() {
        operator = "÷";
        numberical = getBinaryLabel();
    }

    @FXML
    void multiply() {
        operator = "×";
        numberical = getBinaryLabel();
    }

    @FXML
    void plus() {
        operator = "+";
        numberical = getBinaryLabel();
    }

    @FXML
    void minus() {
        operator = "-";
        numberical = getBinaryLabel();
    }

    @FXML
    void equal() {
        BigInteger store, result;
        if (operator == "÷") {
            if (oct.isSelected()) {
                store = new BigInteger(ta2.getText(), 8);
                result = numberical.divide(store);
                ta2.setText(result.toString(8));
            } else if (hex.isSelected()) {
                store = new BigInteger(ta2.getText().substring(2), 16);
                result = numberical.divide(store);
                ta2.setText("0x" + result.toString(16));
            } else {
                store = new BigInteger(ta2.getText());
                result = numberical.divide(store);
                ta2.setText(result.toString());
            }
        } else if (operator == "×") {
            if (oct.isSelected()) {
                store = new BigInteger(ta2.getText(), 8);
                result = numberical.multiply(store);
                ta2.setText(result.toString(8));
            } else if (hex.isSelected()) {
                store = new BigInteger(ta2.getText().substring(2), 16);
                result = numberical.multiply(store);
                ta2.setText("0x" + result.toString(16));
            } else {
                store = new BigInteger(ta2.getText());
                result = numberical.multiply(store);
                ta2.setText(result.toString());
            }
        } else if (operator == "+") {
            if (oct.isSelected()) {
                store = new BigInteger(ta2.getText(), 8);
                result = numberical.add(store);
                ta2.setText(result.toString(8));
            } else if (hex.isSelected()) {
                store = new BigInteger(ta2.getText().substring(2), 16);
                result = numberical.add(store);
                ta2.setText("0x" + result.toString(16));
            } else {
                store = new BigInteger(ta2.getText());
                result = numberical.add(store);
                ta2.setText(result.toString());
            }
        } else if (operator == "-") {
            if (oct.isSelected()) {
                store = new BigInteger(ta2.getText(), 8);
                result = numberical.subtract(store);
                ta2.setText(result.toString(8));
            } else if (hex.isSelected()) {
                store = new BigInteger(ta2.getText().substring(2), 16);
                result = numberical.subtract(store);
                ta2.setText("0x" + result.toString(16));
            } else {
                store = new BigInteger(ta2.getText());
                result = numberical.subtract(store);
                ta2.setText(result.toString());
            }
        } else {
            ta2.appendText("");
        }
        binaryLabel(ta2.getText());
        operator = "";
    }

    //**********************************for exchangeRateMode************************************
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
        if (erInput.getText().isEmpty() || erInput.getText() == "0") {
            hint.setText("Please input number!!");
        } else {
            input = Double.parseDouble(erInput.getText());
            //hint.setText("Please only input number!!");
            String currencyInput = "USD" + (String) cbInput.getValue();
            String currencyOutput = "USD" + (String) cbOutput.getValue();
            rate.setCurrency(currencyInput);
            input = input / Double.parseDouble(rate.getRate());
            rate.setCurrency(currencyOutput);
            input = input * Double.parseDouble(rate.getRate());
            hint.setText("Exchange successful.");
            erOutput.setText(String.valueOf(input));

        }
    }

    //**********************************for switchMode************************************
    @FXML
    private void switchSMode(ActionEvent event) throws IOException {
        //load up OTHER FXML document
        Parent root = FXMLLoader.load(getClass().getResource("standardMode.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
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

    //**********************************method support all calculator************************************
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

    // turn the binary label into biginteger
    private BigInteger getBinaryLabel() {
        BigInteger decimal;
        String binary;
        StringBuilder store = new StringBuilder();
        Label lbArray[] = {lb0, lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lb10, lb11, lb12, lb13, lb14, lb15, lb16,
            lb17, lb18, lb19, lb20, lb21, lb22, lb23, lb24, lb25, lb26, lb27, lb28, lb29, lb30, lb31, lb32,
            lb33, lb34, lb35, lb36, lb37, lb38, lb39, lb40, lb41, lb42, lb43, lb44, lb45, lb46, lb47, lb48,
            lb49, lb50, lb51, lb52, lb53, lb54, lb55, lb56, lb57, lb58, lb59, lb60, lb61, lb62, lb63};
        for (int i = 0; i < 64; i++) {
            store.append(lbArray[i].getText());
        }
        binary = store.reverse().toString();
        decimal = new BigInteger(binary.trim(), 2);
        return decimal;
    }

    // textarea change into binary and set on label
    private void binaryLabel(String text) {
        BigInteger decimal = null;
        String binary = new String();
        StringBuilder reverse;
        Label lbArray[] = {lb0, lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9, lb10, lb11, lb12, lb13, lb14, lb15, lb16,
            lb17, lb18, lb19, lb20, lb21, lb22, lb23, lb24, lb25, lb26, lb27, lb28, lb29, lb30, lb31, lb32,
            lb33, lb34, lb35, lb36, lb37, lb38, lb39, lb40, lb41, lb42, lb43, lb44, lb45, lb46, lb47, lb48,
            lb49, lb50, lb51, lb52, lb53, lb54, lb55, lb56, lb57, lb58, lb59, lb60, lb61, lb62, lb63};
        if (oct.isSelected()) {
            decimal = new BigInteger(text, 8);
        } else if (dec.isSelected()) {
            decimal = new BigInteger(text);
        } else if (hex.isSelected()) {
            text = text.substring(2);
            decimal = new BigInteger(text, 16);
        }
        binary = decimal.toString(2);
        reverse = new StringBuilder(binary).reverse();
        binary = reverse.toString();
        for (int i = 0; i < 64; i++) {
            lbArray[i].setText("0");
        }
        for (int i = 0; i < binary.length(); i++) {
            String io = binary.substring(i, i + 1);
            lbArray[i].setText(io);
        }
    }

    // deal with oct, dec & hex changing 
    private void radixChange() {
        if (dec.isSelected()) {
            ta2.setText(getBinaryLabel().toString());
            binaryLabel(getBinaryLabel().toString());
        } else if (oct.isSelected()) {
            ta2.setText(getBinaryLabel().toString(8));
            binaryLabel(getBinaryLabel().toString(8));
        } else if (hex.isSelected()) {
            ta2.setText("0x" + getBinaryLabel().toString(16));
            binaryLabel("0x" + getBinaryLabel().toString(16));
        }
    }

}
