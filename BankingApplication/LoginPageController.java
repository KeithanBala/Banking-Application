import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Controller class for the first vista.
 */
public class LoginPageController {

    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Button submitButton;
/*
    public LoginPageController(){

    }
*/
    @FXML
    protected void handleSubmitButton(ActionEvent actionEvent) throws IOException {
        Window owner = submitButton.getScene().getWindow();
        Stage currentStage = (Stage) owner;

        //Displays an error window if username is not provided
        if(usernameTextField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error", "Please enter your username");
            return;
        }

        //Displays an error window if password is not provided
        if(passwordTextField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error", "Please enter your password");
            return;
        }

        //Takes the username and creates a path to the <username>.txt file in the /data/ folder
        //The actual existence of this abstract file is checked in the if statement below
        String username = new String(usernameTextField.getText().toLowerCase());
        String fileName = username + ".txt";
        File parentDir = new File("data");
        File file = new File(parentDir, fileName);

        //Check if Manager login > Leads to Manager page
        if(usernameTextField.getText().toLowerCase().equals("admin")){
            if(passwordTextField.getText().toLowerCase().equals("password")) {
                VistaNavigator.loadVista(VistaNavigator.MANAGER_PAGE);
            }else{
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error", "Invalid username or password");
            }
        }else if(file.exists() && !file.isDirectory()){
            //Customer exists, check if password is correct
            BufferedReader reader = new BufferedReader( new FileReader(file));
            String password = reader.readLine();
            if(password.equals(passwordTextField.getText())){
                CustomerPageController.setCustomer(new Customer(file,username));
                VistaNavigator.loadVista(VistaNavigator.CUSTOMER_PAGE);
                return;
            }else{
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error", "Invalid username or password");
                return;
            }
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Login Error", "Invalid username or password");
        }
    }
}