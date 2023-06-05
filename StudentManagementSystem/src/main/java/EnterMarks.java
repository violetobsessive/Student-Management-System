

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnterMarks
 */
@WebServlet("/EnterMarks")
public class EnterMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterMarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the student Id sotred in session
				
				PrintWriter out = response.getWriter();   
		        response.setContentType("text/html");  
		         
		        out.println("<html><body>");  
		        try  
		        {  
		            Class.forName("com.mysql.cj.jdbc.Driver");
					// Get a database connection.
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student_assessment_system", "root", "wqfzjnzx0830UK@");
					System.out.println("database connected"); 
		      
		        	   // Retrieve data from the result set
		        	   out.println("<html><body><h1>Please enter the assessment results</h1>\r\n"
		               		+ "   <form method=\"post\" action=\"/Assignment3/StoreMarks\">  \r\n"
		               		+ "   \r\n"
		               		+ "		<label for=\"quizmark\">Quiz Mark: </label>\r\n"
		               		+ "		<input type=\"text\" name=\"quizMark\"><br>\r\n"
		               		+ "		\r\n"
		               		+ "		<label for=\"assignmentmark\">Assignment Mark: </label>\r\n"
		               		+ "		<input type=\"text\" name=\"assignmentMark\"><br>\r\n"
		               		+ "		\r\n"
		               		+ "		<label for=\"finalmark\">Final Exam Mark: </label>\r\n"
		               		+ "		<input type=\"text\" name=\"finalMark\"><br>\r\n"
		               		+ "		\r\n"
		               		+ "		<br><input type=\"submit\" value=\"Enter\">\r\n"
		               		+ "</form></body></html>");}
		     
		            catch (Exception e) 
		           {  
		            out.println("error"); 
		        }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doGet(request, response);
}}
