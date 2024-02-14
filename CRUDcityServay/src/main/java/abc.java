

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class abc
 */
@WebServlet("/abc")
public class abc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abc() {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter p = res.getWriter();
		
		
		String name = req.getParameter("name");
		String area = req.getParameter("area");
		String cont = req.getParameter("contact");
		String prof = req.getParameter("prof");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/city", "root", "");
			PreparedStatement ps = con.prepareStatement("INSERT INTO `publicdata`(`ID`, `Name`, `Area`, `Contact number`, `Proffession`) VALUES (null,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, area);
			ps.setString(3, cont);
			ps.setString(4, prof);
			int i = ps.executeUpdate();
			if(i==1)
			{
				req.getRequestDispatcher("list").include(req, res);
			}
			else
			{
				p.print("Failed to enter record. Try again.");
				req.getRequestDispatcher("index.html").include(req, res);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
