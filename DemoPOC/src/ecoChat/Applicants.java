package ecoChat;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Applicants extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int job_id=Integer.parseInt(req.getParameter("job_id"));
		HttpSession session = req.getSession(false);
		ExcelWriter writer=new ExcelWriter();
		ArrayList<UserBean> applicants=new ArrayList<UserBean>();
		if(session!=null&&session.getAttribute("role").equals("admin")) {

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
				
				sql = "select * from users where user_id in(select user_id from applicants where job_id=?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, job_id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int userid=rs.getInt("user_id");
					 String name=rs.getString("user_name");
					 String mobile=rs.getString("user_mobile");
					 String email=rs.getString("user_email");
					 String gender=rs.getString("user_gender");
					
					 String category=rs.getString("user_category");
					 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					 String dOB = formatter.format(rs.getDate("user_dob"));  
					 UserBean bean=new UserBean(String.valueOf(userid), name, mobile, email, gender,dOB, category);
					 applicants.add(bean);
				}
				     String filename=String.valueOf(job_id);
					writer.writeExcel(applicants,filename);
				
					resp.sendRedirect("/applicants/"+filename+"xlsx");
					

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
}
}
