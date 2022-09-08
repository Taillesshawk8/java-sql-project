package sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class SqlTest {

    @Test
    public void getAllCustomers_checkNumber_shouldBeTwo() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlproject", "root", "Gl@d0s");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * " +
                "from customers");
        int rsCount = 0;
        while(resultSet.next()) {
            rsCount = rsCount + 1;
        }
        Assertions.assertEquals(2,rsCount);
        System.out.println("Number of customers: "+rsCount);
    }

    @Test
    public void getAccountsForSarah_checkNumber_shouldBeTwo() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlproject", "root", "Gl@d0s");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT * " +
                "FROM account\n" +
                "where CustomerID=1;");
        int rsCount = 0;
        while(resultSet.next()) {
            rsCount = rsCount + 1;
        }
        Assertions.assertEquals(2,rsCount);
        System.out.println("Number of account for Mikasa: "+rsCount);
    }

    @Test
    public void retrieveTransactionsForAccount_checkTotalBalance_shouldBeZero() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlproject", "root", "Gl@d0s");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select sum(TransactionAmount) " +
                "as TotalTransactionBalance " +
                "from transactions\n" +
                "where AccountNo=123456;");
        while (resultSet.next()) {
            Assertions.assertEquals(3000,resultSet.getInt("TotalTransactionBalance"));
            System.out.println("Total balance of Mikasa's Checking Account from transactions: "+resultSet.getString("TotalTransactionBalance"));
        }
    }
}
