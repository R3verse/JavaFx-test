/**
 * Created by Maxjensendk on 27-02-2016.
 */
public class PortfolioController {

    private double balance;
    private String name;

    public PortfolioController()
    {
        this.balance = 0.00;
        this.name = "";

    }

    public double getBalance()
    {
        return balance;
    }
    public void setBalance(double balance)
    {
         this.balance = balance;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
       this.name = name;
    }

}
