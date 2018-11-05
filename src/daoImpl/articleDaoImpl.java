package daoImpl;

import Dao.articleDao;
import Entity.Article;
import Util.Conn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LongYi
 * @date 2018/10/26 21:29
 * @log: 1.
 */
public class articleDaoImpl implements articleDao {
    @Override
    public void addArticle(Article article) {
        Connection conn = Conn.getConn();
        String sql = "insert into article(content, pub_date, title, articletype_id, user_id,user_name,imgurl) values(?,?,?,?,?,?,?) ";
        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,article.getContent());
            pst.setDate(2, new java.sql.Date(article.getPub_date().getTime()));
            pst.setString(3,article.getTitle());
            pst.setInt(4,article.getArticle_typeid());
            pst.setInt(5,article.getUser_id());
            pst.setString(6,article.getUser_name());
            pst.setString(7,article.getImgurl());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Article> findall(int user_id) {
        Connection conn = Conn.getConn();
        String sql = "select * from article where user_id = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<Article>();
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(1,user_id);
            rs = pst.executeQuery();
            while (rs.next()){
                Article article = new Article();
                article.setContent(rs.getString("content"));
                article.setArticle_typeid(rs.getInt("articletype_id"));
                article.setPub_date(rs.getDate("pub_date"));
                article.setUser_id(rs.getInt("user_id"));
                article.setTitle(rs.getString("title"));
                article.setId(rs.getInt("id"));
                article.setUser_name(rs.getString("user_name"));
                article.setImgurl((rs.getString("imgurl")));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }

    @Override
    public Article findbytitle(String title) {
        String sql = "select id,title,content,pub_date,user_id, user_name,imgurl,articletype_id from article where title = ?";
        PreparedStatement pst = null;
        Connection conn = Conn.getConn();
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,title);
            rs = pst.executeQuery();
            Article article = new Article();
            while (rs.next()){
                article.setId(rs.getInt("id"));
                article.setArticle_typeid(rs.getInt("articletype_id"));
                article.setContent(rs.getString("content"));
                article.setPub_date(rs.getDate("pub_date"));
                article.setUser_id(rs.getInt("user_id"));
                article.setUser_name(rs.getString("user_name"));
                article.setImgurl((rs.getString("imgurl")));
                article.setTitle(title);
            }
            pst.close();
            conn.close();
            return article;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Article findbyid(int id) {
        String sql = "select id,title,content,pub_date,user_id, user_name,imgurl,articletype_id from article where id = ?";
        PreparedStatement pst = null;
        Connection conn = Conn.getConn();
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            Article article = new Article();
            while (rs.next()){
                article.setId(id);
                article.setArticle_typeid(rs.getInt("articletype_id"));
                article.setContent(rs.getString("content"));
                article.setPub_date(rs.getDate("pub_date"));
                article.setUser_id(rs.getInt("user_id"));
                article.setUser_name(rs.getString("user_name"));
                article.setImgurl((rs.getString("imgurl")));
                article.setTitle(rs.getString("title"));
            }
            pst.close();
            conn.close();
            return article;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatearticle(Article article) {
        Connection conn = Conn.getConn();
        PreparedStatement pst = null;
        String sql = "update article set content = ?, pub_date = ?, title = ?, articletype_id = ? ,imgurl =? where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(6,article.getId());
            pst.setString(1,article.getContent());
            pst.setDate(2, new java.sql.Date(article.getPub_date().getTime()));
            pst.setString(3,article.getTitle());
            pst.setInt(4,article.getArticle_typeid());
            pst.setString(5,article.getImgurl());
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delAriticle(int id) {
        Connection conn = Conn.getConn();
        PreparedStatement pst = null;
        String sql = "delete from article where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> find() {
        Connection conn = Conn.getConn();
        String sql = "select * from article ";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Article> articles = new ArrayList<Article>();
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setContent(rs.getString("content"));
                article.setArticle_typeid(rs.getInt("articletype_id"));
                article.setPub_date(rs.getDate("pub_date"));
                article.setUser_id(rs.getInt("user_id"));
                article.setTitle(rs.getString("title"));
                article.setId(rs.getInt("id"));
                article.setUser_name(rs.getString("user_name"));
                article.setImgurl(rs.getString("imgurl"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }
}
