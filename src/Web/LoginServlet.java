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
 * @date 2018/10/27 17:19
 * @log: 1.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        userDaoImpl userDao = new userDaoImpl();
        String name = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        User user = userDao.getUserInfobyname(name);
                if (userDao.isexit(name)){
                    if (user.getUsername().equals(name)&& !user.getPwd().equals(pwd)) {
                        out.print("<script>" + "alert('密码错误啦~~');"+ "</script>");
                        response.setHeader("Refresh", "1;URL=/jsp/login.jsp");
                    }
                    else {
                        request.getSession().setAttribute("username", name);
                        request.getSession().setAttribute("pwd", pwd);
                        request.getSession().setAttribute("info", user.getInfo());
                        request.getSession().setAttribute("email", user.getQq());
                        request.getSession().setAttribute("phone", user.getPhone());
                        request.getSession().setAttribute("user_id", user.getId());
                        out.print("<script>" + "alert('登陆成功啦~~');"+ "</script>");
                        response.setHeader("Refresh", "1;URL=/jsp/index.jsp");
                    }
            }
        else {
                    out.print("<script>" + "alert('你还没注册,快去注册吧~~');"+ "</script>");
                    response.setHeader("Refresh", "1;URL=/jsp/register.jsp");
        }
        }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
