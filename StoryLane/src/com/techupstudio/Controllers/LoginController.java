package com.techupstudio.Controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.techupstudio.Application.StageManager.*;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    public void loginUser(ActionEvent actionEvent) throws IOException {
        if (username.getText().equals("apple") && password.getText().equals("admin")){
            changePrimaryScene("StoryLane : Main Activity", "activity_main");
        }
    }

    @FXML
    private void gotoSignup(ActionEvent actionEvent) throws IOException {
        addStageIfNotExist(getStage("StoryLane : SignUp", "activity_signup"));
    }

}
