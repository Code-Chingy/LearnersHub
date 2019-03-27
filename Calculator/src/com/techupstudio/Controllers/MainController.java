package com.techupstudio.Controllers;

import com.techupstudio.Application.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class MainController {

    String OPERATION = "";

    Calculator calculator = new Calculator();

    @FXML
    Label display, result;

    public void processEvent(ActionEvent actionEvent){
        Button pressed = (Button) actionEvent.getSource();
        String value = pressed.getText();
        OPERATION += pressed.getText();
        if (value.equals("C")){
            result.setText("");
            display.setText("");
        }
        if (value.equals("=")){
            result.setText("result : "+calculator.calculate(OPERATION));
            OPERATION = "";
        }
        else{
            display.setText(OPERATION);
        }

    }


}
