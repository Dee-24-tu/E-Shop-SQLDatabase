import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
        try{
			
			//STEP 1: Register JDBC driver. This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/OnlinePetiteShoesStore","root" ,"" );
            // STEP 3: Statements allow us to  issue SQL queries to the database
			statement = connect.createStatement();
			// Retrieve and process request parameter: "product"
			String product = request.getParameter("product");
			//String size = request.getParameter("size");
			
			out.println("<html><head><body>");
			
			
			String sql = "SELECT * FROM products WHERE qty > 0 AND ( product = '"+ product+"') ORDER BY product, brand, size";
			 result = statement.executeQuery(sql);
              
               result.next();
 
               out.println("<!DOCTYPE html><html><head><link rel='stylesheet' href='css/style.css'/><head><body>");
               out.println("<div id='wrapper'><!-- This wrapper centers the page-->"
   					+ "		<header><!-- This is HEADER--><h1>Petite Shoes Online Store</h1></header>"
   					+ "		<nav><!-- This is the Navigation bar--><ul><li><a href='index.html'>Home</a></li></ul></nav>"	
   					+ "		<div id ='mainsection'><!-- This is the main content bar-->"
   					+ "		<div id='main'><!-- This is the form block--><div id='main-wrapper'>");
               // Print the result in an HTML form inside a table
               out.println("<form method='get' action='OrderServlet'>");
               out.println("<table border='1' cellpadding='6'>");
               out.println("<tr>");
               out.println("<th>&nbsp;</th>");
               out.println("<th>Product</th>");
               out.println("<th>Brand</th>");
               out.println("<th>Price</th>");
               out.println("<th>Qty</th>");
               out.println("<th>Size</th>");
               out.println("</tr>");
			
            // ResultSet's cursor now pointing at first row
               do {
                  // Print each row with a checkbox identified by book's id
                  String id =  result.getString("id");
                  out.println("<tr>");
                  out.println("<td><input type='checkbox' name='id' value='" + id + "' /></td>");
                  out.println("<td>" +  result.getString("product") + "</td>");
                  out.println("<td>" +  result.getString("brand") + "</td>");
                  out.println("<td>$" +  result.getString("price") + "</td>");
                  out.println("<td><input type='text' size='3' value='1' name='qty" + id + "' /></td>");
                  out.println("<td><select name='size" + id + "'>"); // Start of size dropdown
                  out.println("<option value='2'>Size 2</option>");
                  out.println("<option value='3'>Size 3</option>");
                  out.println("<option value='4'>Size 4</option>");
                  out.println("</select></td>");
                  out.println("</tr>");
               } while ( result.next());
               out.println("</table><br />");
               
               out.println("<input class ='btn' type='submit' value='ORDER' />");
               out.println("<input class ='btn' type='reset' value='CLEAR' /></form>");
 
               out.println("</div><!-- end of main--><p></p></div> <!-- end of mainsection --></div> <!-- end of wrapper -->");
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
   					+ "			<i>All &#169; rights are reserved for Petite Shoes Store.</i></p></footer>");
   				out.println("</body></html>");
			
        }catch (Exception e){
    		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}