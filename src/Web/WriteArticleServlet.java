package Web;

import Entity.Article;
import com.google.gson.Gson;
import daoImpl.articleDaoImpl;
import daoImpl.articletypeDaoImpl;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


/**
 * @author LongYi
 * @date 2018/10/28 15:18
 * @log: 1.
 */
@WebServlet("/write")
public class WriteArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
//创建json对象，转换json字符串
        Gson gs = new Gson();
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        reader.close();
//将json字符串转成java对象
        Article a = gs.fromJson(json,Article.class);
//用于后面的插入操作
        articleDaoImpl articleDao = new articleDaoImpl();
        //用于查询类型的id
        articletypeDaoImpl articletypeDao = new articletypeDaoImpl();

        int articletypeid = articletypeDao.getArticletypeid( a.getArticle_type());
        if ( articletypeid != 0 ){
            a.setArticle_typeid(articletypeid);
        }
        else {
            articletypeDao.addArticletype(a.getArticle_type());
            int id = articletypeDao.getArticletypeid(a.getArticle_type());
            a.setArticle_typeid(id);
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        a.setUser_id(user_id);
        a.setPub_date(new Date());
        articleDao.addArticle(a);
        response.getWriter().write("123");
        response.setHeader("Refresh", "1;URL=/jsp/index.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
