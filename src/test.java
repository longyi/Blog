import Entity.Article;
import Entity.User;
import daoImpl.articleDaoImpl;
import daoImpl.articletypeDaoImpl;
import daoImpl.userDaoImpl;

import java.util.List;

/**
 * @author LongYi
 * @date 2018/10/27 0:23
 * @log: 1.
 */
public class test {

    public static void main(String[] args) {
        userDaoImpl userDao = new userDaoImpl();
        articletypeDaoImpl articletypeDao = new articletypeDaoImpl();
        /*User u = new User();
        u.setId(1);
        u.setUsername("asdasd1");
        u.setQq("2asdasd");
        u.setPhone("3asdad");
        u.setPwd("asdad4");
        u.setInfo("shishisadasffgj");
        u.setEmail("qwetyuiop");
        userDaoImpl userDao = new userDaoImpl();
        userDao.updateuser(u);
        System.out.println("ok");*/
  /*      articleDaoImpl articleDao = new articleDaoImpl();
       // List<Article> articles = articleDao.findall();
        for (Article a :articles) {
            System.out.println(a.getPub_date()+" "+a.getContent());
        }*/
    }
}
