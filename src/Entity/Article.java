package Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LongYi
 * @date 2018/10/26 20:58
 * @log: 1.
 */
public class Article implements Serializable {
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String content;
    private  String title;
    private Date pub_date;
    private int article_typeid;
    private String article_type;

    public int getArticle_typeid() {
        return article_typeid;
    }

    public void setArticle_typeid(int article_typeid) {
        this.article_typeid = article_typeid;
    }

    public String getArticle_type() {
        return article_type;
    }

    public void setArticle_type(String article_type) {
        this.article_type = article_type;
    }

    private int user_id;
    private  String user_name;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    private String imgurl;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
