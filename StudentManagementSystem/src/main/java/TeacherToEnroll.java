

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
 * Servlet implementation class teacherToEnroll
 */
@WebServlet("/TeacherToEnroll")
//This is a servlet that update the user input to the database
public class TeacherToEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherToEnroll() {
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
		//get the user input based on the name 
		    String courseID = request.getParameter("enroll");
		    String courseName = request.getParameter("enrollname");
			String teacherId =(String)request.getSession().getAttribute("teacherID");
			
			PrintWriter out = response.getWriter(); 

	    // Validate the form data
		    if (courseID == null || courseID.isEmpty()) {
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
					//update the user input to two different tables in database
					PreparedStatement statement = connection.prepareStatement("INSERT INTO teacher_course (course_ID, user_ID, course_name) VALUES (?, ?, ?)");
					PreparedStatement statement2 = connection.prepareStatement("UPDATE user_table SET course_ID = ? WHERE user_ID = ?");

					// Set the values of the user data in the PreparedStatement		
					statement.setString(1, courseID);
					statement.setString(2, teacherId);
					statement.setString(3, courseName);
					
					statement2.setString(1, courseID);
					statement2.setString(2, teacherId);
					
					// Execute the query to insert the new user data into the database
					int rowsInserted = statement.executeUpdate();
					int rowsInserted2 = statement2.executeUpdate();

					// Close the statement and connection
					statement.close();
					statement2.close();
					//statement2.close();
				
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

