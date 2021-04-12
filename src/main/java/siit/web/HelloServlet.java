package siit.web;

import siit.db.CustomerDao;
import siit.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    private final CustomerDao customerDao = new CustomerDao();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        writer.append("<html><table border=1><tr><th>ID</th><th>Name</th></tr>");

        List<Customer> allCustomers = customerDao.getAllCustomers();
        for (Customer customer : allCustomers) {
            writer.append("<tr><td>")
                    .append(String.valueOf(customer.getId()))
                    .append("</td><td>")
                    .append(customer.getName())
                    .append("</td></tr>");
        }

        writer.append("</table></html>");
    }

}
