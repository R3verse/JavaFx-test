import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;

/**
 * Created by Maxjensendk on 27-02-2016.
 */
public class Portfolio {

    private int balance;
    private PieChart pieChart;

    public Portfolio()
    {
        this.balance = 0;

    }


    public Portfolio(int balance)
    {
        this.balance = balance;


    }

    public Double showBalance()
    {
        PortfolioController portfolioController = new PortfolioController();
        return portfolioController.getBalance();

    }


}
