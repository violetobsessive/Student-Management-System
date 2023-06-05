

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/TeacherServlet")
//This is a servlet that displays all the courses that teacher can select
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");   
        out.println("<html><body>");  
        try 
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");
			// Get a database connection.
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student_assessment_system", "root", "wqfzjnzx0830UK@");
			System.out.println("database connected"); 
      
            Statement stmt = connection.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from course_table");  
            
            //display all the courses in the form of a table
            out.print("<br><h2>Not enrolled in any courses, here is a list of courses that you can choose from.</h2><br>");
            out.println("<br><table border=1 width=50% height=20%>");  
            out.println("<tr><th>couse_id</th><th>course name</th><th>semester</th><tr>");  
            while (rs.next()) 
            {  
                String n = rs.getString("id");  
                String nm = rs.getString("course_name");  
                int s = rs.getInt("semester");   
                out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td></tr>");   
            }  
            out.println("</table>");  
            out.println("</html></body>"); 
            out.println("    <br><form method=\"post\" action=\"/Assignment3/TeacherToEnroll\">\r\n"
            		+ "        <label for=\"enroll\">Enter Course ID:  </label>\r\n"
            		+ "        <input type=\"text\" name=\"enroll\">"
            		+ "        <label for=\"enroll\">Enter Course Name:  </label>\r\n"
            		+ "        <input type=\"text\" name=\"enrollname\">"
            		+ "		<input type=\"submit\" value=\"select\"><br>\r\n"
            		+ "    </form>");
     
            connection.close();  
           }  
            catch (Exception e) 
           {  
            out.println("error"); 
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TeacherToEnroll");
			if(dispatcher != null) {
				dispatcher.forward(request, response);}
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
