package ecoChat;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


@WebServlet("/availablejobs")
public class AvailableJobs extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if(session!=null&&session.getAttribute("rule").equals("user")) {
		int user_id = (Integer) session.getAttribute("user_id");
		ArrayList<Job_Bean> job_list = new ArrayList<Job_Bean>();
	
		String sql=null;
		java.sql.Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			java.sql.Driver ref = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(ref);

			String dbUrl = "jdbc:mysql://localhost:3306/capsv4_db";
			String filePath = "E:/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);

			con = DriverManager.getConnection(dbUrl, prop);
			
			sql = "select * from available_jobs where job_id not in(select job_id from applied_jobs where user_id=?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int job_id = rs.getInt("job_id");
				String job_title = rs.getString("job_title");
				String job_desc = rs.getString("desc");
				String job_category = rs.getString("category");
				Date job_date=rs.getDate("job_date");
				Job_Bean bean = new Job_Bean(job_id, job_title, job_desc, job_category,job_date);
				job_list.add(bean);
			}
			req.setAttribute("joblist", job_list);
			RequestDispatcher dispatcher = req.getRequestDispatcher("availablejobs.jsp");
			dispatcher.forward(req, resp);

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
