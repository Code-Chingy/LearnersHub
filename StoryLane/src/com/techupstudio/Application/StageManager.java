package com.techupstudio.Application;

import com.techupstudio.Utils.Collections.StackList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static com.techupstudio.Res.getLayout;
import static com.techupstudio.Res.getStyleSheet;
import static com.techupstudio.Utils.MyFuncs.range;


public class StageManager {

    public static class StageStack extends StackList<Stage>{
        public boolean contains(String stageTitle){
            for (int i : range(size())){
               if (peekElementAt(i).getTitle().equals(stageTitle)){
                   return true;
               }
            }
            return false;
        }

        public boolean contains(Scene stageScene){
            for (int i : range(size())){
                if (peekElementAt(i).getScene().equals(stageScene)){
                    return true;
                }
            }
            return false;
        }

    }

    private static Stage  PRIMARYSTAGE;
    private static StageStack STAGESTACK = new StageStack();

    public static void setPrimaryStage(String title, String layoutFileName) throws IOException {
        setPrimaryStage(getStage(title, layoutFileName));
    }

    public static void setPrimaryStage(Stage primaryStage) { PRIMARYSTAGE = primaryStage;addStage(primaryStage); }

    public static Stage getPrimaryStage() { return PRIMARYSTAGE; }

    public static void addStage(Stage newStage) {
        newStage.setOnCloseRequest(windowEvent -> closeStage(newStage));
        getStageStack().push(newStage);newStage.show();
    }

    public static void addStageIfNotExist(Stage newStage) {
        if (!(getStageStack().contains(newStage) || getStageStack().contains(newStage.getScene())
                || getStageStack().contains(newStage.getTitle()))){ addStage(newStage);
        }
    }

    public static void addStage(String stageTitle, String layoutFileName) throws IOException {
        addStage(getStage(stageTitle, layoutFileName));
    }

    public static void changePrimaryScene(Stage newStage) { changeStageScene(getPrimaryStage(), newStage); }

    public static void changePrimaryScene(String stageTitle, String layoutFileName) throws IOException {
        changePrimaryScene(getStage(stageTitle, layoutFileName));
    }

    public static void changeStageScene(Stage stage, Stage newStage) {
        stage.setScene(newStage.getScene()); stage.setTitle(newStage.getTitle());
    }

    public static void changeStageScene(Stage stage, String sceneTitle, String layoutFileName) throws IOException {
        changeStageScene(stage, getStage(sceneTitle, layoutFileName));
    }

    public static void closeStage(Stage stage) {
        getStageStack().pop(getStageStack().findFirstIndexOf(stage)).close();
    }

    public static void closeAllStages() { for (int i : range(getStageStack().size())){ getStageStack().pop(i).close(); } }

    public static void placeStageToFront(String stageTitle) {
        for (int i : range(getStageStack().size())){
            if (getStageStack().peekElementAt(i).getTitle().equals(stageTitle)){
                getStageStack().peekElementAt(i).toFront();getStageStack().peekElementAt(i).show();
                addStage(getStageStack().pop(i));
            }
        }
    }

    public static void placeStageToBack(String stageTitle) {
        for (int i : range(getStageStack().size())){
            if (getStageStack().peekElementAt(i).getTitle().equals(stageTitle)){
                getStageStack().peekElementAt(i).toBack();
                getStageStack().insert(getStageStack().size()-1, getStageStack().pop(i));
            }
        }
    }

    public static Stage getStage(String stageTitle, String layoutFileName) throws IOException {
        return getStage(stageTitle, getLayout(layoutFileName));
    }

    public static Stage getStage(String stageTitle, String layoutFileName, String styleSheetName) throws IOException {
        return getStage(stageTitle, getLayout(layoutFileName), getStyleSheet(styleSheetName));
    }

    public static Stage getStage(String stageTitle, URL layoutFileName) throws IOException {
        Parent root = FXMLLoader.load(layoutFileName);
        Stage primaryStage = new Stage();
        primaryStage.setTitle(stageTitle);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return primaryStage;
    }

    public static Stage getStage(String stageTitle, URL layoutFileURL, URL styleSheetURL) throws IOException {
        Parent root = FXMLLoader.load(layoutFileURL);
        Stage primaryStage = new Stage();
        primaryStage.setTitle(stageTitle);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(styleSheetURL.toExternalForm());
        primaryStage.setScene(scene);
        return primaryStage;
    }

    public static StageStack getStageStack(){ return STAGESTACK; }

}
