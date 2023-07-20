import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckServlet2")
public class CheckServlet2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        // 獲取請求參數
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 獲取 pickUp 的值
        String pickUp = request.getParameter("pickUp");

        // 獲取 address 的值
        String address = request.getParameter("address");

        // 獲取 payMent 的值
        String payMent = request.getParameter("payMent");
        
        String account = request.getParameter("account1");

        // 創建數據庫連接信息
        Connection conn = null;
        PreparedStatement stmt = null;

        // 連接到 MySQL 数据库
        String url = "jdbc:mysql://localhost:3306/clothesshop";
        String user = "root";
        String password = "1234";

//        // 若訂單為空跳轉到空單信息頁面
//        if (order == null || order.isEmpty()) {
//            showMsgAndLocation(response, "購物車內無訂單", "shoppingPage.jsp");
//            return;
//        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("寫入 JDBC OK");
        } catch (ClassNotFoundException e) {
            out.print("無法載入驅動");
            return;
        }

        try {
            // 創建數據庫連接
            conn = DriverManager.getConnection(url, user, password);

         // 準備插入語句
            String sql = "INSERT INTO orders (order_name, productName, quantity, price, total_price, pickUp, address, payMent,account) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";            
            stmt = conn.prepareStatement(sql);

            int index = 0; // 用於追踪order的索引
            while (parameterMap.containsKey("order[" + index + "][order]")) {
                String[] orderValues = parameterMap.get("order[" + index + "][order]");
                String[] productNameValues = parameterMap.get("order[" + index + "][productName]");
                String[] quantityValues = parameterMap.get("order[" + index + "][quantity]");
                String[] priceValues = parameterMap.get("order[" + index + "][price]");
                String[] totalPriceValues = parameterMap.get("order[" + index + "][totalPrice]");

                String order = (orderValues != null && orderValues.length > 0) ? orderValues[0] : null;
                String productName = (productNameValues != null && productNameValues.length > 0) ? productNameValues[0] : null;
                String quantity = (quantityValues != null && quantityValues.length > 0) ? quantityValues[0] : null;
                String price = (priceValues != null && priceValues.length > 0) ? priceValues[0] : null;
                String totalPrice = (totalPriceValues != null && totalPriceValues.length > 0) ? totalPriceValues[0] : null;

                // 設置參數
                stmt.setString(1, order.trim());
                stmt.setString(2, productName.trim());
                stmt.setString(3, quantity.trim());
                stmt.setString(4, price.trim());
                stmt.setString(5, totalPrice.trim());
                stmt.setString(6, pickUp);
                stmt.setString(7, address);
                stmt.setString(8, payMent);
                stmt.setString(9, account);
                System.out.println("java: "+order);
                System.out.println("java: "+productName);
                System.out.println("java: "+quantity);
                System.out.println("java: "+price);
                System.out.println("java: "+totalPrice);
                System.out.println("java: "+pickUp);
                System.out.println("java: "+address);
                System.out.println("java: "+payMent);
                System.out.println("java: "+account);
                // 執行插入語句
                stmt.executeUpdate();

                System.out.println("訂單"+(index+1)+"數據寫入成功");
                index++; // 增加索引，處理下一個order
            }

            // 返回成功訊息


            RequestDispatcher errpage = request.getRequestDispatcher("index2.jsp");
            errpage.forward(request, response);

        } catch (SQLException e) {
        	e.printStackTrace();
            out.print(e.getMessage());
            return;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // 處理關閉連接異常
            }
        }
    }
}