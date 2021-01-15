import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.File;

public class DeleteCustomerController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private Button submitButton;

    @FXML
    public void handleSubmitButton(ActionEvent actionEvent) {
        Window owner = submitButton.getScene().getWindow();
        String username = new String(usernameTextField.getText().toLowerCase());
        String fileName = username + ".txt";
        File parentDir = new File("data");
        File file = new File(parentDir, fileName);

        //Displays an error window if username is not provided
        if(usernameTextField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please enter a username");
            return;
        }

        //Checks if file already exists
        if(file.exists() && !file.isDirectory()) {
            //Delete
            if(file.delete()){
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Success", "Customer with the username '"+username+"' has been deleted");
                usernameTextField.clear();
                return;
            }
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Customer with the username '"+username+"' does not exist");
            usernameTextField.clear();
            return;
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.MANAGER_PAGE);
    }

}
