package design.voight;


import javafx.fxml.FXML;
import javafx.scene.control.*;

public class InterfaceController {


    @FXML
    private Label quitLabel;

    @FXML
    private TextField TextField_InputBox;

    String inputText;

    @FXML
    public void onQuitButtonClick() {
        quitLabel.setText("saving info");
        inputText = TextField_InputBox.getText();
        System.out.println(inputText);
        //TODO extend project system into the UI, fully functioning export of entered data.
    }
}
