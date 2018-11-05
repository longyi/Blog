package Dao;

import Entity.Article;

import java.util.List;

/**
 * @author LongYi
 * @date 2018/10/26 21:11
 * @log: 1.
 */
public interface articleDao {
    void addArticle(Article article);//添加文章
    List<Article> findall(int id);//查询当前登录用户的所有文章
    Article findbytitle(String title);
    Article findbyid(int id);
    void updatearticle(Article article);
    void delAriticle(int id);
    List<Article> find();
}
