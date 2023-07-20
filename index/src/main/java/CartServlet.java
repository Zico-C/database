import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String[] productIDs = request.getParameterValues("productID[]");
	    String[] productNames = request.getParameterValues("productName[]");
	    String[] quantities = request.getParameterValues("quantity[]");
	    String[] prices = request.getParameterValues("price[]");
	    String[] totalPrices = request.getParameterValues("totalPrice[]");

	    // 在這裡進行將資料存入 MySQL 資料庫的邏輯處理
	    
	    // 回應訊息給瀏覽器
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    // 檢視從表單接收的值
	    for (int i = 0; i < productIDs.length; i++) {
	        out.println("購物ID：" + productIDs[i]);
	        out.println("商品名稱：" + productNames[i]);
	        out.println("數量：" + quantities[i]);
	        out.println("價格：" + prices[i]);
	        out.println("總價：" + totalPrices[i]);
	        out.println("<br>");
	    }

	    out.println("資料已儲存至資料庫");
	}
}