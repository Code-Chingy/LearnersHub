package com.techupstudio.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static com.techupstudio.Application.StageManager.*;

public class HomeController {

    @FXML
    public void gotoLogin(ActionEvent actionEvent) throws IOException {
        changePrimaryScene("StoryLane : Login", "activity_login");
    }

    @FXML
    public void gotoSignup(ActionEvent actionEvent) throws IOException {
        if (!getStageStack().contains(getStage("StoryLane : Signup","activity_signup"))){
            changePrimaryScene("StoryLane : Signup", "activity_signup");
        }
    }

}
