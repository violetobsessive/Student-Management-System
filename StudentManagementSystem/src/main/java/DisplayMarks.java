

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
 * Servlet implementation class DisplayMarks
 */
@WebServlet("/DisplayMarks")
// This is a class that shows the marks of each course student enrolled
public class DisplayMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayMarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//extract studentId student entered when logging in stored in session
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
      
			//compare the studentId with data stored in the database and extract the matching data
			PreparedStatement stmt = connection.prepareStatement("SELECT course_ID,quiz_mark,assignment_mark,final_exam FROM assessment_table WHERE user_ID = ?");
            stmt.setString(1, studentId);
            
            
			ResultSet rs = stmt.executeQuery(); 
			
            // If there is matching studentId in the database, show the data in a table
           while (rs.next()){                
        	   out.print("<br><h2>Your grades for courses enrolled are listed below.</h2><br>");
               out.println("<br><table border=1 width=40% height=20%>");  
               out.println("<tr><th>couse_id</th><th>quiz mark</th><th>assignment mark</th><th>final exam</th><tr>"); 
   			
        	   // Retrieve data from the result set
            	String cid = rs.getString("course_ID");   
            	String qm = rs.getString("quiz_mark");  
            	String am = rs.getString("assignment_mark");  
            	String fe = rs.getString("final_exam");  
            	
                out.println("<tr><td>" +cid + "</td>"
                		+ "<td>" + qm + "</td>" + "<td>" + am + "</td>" + "<td>" + fe + "</td>"+"</tr>");
                
                out.println("</table>");
                out.println("<form method=\"post\" action=\"/Assignment3/StudentEnrolled\"><br>"
                		+ "<input type=\"submit\" value=\"Back to student dashboard\"></form>");
                
                out.println("<form method=\"post\" action=\"/Assignment3/LogOut\"><br>"
                   		+ "<input type=\"submit\" value=\"Log Out\"></form>");
                out.println("</html></body>"); 
          }     
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
