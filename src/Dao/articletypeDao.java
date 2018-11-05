package Dao;

import Entity.Article;
import Entity.Article_type;

/**
 * @author LongYi
 * @date 2018/10/28 15:36
 * @log: 1.
 */
public interface articletypeDao {
    int addArticletype(String type);
    int getArticletypeid(String name);
    String getArticletype(int id);
}
