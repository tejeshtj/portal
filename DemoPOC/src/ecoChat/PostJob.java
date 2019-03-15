package ecoChat;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/postjob")
public class PostJob extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int job_id=Integer.parseInt(req.getParameter("job_id"));
		String job_title=req.getParameter("job_title");
		String job_desc=req.getParameter("job_desc");
		String job_category=req.getParameter("job_category");
		Date job_date;
		

		HttpSession session = req.getSession(false);
		if(session!=null&&session.getAttribute("role").equals("admin")) {
		

	
		String sql=null;
		java.sql.Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			java.sql.Driver ref = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(ref);
			job_date=new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("job_date"));
			String dbUrl = "jdbc:mysql://localhost:3306/capsv4_db";
			String filePath = "E:/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);

			con = DriverManager.getConnection(dbUrl, prop);
			
			sql = "insert into jobs values(?,?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, job_id);
            pstmt.setString(2,job_title);
            pstmt.setString(3,job_desc);
            pstmt.setString(4,job_category);
            pstmt.setDate(5,(java.sql.Date) job_date);
			int count = pstmt.executeUpdate();

			if (count>0) {
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("posted.jsp");
				dispatcher.forward(req, resp);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
			dispatcher.forward(req, resp);

		}

		finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		}
		else if(session==null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("loginError.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
