package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class new_windowController {
    @FXML private Label label;

    @FXML
    private void initialize() {
        label.setText("\nYou insert the keymodule into the radio"
                + "and the static is replaced by a beeping sound"
                + "\nAfter some time a voice is heard"
                + "\n'We have recieved your distress call'"
                + "\n'A rescue ship has been sent your way and will arrive shortly'"
        +"\n'Congratulations on your successful escape!'");
    }
    @FXML
    private void handleExitButton() {
        Platform.exit(); // 退出游戏
    }
}
