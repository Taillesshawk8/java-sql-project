import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ProduceReports {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the customer's ID:");
        String customerID = myObj.nextLine();
        System.out.println("--------------------------------------------------------------------");
        customerAddress(customerID);
        transactionHistory(customerID);
        customerTotalBalance(customerID);
    }

    public static void customerAddress(String customerID){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlproject", "root", "Gl@d0s");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select Address \n" +
                            "from customers\n" +
                            "where CustomerID=1;");
            while (resultSet.next()) {
                System.out.println("This customer's address is: "+resultSet.getString("Address"));
                System.out.println("--------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void customerTotalBalance(String customerID){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlproject", "root", "Gl@d0s");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select sum(AccountBalance) as TotalBalance \n" +
                    "from account\n" +
                    "where CustomerID="+customerID+";");
            while (resultSet.next()) {
                System.out.println("TotalBalance: "+resultSet.getString("TotalBalance"));
                System.out.println("--------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void transactionHistory(String customerID){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlproject", "root", "Gl@d0s");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select account.AccountNo, transactions.TransactionDate,transactions.TransactionAmount,transactions.TransactionType \n" +
                    "from account \n" +
                    "left join transactions on account.AccountNo = transactions.AccountNo\n" +
                    "where account.CustomerID="+customerID+" and account.AccountType='CHECKING';");
            System.out.println("Customer's Transaction History:");
            System.out.println("--------------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.println("AccountNo: "+resultSet.getString("account.AccountNo"));
                System.out.println("TransactionDate: "+resultSet.getString("transactions.TransactionDate"));
                System.out.println("TransactionAmount: "+resultSet.getString("transactions.TransactionAmount"));
                System.out.println("TransactionType: "+resultSet.getString("transactions.TransactionType"));
                System.out.println("--------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}