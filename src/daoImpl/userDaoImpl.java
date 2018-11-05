package daoImpl;

import Dao.userDao;
import Entity.User;
import Util.Conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LongYi
 * @date 2018/10/26 21:29
 * @log: 1.
 */
//
public class userDaoImpl implements userDao {
    @Override
    public int adduser(User user) {
        Connection conn = Conn.getConn();
        String sql = "insert into user(email,info,password,phone,qq,username) values (?,?,?,?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,user.getEmail());
            pst.setString(2,user.getInfo());
            pst.setString(3,user.getPwd());
            pst.setString(4,user.getPhone());
            pst.setString(5,user.getQq());
            pst.setString(6,user.getUsername());
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

    //更新
    @Override
    public int updateuser(User user) {
        Connection conn = Conn.getConn();
        PreparedStatement pst = null;
        String sql = "update user set email = ?, info = ?, password = ?, phone = ?, qq = ?, username = ? where id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setInt(7,user.getId());
            pst.setString(1,user.getEmail());
            pst.setString(2,user.getInfo());
            pst.setString(3,user.getPwd());
            pst.setString(4,user.getPhone());
            pst.setString(5,user.getQq());
            pst.setString(6,user.getUsername());
            pst.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //ͨ���û�����ѯ
    @Override
    public User getUserInfobyname(String username) {
        Connection conn = Conn.getConn();
        String sql = "select * from user where username = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            rs = pst.executeQuery();
            User user = new User();
            while (rs.next()){
                user.setEmail(rs.getString("email"));
                user.setId(rs.getInt("id"));
                user.setInfo(rs.getString("info"));
                user.setPwd(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setQq(rs.getString("qq"));
                user.setUsername(username);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //��¼
    @Override
    public Boolean isexit(String name) {
        String sql = "SELECT * FROM user WHERE username = ? ";
        Connection conn = Conn.getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,name);
            rs = pst.executeQuery();
            if(rs.next()) {
                pst.close();
                conn.close();
                return true;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getuserbyid(int id) {
        String sql = "SELECT * FROM user WHERE id = ? ";
        Connection conn = Conn.getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            User user = new User();
            while(rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setId(id);
                user.setInfo(rs.getString("info"));
                user.setPwd(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setQq(rs.getString("qq"));
                user.setUsername(rs.getString("username"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
