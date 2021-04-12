package siit.db;

import org.h2.jdbcx.JdbcDataSource;
import siit.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    //add phone and email to Customer class
    //use insert instead of select (example in testdata.sql)
    //set values on prepared statement

    public void addCustomer(Customer customer) {
        JdbcDataSource dataSource = getDataSource();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO CUSTOMERS (NAME, PHONE, EMAIL, BIRTHDAY)" +
                                " VALUES (?,?,?,?)")
        ) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getEmail());
            stmt.setDate(4, Date.valueOf(customer.getBirthDate()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomersByName(String nameSearch) {
        List<Customer> customers = new ArrayList<>();
        JdbcDataSource dataSource = getDataSource();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT id,name,phone,email from customers where name LIKE ?")
        ) {

            stmt.setString(1, nameSearch);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    customers.add(extractCustomer(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        JdbcDataSource dataSource = getDataSource();

        try (
                Connection connection = dataSource.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id,name, phone, email from customers")
        ) {

            while (rs.next()) {
                customers.add(extractCustomer(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Customer extractCustomer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");

        return new Customer(id, name, phone, email);
    }

    private JdbcDataSource getDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:file:./db/store;AUTO_SERVER=true");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
