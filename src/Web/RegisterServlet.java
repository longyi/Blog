package Web;

import Entity.User;
import daoImpl.userDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LongYi
 * @date 2018/10/27 17:53
 * @log: 1.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        userDaoImpl userDao = new userDaoImpl();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String phone = request.getParameter("phone");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
        user.setEmail(email);
        user.setPwd(pwd);
        user.setPhone(phone);
        user.setQq(qq);
        user.setUsername(name);
        if(userDao.adduser(user) != 0){
            out.print("<script>" + "alert('注册成功啦');"+ "</script>");
            response.setHeader("Refresh", "1;URL=/jsp/login.jsp");
        }else{
            out.print("<script>" + "alert('注册失败');"+ "</script>");
            response.setHeader("Refresh", "1;URL=/jsp/register.jsp");
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
