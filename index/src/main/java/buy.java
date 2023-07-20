

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


@WebServlet("/buy")
public class buy extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String name=request.getParameter("name");
		String product=request.getParameter("product");
		String price=request.getParameter("price");
		String quantity=request.getParameter("quantity");
		
		String p_num=request.getParameter("p_num");
		if(p_num==null) {
			p_num="";
		}
		
        System.out.println("1:"+price);
        System.out.println("2:"+quantity);
		
		
		Integer amount=Integer.parseInt(price)*Integer.parseInt(quantity);
		
    	String url="jdbc:mysql://localhost:3306/clothesshop";
    	String user="root";
    	String passwd="1234";
    	
    	String sql = "INSERT INTO cart_items VALUES(?,?,?,?,?,?)";
    	//String sql = "INSERT INTO product VALUES(?)";
    	   	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			out.print("WriteIn JDBC OK");
		}catch(ClassNotFoundException e){
			out.print("無法載入驅動程式");
		}
		
		
		try   
	       {
			   conn=DriverManager.getConnection(url,user,passwd);
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
	       }
	       catch(SQLException e){
	       
	           out.print(e.getMessage());
	           return;
	       }
	       
		try{
	        
            stmt.close(); 
            conn.close(); 
	      }
	      catch( SQLException e ){
	    	  
	      }
			RequestDispatcher productpage=request.getRequestDispatcher("index.jsp");
			productpage.forward(request, response);
}
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
