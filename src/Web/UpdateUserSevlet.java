package Web;


import Entity.User;
import daoImpl.userDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/updateinfo")
public class UpdateUserSevlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws HTTPException,IOException {
        HttpSession session = request.getSession();
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
        String info = request.getParameter("info");
        int id = Integer.parseInt(session.getAttribute("user_id").toString());
        user.setId(id);
        user.setEmail(email);
        user.setInfo(info);
        user.setPwd(pwd);
        user.setPhone(phone);
        user.setQq(qq);
        user.setUsername(name);
        if (userDao.updateuser(user) != 0) {

            if (user.getPwd().equals(session.getAttribute("pwd"))){
              session.setAttribute("username", name);
                session.setAttribute("pwd", pwd);
                session.setAttribute("info", user.getInfo());
                session.setAttribute("email", user.getQq());
                session.setAttribute("phone", user.getPhone());
                session.setAttribute("user_id", user.getId());
                session.setAttribute("info",user.getInfo());
                out.print("<script>" + "alert('更新信息成功');"+ "</script>");
                response.setHeader("Refresh", "1;URL=/jsp/index.jsp");
            }
            else {
                session.removeAttribute("username");
                session.removeAttribute("user_id");
                session.removeAttribute("pwd");
                session.removeAttribute("info");
                session.removeAttribute("email");
                session.removeAttribute("phone");
                out.print("<script>" + "alert('更新时更新了密码，请重新登陆');"+ "</script>");
                response.setHeader("Refresh", "1;URL=/jsp/login.jsp");
            }
        } else {
            out.print("<script>" + "alert('更新失败,请重新更新');"+ "</script>");
            response.setHeader("Refresh", "1;URL=/jsp/info.jsp");
        }
    }
}
