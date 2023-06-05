package Assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class servlet1
 */
@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servlet1() { 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Get parameters from request object.
		String userName = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();

		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
			// Get a database connection.
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student_assessment_system", "root", "wqfzjnzx0830UK@");
			System.out.println("database connected");
			request.getSession().setAttribute("dbConnection", connection);
			
			// Prepare an SQL statement that can takes in a parameter and match it with the data in the database
			// It is a dynamic statement
			String sql = "SELECT * FROM user_table WHERE user_ID=? AND password=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);

			// Execute the SQL statement.
			ResultSet rs = stmt.executeQuery();

			// Check if the username and password matches
			String role;
			if (rs.next()) {
				System.out.println("Login successful");
				
				//check the role
			role = rs.getString("role");
				if(role!= null && role.equals("student")){
					//get the student id from the database and save it in the session
					String studentID = rs.getString("user_ID");
					request.getSession().setAttribute("studentID", studentID);
					
					//get the course id from the database
					String courseid = rs.getString("course_ID");
					//if courseid == null, redirect to another servlet that ask the user to enroll a course
                   if(courseid.equals("null")) {
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/StudentServlet");
						if(dispatcher != null) {
							dispatcher.forward(request, response);}}
                 //if courseid != null, redirect to another servlet that shows the enrolled courses
					else {RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/StudentEnrolled");
					if(dispatcher != null) {
						dispatcher.forward(request, response);}}
				}
				
				// Redirect to teacher.jsp
			 if(role!= null && role.equals("teacher")){
					//get the teacher id from the database and save the data in session
					String teacherID = rs.getString("user_ID");
					request.getSession().setAttribute("teacherID", teacherID);
					
					//get the course id from the database
					String courseid = rs.getString("course_ID");
					
					//if courseid == null, redirect to another servlet that ask the user to enroll a course
					if(courseid.equals("null")) {
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TeacherServlet");
						if(dispatcher != null) {
							dispatcher.forward(request, response);}}
					//if courseid != null, redirect to another servlet that shows the courses that teacher has been assigned to
					else {RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TeacherEnrolled");
					if(dispatcher != null) {
						dispatcher.forward(request, response);}}
			
				// Redirect to admin.jsp
			}else if (role!= null && role.equals("admin")){
				response.sendRedirect("admin.jsp");
			}
		}else {
			out.println("Invalid username or password");
		}
			// Close the database connection.
			connection.close();
		} catch (SQLException e) {
			out.print("An error occurred while processing your request.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

