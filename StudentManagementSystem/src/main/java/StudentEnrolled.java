

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentEnrolled
 */
@WebServlet("/StudentEnrolled")
// This is a servlet that shows the courses the student enrolled
public class StudentEnrolled extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentEnrolled() {
        super();  
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the student Id sotred in session
		String studentId =(String)request.getSession().getAttribute("studentID");
		PrintWriter out = response.getWriter();   
        response.setContentType("text/html");  
         
        out.println("<html><body>");  
        try  
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");
			// Get a database connection.
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student_assessment_system", "root", "wqfzjnzx0830UK@");
			System.out.println("database connected"); 
      
			//extract the course information after matching the studentId student entered to the studentId stored in the database
			PreparedStatement stmt = connection.prepareStatement("SELECT course_ID,course_name FROM user_course_table WHERE user_ID = ?");
            stmt.setString(1, studentId);
            //execute the sql 
			ResultSet rs = stmt.executeQuery(); 
		
            out.print("<br><h2>Here is (are) the course(s) you have enrolled.</h2><br>");
            out.println("<br><table border=1 width=40% height=20%>");  
            out.println("<tr><th>couse_id</th><th>course name</th><tr>"); 
            
           while (rs.next()){                
        	   // Retrieve data from the result set
            	String cid = rs.getString("course_ID");   
            	String cn = rs.getString("course_name");  
           
            	//display the result in a table
                out.println("<tr><td>" +cid + "</td>"
                		+ "<td>" + cn + "&nbsp;&nbsp"+"<form method=\"post\" action=\"/Assignment3/DisplayMarks\"style=\"display: inline-block;\">"
                		+ "<input type=\"submit\" value=\"View grades\" margin-right=\"10px\">"
                		+"</form></td></tr>");   
               
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
