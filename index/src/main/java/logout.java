import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/logout")
public class logout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        // 清除資料
        
        session.setAttribute("result","");
        session.removeAttribute("result");
        session.invalidate();
        
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("errorPage3.jsp").include(request, response);
        
        out.print("您已成功登出退出系統!");
        out.close();
    }
}
