

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
 * Servlet implementation class deleteRecord
 */
@WebServlet("/deleteRecord")
public class deleteRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteRecord() {
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
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/city", "root", "");
			PreparedStatement ps = con.prepareStatement("DELETE FROM `publicdata` WHERE `ID`=?");
			
			ps.setInt(1, id);
			
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
