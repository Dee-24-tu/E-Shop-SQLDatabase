import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
		 
		  if(LoginDatabaseConnector.validate(request.getParameter("name"),request.getParameter("pass"))){  
		        RequestDispatcher rd=request.getRequestDispatcher("WelcomeServlet");  
		        rd.forward(request,response);  
		    }  
		    else{  
		         
		        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
		        rd.include(request,response);  
		        out.println("<html><body>");
                out.println("<h2>Error:</h2>");
                out.println("<p>Wrong username or password error. Please try again!</p>");
                out.println("</html></body>");
		    }  
		          
		    out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
