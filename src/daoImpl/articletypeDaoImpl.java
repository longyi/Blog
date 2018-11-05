package daoImpl;

import Dao.articletypeDao;

import Util.Conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LongYi
 * @date 2018/10/28 15:37
 * @log: 1.
 */
public class articletypeDaoImpl  implements articletypeDao {
    @Override
    public int addArticletype(String type) {
        Connection conn = Conn.getConn();
        String sql = "insert into article_type(articletype) values (?)";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,type);
            pst.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
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
    public int getArticletypeid(String type) {
        String sql = "select id from article_type where articletype=?";
        PreparedStatement pst = null;
        Connection conn = Conn.getConn();
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, type);
            rs = pst.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            pst.close();
            conn.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getArticletype(int id) {
        String sql = "select articletype from article_type where id=?";
        PreparedStatement pst = null;
        Connection conn = Conn.getConn();
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            String type = null;
            while (rs.next()) {
                type = rs.getString("articletype");
            }
            rs.close();
            pst.close();
            conn.close();
            return type;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
