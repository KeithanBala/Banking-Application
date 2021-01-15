import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;


/**
 * This class represents the Customer object. It includes multiple mutable fields such as the
 * tier, and balance, but also includes immutable fields such as the file, username, and the password.
 *
 * AF(c) = customer such that customer has an immutable username, file, password, and a mutable tier and balance.
 *
 * Rep Invariant: Customer such that the username and password is not null or empty, file has to exist, with proper file outputs
 */
public class Customer {
    private Tier myTier;
    private double balance;
    private String password;
    private String username;
    private File file;

    /**
     * Effects: constructs the customer object
     * Requires: The customer file with the valid information stored, and the customer's username
     * Modifies: The customer fields: Tier, balance, password, username, file
     */
    public Customer(File file, String name){
        this.file = file;
        BufferedReader reader;
        String tier;
        username = name;
        try {
            reader = new BufferedReader( new FileReader(file));
            password = reader.readLine();
            balance = Double.parseDouble(reader.readLine());

            tier = reader.readLine();
            switch(tier.toLowerCase()){
                case "silver":
                    myTier = new Silver();
                    break;
                case "gold":
                    myTier = new Gold();
                    break;
                case "platinum":
                    myTier = new Platinum();
                    break;
                default:
                    System.out.println("Customer has invalid tier");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Customer file read error");
        }
    }

    /**
     * Effects: returns the customer balance
     */
    public double getBalance(){
        return balance;
    }

    /**
     * Effects: changes customer balance
     * Requires: a new balance
     * Modifies: customer's existing balance
     *
     */
    public void setBalance(double value){
        balance = value;
    }

    /**
     * Effects: changes customer tier.
     * Requires: a tier object.
     * Modifies: customer's tier
     *
     */
    public void setTier(Tier t) {
        myTier = t;
    }

    /**
     * Effects: Returns the customer tier.
     *
     */
    public Tier getTier(){
        return myTier;
    }

    /**
     * Effects: Returns the customer username
     *
     */
    public String getUsername() {
        return username;
    }

    /**
     * Effects: Changes all text file fields
     * Modifies: The file + balance + tier.
     */
    public void updateFile(){
        try {
            myTier.changeTier(this); //Updates tier based on current balance
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println(password); //First line will be password
            writer.println(balance); //Second line initial balance
            writer.println(myTier.toString()); //Third line initial tier
            writer.close();
        }catch(Exception e){
            System.out.println("Error updating file");
        }
    }

    /**
     * Effects: returns true if the customer object is valid
     * Requires: A customer object
     *
     */
    public Boolean repOK(Customer c){
        BufferedReader reader;
        String name = c.getUsername();
        Tier tier = c.getTier();
        if(name.isEmpty() || name.equals("") || !tier.equals("Silver") || !tier.equals("Gold") || !tier.equals("Platinum"))
        {
            return false;
        }else
            {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "myTier=" + myTier +
                ", balance=" + balance +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", file=" + file +
                '}';
    }
}
