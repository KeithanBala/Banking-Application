import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller class for the first vista.
 */
public class ManagerPageController {

    @FXML
    public void handleAddCustomerButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.ADD_CUSTOMER);
    }

    @FXML
    public void handleDeleteCustomerButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.DELETE_CUSTOMER);
    }

    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.LOGIN_PAGE);
    }
}