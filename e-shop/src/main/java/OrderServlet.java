import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShopOrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// Prints formatted representations of objects to a text-output stream
		String[] ids = request.getParameterValues("id"); // Possibly more than
															// one values
		try {

			// STEP 1: Register JDBC driver. This will load the MySQL driver,
			// each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost/OnlinePetiteStore", "root", "");
			// STEP 3: Statements allow us to issue SQL queries to the database
			statement = connect.createStatement();
			// Print html with CSS style sheet
			out.println("<!DOCTYPE html><html><head><link rel='stylesheet' href='css/style.css'/><head><body>");
			out.println("<div id='wrapper'><!-- This wrapper centers the page-->"
					+ "		<header><!-- This is HEADER--><h1>Petite Shoes Online Store</h1></header>"
					+ "		<nav><!-- This is the Navigation bar--><ul><li><a href='index.html'>Home</a></li></ul></nav>"	
					+ "		<div id ='mainsection'><!-- This is the main content bar--><div id='scr'></div>"
					+ "		<div id='main'><!-- This is the  form block--><div id='main-wrapper'>");
			
			out.println("<table border='1' cellpadding='6'>");
			// print the following headings to the table
			out.println("<tr><th>Product</th><th>brand</th><th>Price</th><th>Quantity</th><th>Size</th></tr>");

			float totalPrice = 0f;
			for (String id : ids) {
				String sql = "SELECT * FROM products WHERE id = " + id;

				result = statement.executeQuery(sql);

				// Expect only one row in ResultSet
				result.next();
				// use following strings
				String product = result.getString("product");
				String brand = result.getString("brand");
				float price = result.getFloat("price");
				String size = result.getString("size"); // Retrieve size parameter

				int qtyOrdered = Integer.parseInt(request.getParameter("qty" + id));
				
				sql = "UPDATE products SET qty = qty - " + qtyOrdered + " WHERE id = " + id;

				statement.executeUpdate(sql);

				// Display this book ordered
				out.println("<tr>");
				out.println("<td>" + product + "</td>");
				out.println("<td>" + brand + "</td>");
				out.println("<td>" + price + "</td>");
				out.println("<td>" + qtyOrdered + "</td>");
				out.println("<td>" + size + "</td></tr>");
				totalPrice += price * qtyOrdered;
			}

			out.println("<tr><td>Total Price: $");
			out.printf("%.2f</td></tr>", totalPrice);
			out.println("</table>");

			
				// print the following strings and text
				out.println("<h3>Thank you "  + "</h3>");
				out.println("<p>Your order will be shipped in 2 days<br/><b>");
					
			

			out.println("</form> <!-- end of form--></div></div><!-- end of main--><p></p></div> <!-- end of mainsection --> </div> <!-- end of wrapper -->");
			out.println("<!-- Footer of the page-->\r\n"
					+ "	<footer>\r\n"
					+ "		<p> \r\n"
					+ "			<i>This is for educational purposes.</i><br>\r\n"
					+ "			<br>\r\n"
					+ "			Keep in contact here:<br>\r\n"
					+ "			Tell:<a href=\"tel: +353(0)123-456-789\" rel=\"\"> (0)123-456-789</a><br>\r\n"
					+ "			Email:<a href=\"mailto:feedback@petitestore.ie\"> feedback@petitestore.ie.</a><br>\r\n"
					+ "			<br>\r\n"
					+ "			You can also follow us on our social media account.<br>\r\n"
					+ "			Facebook:<a href=\"https://www.facebook.com\" target=\"_blank\" rel=\"noopener\"> Click </a>to follow.<br>\r\n"
					+ "			<br>\r\n"
					+ "			<i>All &#169; rights are reserved for Petite Shoes Store.</i>\r\n"
					+ "		</p>\r\n"
					+ "	</footer></body></html>");
			// end of form
		} catch (Exception e) {
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}