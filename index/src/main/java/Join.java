import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class Join extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		Connection conn = null;
		PreparedStatement stmt = null;
		String account = request.getParameter("account");
		String name = request.getParameter("memberName");
		String password = request.getParameter("password");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");

		String url = "jdbc:mysql://localhost:3306/clothesshop";
		String user = "root";
		//String passwd = "gregchiu99";
		String passwd = "1234";
		String sql1 = "select account from memberlist where account='" + account + "'";
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			out.print("WriteIn JDBC OK");
		} catch (ClassNotFoundException e) {
			out.print("無法載入驅動程式");
		}

		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, user, passwd);
			stmt = conn.prepareStatement(sql1);
			rs = stmt.executeQuery();
			while (rs.next()) {
				request.getRequestDispatcher("errorPage2.jsp").forward(request, response);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "INSERT INTO memberlist(account,memberName,password,tel,address) VALUES(?,?,?,?,?)";
		try {
			
			conn = DriverManager.getConnection(url, user, passwd);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account);
			stmt.setString(2, name);
			stmt.setString(3, password);
			stmt.setString(4, tel);
			stmt.setString(5, address);
			stmt.executeUpdate();
			stmt.clearParameters();
			request.getRequestDispatcher("errorPage4.jsp").forward(request, response);
		} catch (SQLException e) {

			out.print(e.getMessage());
			return;
		}

		try {

			stmt.close();
			conn.close();
		} catch (SQLException e) {

		}
		RequestDispatcher disp = request.getRequestDispatcher("index2.jsp?arg=" + name);
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}