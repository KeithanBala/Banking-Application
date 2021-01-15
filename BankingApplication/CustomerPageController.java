import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class CustomerPageController {
    @FXML
    private Label header;
    @FXML
    private Label balanceLabel;
    @FXML
    private TextField withdrawTextField;
    @FXML
    private TextField depositTextField;
    @FXML
    private TextField purchaseTextField;
    private static Customer c;

    //Declares the currently logged in customer, in order to get all information.
    public static void setCustomer(Customer x){
        c = x;
    }

    //Displays a welcome message with username + current balance.
    @FXML
    public void initialize(){
        try {
            header.setText("Welcome " + c.getUsername().substring(0,1).toUpperCase() + c.getUsername().substring(1) + "!");
        }catch (Exception e){

        }
        balanceLabel.setText("Balance: $"+c.getBalance());
    }

    @FXML
    public void handleWithdrawButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.WITHDRAW_PAGE);
    }

    @FXML
    public void handleDepositButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.DEPOSIT_PAGE);
    }

    @FXML
    public void handlePurchaseButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.PURCHASE_PAGE);
    }

    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.LOGIN_PAGE);
    }

    @FXML
    public void handleWithdrawSubmitButton(ActionEvent actionEvent) {
        Window owner = withdrawTextField.getScene().getWindow();
        String withdraw = withdrawTextField.getText();
        if(withdraw.isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please enter an amount");
        }else if(withdraw.matches("[0-9]+(\\.[0-9]{1,2})?")){
            double value = Double.parseDouble(withdraw);
            if(value > c.getBalance()){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Withdraw amount is greater than balance");
            }else{
                c.setBalance(c.getBalance()-value);
                initialize();
                c.updateFile();
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Success",
                        "An amount of $"+value+" has been withdrawn from your account");
            }
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Amount doesn't match criteria");
        }
    }

    @FXML
    public void handleWithdrawBackButton(ActionEvent actionEvent) {
        VistaNavigator.loadVista(VistaNavigator.CUSTOMER_PAGE);
    }

    @FXML
    public void handleDepositSubmitButton(ActionEvent actionEvent) {
        Window owner = depositTextField.getScene().getWindow();
        String deposit = depositTextField.getText();
        if(deposit.isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please enter an amount");
        }else if(deposit.matches("[0-9]+(\\.[0-9]{1,2})?")){
            double value = Double.parseDouble(deposit);
            c.setBalance(c.getBalance()+value);
            initialize();
            c.updateFile();
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Success", "An amount of $"+value+" has been added to your account");
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Amount doesn't match criteria"); //TODO elaborate
        }
    }

    @FXML
    public void handlePurchaseSubmitButton(ActionEvent actionEvent) {
        Window owner = purchaseTextField.getScene().getWindow();
        String purchase = purchaseTextField.getText();
        if(purchase.isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please enter an amount");
        }else if(purchase.matches("[0-9]+(\\.[0-9]{1,2})?")){
            double value = Double.parseDouble(purchase);
            if(value > c.getBalance()){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Purchase amount is greater than balance");
            }else if(value < 50) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Purchase amount must be $50 or greater");
            }else{
                c.setBalance(c.getBalance()-value-c.getTier().getFee());
                initialize();
                c.updateFile();
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Success", "An amount of $"+value+" has been withdrawn from your account, and a fee of $" +c.getTier().getFee()+" has been applied");
            }
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Amount doesn't match criteria");
        }
    }
}
