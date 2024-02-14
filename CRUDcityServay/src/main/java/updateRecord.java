

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
 * Servlet implementation class updateRecord
 */
@WebServlet("/updateRecord")
public class updateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateRecord() {
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
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String contact = req.getParameter("contact");
		String area = req.getParameter("area");
		String prof = req.getParameter("prof");
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/city", "root", "");
			PreparedStatement ps = con.prepareStatement("UPDATE `publicdata` SET `Name`=?,`Area`=?,`Contact number`=?,`Proffession`=? WHERE `ID`=?");
			ps.setString(1, name);
			ps.setString(2, area);
			ps.setString(3, contact);
			ps.setString(4, prof);
			ps.setInt(5, id);
			
			int i = ps.executeUpdate();
			
			if(i==1)
			{
				req.getRequestDispatcher("list").include(req, res);
			}
			else
			{
				p.print("failed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
