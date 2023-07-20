

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login2")
public class Login2 extends HttpServlet {
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		Connection conn = null;
		PreparedStatement stmt= null;
	    ResultSet rs = null;
	   
		String acc = request.getParameter("account");
		String pwd = request.getParameter("password");
		String name = request.getParameter("memberName");
		String tel = request.getParameter("tel");
//		String email=request.getParameter("email");
		String address = request.getParameter("address");

		String url="jdbc:mysql://localhost:3306/clothesshop";
    	String user="root";
    	String passwd="1234";
    	String sql = "select * from memberlist where account=? and password=?";
    	
    	
    	if(acc.isEmpty()||pwd.isEmpty()) {
        	   
    			
        	   //response.setContentType("text/html");
        	   //PrintWriter out1 = response.getWriter();
        	   //out1.println("<h3>錯誤：帳號和密碼不能為空</h3>");
        	   //out1.println("<script>");
    	       //out1.println("document.location.href='index.jsp';");
        	   //
        	   //out1.println("setTimeout(\"location.href='index.jsp'\",3000)");
    	       //out1.println("</script>");
        	   //out1.close();
        	   
        	   
        	   //RequestDispatcher errpage=request.getRequestDispatcher("index.jsp");
    	       //errpage.forward(request, response);
    		
    		
    			showMsgAndLocation(response,"錯誤：帳號和密碼不能為空","index.jsp");
    	}
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			out.println("WriteIn JDBC OK");
		}catch(ClassNotFoundException e){
			out.print("無法載入驅動程式");
		}
    	  
    	
    	
		try   
	       {
			   conn=DriverManager.getConnection(url,user,passwd);
			   stmt = conn.prepareStatement(sql);
			   stmt.setString(1, acc);
			   stmt.setString(2, pwd);
			   
	           rs = stmt.executeQuery();
	           
	       
			   
	          //將單筆資料存成HashMap
	           ArrayList<String> columns=this.getResultColumnName(rs);

	           HashMap<String, Object> result=null;
	           if(rs.next())
	           {
	               result=new HashMap<String, Object>();        
	               for(String a : columns)
	                   result.put(a, rs.getObject(a));
	           }
	        	
	           if(result == null) {
	        	   //沒比對到 回去
	        	   
		           RequestDispatcher disp=request.getRequestDispatcher("errorPage.jsp");
		   			disp.forward(request, response);
	           }
	           else {
		           request.getSession().setAttribute("result", result);
		           //request.setAttribute("result", result);
		           //String address = result.get("address").toString();
		           
		           RequestDispatcher disp=request.getRequestDispatcher("index2.jsp");
	   			disp.forward(request, response);
		           
	           }

	           //RequestDispatcher errpage=request.getRequestDispatcher("errorPage.jsp");
    	       //errpage.forward(request, response);
	     } catch(Exception  e){                     
	           out.print(e.getMessage());
	           return;			
		}   
		     
		
		
		try{   
			stmt.clearParameters();
            stmt.close(); 
            conn.close(); 
	      }
	      catch(SQLException e){  
	}
}
	ArrayList<String> columnNames;  
    ArrayList<HashMap<String,Object>> result;
	//取得結果集的欄名1
    protected ArrayList<String> getResultColumnName(ResultSet rs) throws Exception{
        this.columnNames=new ArrayList<String>();
        ResultSetMetaData rsmd=rs.getMetaData();
        for(int i=1;i<=rsmd.getColumnCount();i++) {
            this.columnNames.add(rsmd.getColumnName(i));
        }
        return this.columnNames;
    }
    //將結果集轉為集合
    protected ArrayList<HashMap<String,Object>> getResult(ResultSet rs) throws Exception {
        
        getResultColumnName(rs);
        this.result=new ArrayList<HashMap<String,Object>>();
        while(rs.next()) {
            HashMap<String,Object> data=new HashMap<String,Object>();
            for(String a:this.columnNames) {
                data.put(a, rs.getObject(a));
            }
            this.result.add(data);            
        }
        return this.result;
    }
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void showMsgAndLocation(HttpServletResponse response,String msg,String directPage) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		//動作完成...
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("alert ('").append(msg).append("');\n");
		if (directPage == null) {
			sb.append("history.back();\n");
		}else {
			sb.append("window.location = '").append(directPage).append("';\n");
		}
		sb.append("</script>");

		try {
			out.println(sb.toString());
			out.close();
		} catch (Exception e) {};
	}
}
