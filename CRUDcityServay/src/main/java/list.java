

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class list
 */
@WebServlet("/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public list() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter p = res.getWriter();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/city","root","");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `publicdata`");
			
			
			p.print("<body style='background-image:url(peoples.jpg);background-size: cover;background-position: fixed;'>");
			p.print("<h1 style='text-align:center; font-size:50px;'>Main console</h1>");
			p.print("<a href='update.html' style='display:flex;'>Update</a> <a href='index.html' style='display:flex;'>Insert</a> <a href='Delete.html' style='display:flex;'>Delete</a>");
			p.print("");
			p.print("");
			p.print("<br>");
			p.print("<br>");
			p.print("<br>");
			p.print("<br>");
			p.print("<br>");
			p.print("<table colspacing='0' width='400px' border='1' style='margin-left:36%;margin-top:0; color: violet;'>");
			p.print("<tr>");
			p.print("<td>ID</td>");
			p.print("<td>Name</td>");
			p.print("<td>Area</td>");		
			p.print("<td>Contact number</td>");
			p.print("<td>Proffession</td>");
			p.print("</tr>");
			
			while(rs.next())
			{
				p.print("<tr>");
				p.print("<td>"+rs.getInt("ID")+"</td>");
				p.print("<td>"+rs.getString("Name")+"</td>");
				p.print("<td>"+rs.getString("Area")+"</td>");
				p.print("<td>"+rs.getString("Contact number")+"</td>");
				p.print("<td>"+rs.getString("Proffession")+"</td>");
				p.print("</tr>");
			}
			
			p.print("</table>");
			
			p.print("</body>");
			
			
		}
		catch(Exception e)
		{
			e.getStackTrace();
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
