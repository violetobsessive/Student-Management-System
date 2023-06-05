

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
 * Servlet implementation class ShowStudentsEnrolled
 */
@WebServlet("/ShowStudentsEnrolled")
public class ShowStudentsEnrolled extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentsEnrolled() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String assigned_courseId =(String)request.getSession().getAttribute("AssignedCourseId");
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        out.println("<html><body>");  
        
        try  
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");
			// Get a database connection.
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student_assessment_system", "root", "wqfzjnzx0830UK@");
			System.out.println("database connected"); 
      
			PreparedStatement stmt = connection.prepareStatement("SELECT user_ID,course_ID FROM user_course_table WHERE course_ID = ?");
            stmt.setString(1, assigned_courseId);
			ResultSet rs = stmt.executeQuery(); 
			
		//Display the students who are enrolled in this course by extracting data from the database
            out.print("<br><h2>Here is (are) the students that are enrolled in this course</h2>");
            out.println("<br><table border=1 width=40% height=20%>");  
            out.println("<tr><th>user_id</th><th>couse_id</th><tr>"); 
            
           while (rs.next()){                
        	   // Retrieve data from the result set
        	    String un = rs.getString("user_ID"); 
        	    request.getSession().setAttribute("username", un);
            	String cid = rs.getString("course_ID"); 
            	request.getSession().setAttribute("cid", cid);
            
            	// To enter marks for each student, it'll redirect to a jsp page that ask the user to input student marks
                out.println("<tr><td>" +un + "</td>"
                		+ "<td>" + cid + "&nbsp;&nbsp"+"<form method=\"post\" action=\"/Assignment3/EnterMarks\" style=\"display: inline-block;\" margin-right=\"10px\">" 
                		+ "<input type=\"submit\" value=\"Enter marks for assessment\">"
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
