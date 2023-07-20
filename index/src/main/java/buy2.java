
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/buy2")
public class buy2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement stmt = null;

        String name = request.getParameter("name");
        String product = request.getParameter("product");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");

        String p_num = request.getParameter("p_num");
        if (p_num == null) {
            p_num = "";
        }

        Integer amount = Integer.parseInt(price) * Integer.parseInt(quantity);

        String url = "jdbc:mysql://localhost:3306/clothesshop";
        String user = "root";
        String passwd = "1234";

        String sql = "INSERT INTO product VALUES(?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.print("WriteIn JDBC OK");
        } catch (ClassNotFoundException e) {
            out.print("無法載入驅動程式");
        }

        try {
            conn = DriverManager.getConnection(url, user, passwd);
            stmt = conn.prepareStatement(sql);
            stmt.setString(6, name);
            stmt.setString(1, product);
            stmt.setString(2, p_num);
            stmt.setString(3, price);
            stmt.setString(4, quantity);
            stmt.setInt(5, amount);
            stmt.executeUpdate();
            stmt.clearParameters();
            out.print("資料已成功插入資料庫");
        } catch (SQLException e) {
            out.print(e.getMessage());
            return;
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}