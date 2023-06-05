

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
//It is a servlet that shows all the courses to the student that need to enroll in one of these courses
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super(); 
        // TODO Auto-generated constructor stub 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the course information from the database
		PrintWriter out = response.getWriter();   
        response.setContentType("text/html");    
        out.println("<html><body>");   
        try  
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");
			// Get a database connection.
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/student_assessment_system", "root", "wqfzjnzx0830UK@");
			System.out.println("database connected");  
      
			//select everything from one of the tables in the database, not dynamic
            Statement stmt = connection.createStatement();  
            
            ResultSet rs = stmt.executeQuery("select * from course_table");   
            
            //print a table using html tags that list everything in course_table in the database
            out.print("<br><h2>Not enrolled in any courses, here is a list of courses that you can choose from.</h2><br>");
            out.println("<br><table border=1 width=50% height=20%>");  
            out.println("<tr><th>couse_id</th><th>course name</th><th>semester</th><tr>");  
            while (rs.next()) 
            {  
            	//when there is results, extract the value from the database and print them in the table
                String n = rs.getString("id");  
                String nm = rs.getString("course_name");  
                int s = rs.getInt("semester");   
                out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td></tr>");   
            }  
            out.println("</table>");  
            out.println("</html></body>"); 
            //create two inputs for user to type in the course and course name they want to enroll, and a enroll submit button
            out.println("    <br><form method=\"post\" action=\"/Assignment3/enroll\">\r\n"
            		+ "        <label for=\"enroll\">Enter Course ID:  </label>\r\n"
            		+ "        <input type=\"text\" name=\"enroll\">"
            		+ "        <label for=\"enroll\">Enter Course Name:  </label>\r\n"
            		+ "        <input type=\"text\" name=\"enrollname\">"
            		+ "		<input type=\"submit\" value=\"enroll\"><br>\r\n"
            		+ "    </form>");
     
            connection.close();  
           }  
            catch (Exception e) 
           {  
            out.println("error"); 
            
            //once user click on the button, redirect/forward to another servlet that store the data of user input into the database
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/enroll");
			if(dispatcher != null) {
				dispatcher.forward(request, response);}
        }  
    }   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
