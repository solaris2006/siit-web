<% siit.db.CustomerDao customerDao = new siit.db.CustomerDao();
   java.util.List<siit.model.Customer> allCustomers = customerDao.getAllCustomers();
   %>

<html>
  <table border=1>
    <tr>
	  <th>ID</th>
	  <th>Name</th>
	</tr>
	<%
	for (siit.model.Customer customer : allCustomers) {
	%>
        <tr>
            <td><%= customer.getId() %></td>
            <td><%= customer.getName() %></td>
        </tr>
    <%
    }
	%>
  </table>
</html>