

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
 * Servlet implementation class TeacherEnrolled
 */
@WebServlet("/TeacherEnrolled")
//This is a servlet that show the courses that user has already been assigned
public class TeacherEnrolled extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherEnrolled() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Extract the teacherId stored in session
     	String teacherId =(String)request.getSession().getAttribute("teacherID");
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
         
        out.println("<html><body>");  
        try  
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");
			// Get a database connection.
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student_assessment_system", "root", "wqfzjnzx0830UK@");
			System.out.println("database connected"); 
      
			// Get the data of the selected course from the database and display it on the screen
			PreparedStatement stmt = connection.prepareStatement("SELECT course_ID,course_name FROM teacher_course WHERE user_ID = ?");
            stmt.setString(1, teacherId);
			ResultSet rs = stmt.executeQuery(); 
		
            out.print("<br><h2>Here is (are) the course(s) you have been assigned.</h2><br>");
            out.println("<br><table border=1 width=40% height=20%>");  
            out.println("<tr><th>couse_id</th><th>course name</th><tr>"); 
            
           while (rs.next()){                
        	   // Retrieve data from the result set
            	String cid = rs.getString("course_ID"); 
            	request.getSession().setAttribute("AssignedCourseId", cid);
            	String cn = rs.getString("course_name");  
           //when user selects a course, it'll redirect to another servlet that shows all the students enrolled to this course
                out.println("<tr><td>" +cid + "</td>"
                		+ "<td>" +cn + "&nbsp;&nbsp"+"<form method=\"post\" action=\"/Assignment3/ShowStudentsEnrolled\" "
                				+ "style=\"display: inline-block;\" margin-right=\"10px\">" 
                		+ "<input type=\"submit\" value=\"View students enrolled\">"
                		+ "</form></td></tr>");   
               
          }  
            out.println("</table>");
            out.println("</html></body>"); 
     
            connection.close();  
            stmt.close();
           }  
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
	}

}
