package Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author LongYi
 * @date 2018/10/26 20:35
 * @log: 1.建立数据库连接
 */
public class Conn {
    private static String url = "jdbc:mysql://localhost:3306/blog";
    private static String name = "root";
    private static String pwd = "123456";
    private static String driver = "com.mysql.jdbc.Driver";

    public static Connection getConn(){
        Connection conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,name,pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
