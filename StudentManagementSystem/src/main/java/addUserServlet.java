

import java.io.IOException;
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
 * Servlet implementation class addUserServlet
 */
@WebServlet("/addUserServlet")
public class addUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUserServlet() {
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
		 // Retrieve the form data from the request object
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String firstname = request.getParameter("firstname");
	    String lastname = request.getParameter("lastname");
	    String phone = request.getParameter("phone");
	    String role = request.getParameter("role");
	    String course_ID = request.getParameter("course_ID");

	    // Validate the form data
	    if (username == null || username.isEmpty() ||
	        password == null || password.isEmpty() ||
	        firstname == null || firstname.isEmpty() ||
	        lastname == null || lastname.isEmpty() ||
	        phone == null || phone.isEmpty() ||
	        role == null || role.isEmpty()) {
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
				PreparedStatement statement = connection.prepareStatement("INSERT INTO user_table (user_ID, password, first_name, last_name, phone, role, course_ID) VALUES (?, ?, ?, ?, ?, ?, ?)");

				// Set the values of the user data in the PreparedStatement
				statement.setString(1, username);
				statement.setString(2, password);
				statement.setString(3, firstname);
				statement.setString(4, lastname);
				statement.setString(5, phone);
				statement.setString(6, role);
				statement.setString(7, course_ID);

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
	      
	        // After adding the user, redirect the user to a confirmation page or the dashboard
	        response.sendRedirect("UserAdded.jsp");
	    }
	}
}