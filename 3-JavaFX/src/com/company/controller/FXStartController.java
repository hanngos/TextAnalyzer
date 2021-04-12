package com.company.controller;

import com.company.view.FXMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Class, which handles reading data typed in by user in GUI - equivalent of previous Controller class.
 *
 * @author Hanna Gościniak
 * @version 1.0
 * */
public class FXStartController {

    public Button runButton;
    public TextField inputText;

    FXMenuController fxMenuController = new FXMenuController();

    /**
     * Public method displaying alert - dialog window containing information about program*/
    public void infoButtonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Program Info");
        alert.setHeaderText("Look, an information about program");
        alert.setContentText("Type in text and click run! \nname: Text Analyzer \nauthor: Hanna Gościniak");
        alert.showAndWait();
    }

    /**
     * Public method for handling runButton click - changing stage to menu one if string typed in by user
     * isn't blank.
     * @param event type of ActionEvent used to change stage
     * @throws IOException if fxml won't be found in resources
     * */
    public void runButtonClick(ActionEvent event) throws IOException {
        fxMenuController.textInit(inputText.getText());

        if (!inputText.getText().isBlank()) {
            Parent menu = FXMLLoader.load(getClass().getResource("../view/menuWindow.fxml"));
            Stage menuWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            menuWindow.setTitle("My Program");
            menuWindow.setScene(new Scene(menu, 500, 350));
            menuWindow.show();
        }
    }

}