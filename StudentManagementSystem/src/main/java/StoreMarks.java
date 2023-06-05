

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StoreMarks
 */
@WebServlet("/StoreMarks")
public class StoreMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreMarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// Retrieve the form data from the request object
		// Store the user input to the database
	    String quizMark = request.getParameter("quizMark");
	    String assignmentMark = request.getParameter("assignmentMark");
	    String finalMark = request.getParameter("finalMark");
	    String username =(String)request.getSession().getAttribute("username");
	    String cid =(String)request.getSession().getAttribute("cid");

	    // Validate the form data
	    if (quizMark == null || quizMark.isEmpty() ||
	    	assignmentMark == null || assignmentMark.isEmpty() ||
	    	finalMark == null || finalMark.isEmpty()) 
	       {
	        // If any required field is missing or empty, display an error message
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
	    } else {
	    	  // Otherwise, add the new user to the database
	        // TODO: Add code to add user to the database
	    	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student_assessment_system", "root", "wqfzjnzx0830UK@");
				System.out.println("database connected");
				
				// Create a PreparedStatement with the SQL query
				PreparedStatement statement = connection.prepareStatement("INSERT INTO assessment_table (quiz_mark, assignment_mark, final_exam, user_ID, course_ID) VALUES (?, ?, ?, ?, ?)");

				// Set the values of the user data in the PreparedStatement
				statement.setString(1, quizMark);
				statement.setString(2, assignmentMark);
				statement.setString(3, finalMark);
				statement.setString(4, username);
				statement.setString(5, cid);
				// Execute the query to insert the new user data into the database
				int rowsInserted = statement.executeUpdate();

				// Close the statement and connection
				statement.close();
				connection.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	out.println("added");
	      
	        // After adding the user, redirect the user to a confirmation page or the dashboard
	        //response.sendRedirect("UserAdded.jsp");
	    }
	}

}
