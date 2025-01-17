import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
/**
 * Servlet implementation class EshopDisplayServlet
 */
@WebServlet("/EshopDisplayServlet")
public class EshopDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result= null;
	private static final Logger LOGGER = Logger.getLogger(EshopDisplayServlet.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EshopDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		
        protected void doGet(HttpServletRequest request,
    			HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		// Prints formatted representations of objects to a text-output stream
    		PrintWriter out = response.getWriter();

    		try {

    			// STEP 1: Register JDBC driver. This will load the MySQL driver,
    			// each DB has its own driver
    			Class.forName("com.mysql.jdbc.Driver");

    			// STEP 2: Open a connection. Setup the connection with the DB
    			connect = DriverManager.getConnection(
    					"jdbc:mysql://localhost/OnlinePetiteShoesStore", "root", "");
    			// STEP 3: Statements allow us to issue SQL queries to the database
    			statement = connect.createStatement();
    			// STEP 4: declare a query
    			String sql = "SELECT DISTINCT product FROM products WHERE qty > 0";
    			// STEP 5: execute a query
    			result = statement.executeQuery(sql);
    			// Print html with CSS style sheet
    			out.println("<!DOCTYPE html><html><head><link rel='stylesheet' href='css/style.css'/></head><body>");
    			out.println("<div id='wrapper'><!-- This wrapper centers the page-->"
    					+ "		<header><!-- This is HEADER--><h1>Petite Shoes Online Store</h1></header>"
    					+ "		<nav><!-- This is the Navigation bar--><ul><li><a href='index.html'>Home</a></li></ul></nav>"	
    					+ "		<div id ='mainsection'><!-- This is the main content bar--><div id='scr'></div>"
    					+ "		<div id='main'><!-- This is the form block--><div id='main-wrapper'>"
    					+ "     <form action='QueryServlet' method='get' class='form'>  <!-- Start of form-->");

    			// Begin an HTML form
    			out.println("<!-- Start of form--><form method='get' action='QueryServlet'>");
    			out.print("Welcome "+request.getParameter("name")); 
    			out.println("<hr>");
    			out.println("<h3>Successful Login!</h3>");
    			out.println("<hr>");
    			out.println("<p></p>");
    			// A pull-down menu of all the selection option
    			out.println("<h2>Have a look at our selected shoes.</h2><p>Choose Shoes:</p><select  name='product' size='1'>");
    			out.println("<option class='drop-down' value=''>Select...</option>"); 
    			
    			// Loop through the result set to populate the product dropdown
    			while (result.next()) { // list all the options
    				String product = result.getString("product");
    				out.println("<option value='" + product + "'>" + product + "</option>");
    			}
    			
    			out.println("</select><br />"); // Close the select tag for size
    			
    			// create a submit button
    			out.println("<input id='space' class='btn' type='submit' value='SEARCH' />");
    			out.println("<p> </p>");
    			
    			out.println("</form></form> <!-- end of form--></div><!-- end of main--><p></p></div> <!-- end of mainsection --></div> <!-- end of wrapper -->");
    			// end of form
    			RequestDispatcher rd = request
    					.getRequestDispatcher("ShopDisplay.html");
    			rd.include(request, response);
    			

    		//}
    		} catch (Exception e) {
    			// Handle the exception gracefully
    		    e.printStackTrace(); 
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
    			out.println("</div></body></html>");
    		}
    	}

    	/**
    	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    	 *      response)
    	 */
    	protected void doPost(HttpServletRequest request,
    			HttpServletResponse response) throws ServletException, IOException {
    		// Retrieve the selected product and size from request parameters
    	    String product = request.getParameter("product");
    	    String size = request.getParameter("size");

    	    // You can then use these values for further processing, such as updating the SQL database
    	    
    	    // Redirect to the checkout page or perform other operations as needed
    	    response.sendRedirect("checkout.jsp");
    	}
}

	