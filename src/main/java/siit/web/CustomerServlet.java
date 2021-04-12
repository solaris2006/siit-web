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

@WebServlet(urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {

    private final CustomerDao customerDao = new CustomerDao();

    @Override
    protected void doGet(   
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        List<Customer> allCustomers = customerDao.getAllCustomers();
        req.setAttribute("customers", allCustomers);

        req.getRequestDispatcher("WEB-INF/customers-list.jsp")
                .forward(req, resp);
    }

}
