/**
 * The Silver.java class is responsible for providing the fee associated with being a Silver member,
 * through the getFee() method.
 * The class is immutable because the only field that exists is the fee field which is a constant.
 */

public class Silver extends Tier {
    private final int fee = 20;

    @Override
    public void changeTier(Customer c){
        double balance = c.getBalance();
        if( balance < 10000){
            c.setTier(new Silver());
        }else if(balance < 20000){
            c.setTier(new Gold());
        }else{
            c.setTier(new Platinum());
        }
    }

    @Override
    public int getFee(){
        return fee;
    }

    @Override
    public String toString(){
        return "Silver";
    }
}
