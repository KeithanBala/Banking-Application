import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.*;

public class AddCustomerController {
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Button submitButton;

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.MANAGER_PAGE);
    }

    @FXML
    public void handleSubmitButton(ActionEvent actionEvent) {
        String username = new String(usernameTextField.getText().toLowerCase());
        String fileName = username + ".txt";
        File parentDir = new File("data");
        File file = new File(parentDir, fileName);
        Window owner = submitButton.getScene().getWindow();

        //Displays an error window if username is not provided
        if(usernameTextField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please enter a username");
            return;
        }

        //Displays an error window if password is not provided
        if(passwordTextField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please enter a password");
            return;
        }

        //Checks if file already exists
        if(file.exists() && !file.isDirectory()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Customer with that username already exists");
            return;
        }else{
            try {
                PrintWriter writer = new PrintWriter(file, "UTF-8");
                writer.println(passwordTextField.getText());
                writer.println(100.00);
                writer.println("silver");
                writer.close();
            }catch(Exception e){
                System.out.print(e);
            }
        }
    }
}
