import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertData
 */
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertData() {
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
			out.println( "Registering  JDBC driver");
			//STEP 1: Register JDBC driver. This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			out.println( "Seting up the connection with the DB");
			//STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/OnlinePetiteShoesStore","root" ,"" );
            // STEP 3: Statements allow us to  issue SQL queries to the database
			statement = connect.createStatement();
			//STEP 4: declare a query
			String sql = "INSERT INTO products (id, product, brand, price,size,qty) VALUES" +
					"(101, 'Wedges', 'Tory Burch', 399, 'Size 2', 10), " +
		    		"(102, 'Wedges', 'Tory Burch', 399, 'Size 3', 10), " +
		    		"(103, 'Wedges', 'Tory Burch', 399, 'Size 4', 10), " +
		    		 "(104, 'Flats', 'Gucci', 799, 'Size 2', 10), " +
		             "(105, 'Flats', 'Gucci', 799, 'Size 3', 10), " +
		             "(106, 'Flats', 'Gucci', 799, 'Size 4', 10), " +
		             "(107, 'Boots', 'Timberland', 799, 'Size 2', 10), " +
		             "(108, 'Boots', 'Timberland', 799, 'Size 3', 10), " +
		             "(109, 'Boots', 'Timberland', 799, 'Size 4', 10), " +
		             "(110, 'Sneakers', 'Michael Kors', 199, 'Size 2', 10), " +
		             "(111, 'Sneakers', 'Michael Kors', 199, 'Size 3', 10), " +
		             "(112, 'Sneakers', 'Michael Kors', 199, 'Size 4', 10), " +
		             "(113, 'Heels', 'Jimmy Choo', 695, 'Size 2', 10), " +
		             "(114, 'Heels', 'Jimmy Choo', 695, 'Size 3', 10), " +
		             "(115, 'Heels', 'Jimmy Choo', 695, 'Size 4', 10)";
			//STEP 5: execute a query
			statement.executeUpdate(sql);
			out.println( "Data insert.....");
			
		}catch (Exception e){
			e.printStackTrace();
			
		}// Exception
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
