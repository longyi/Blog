package Dao;

import Entity.User;

/**
 * @author LongYi
 * @date 2018/10/26 21:08
 * @log: 1.
 */
public interface userDao {
     int adduser(User user);
     int updateuser(User user);
     User getUserInfobyname(String username);
     Boolean isexit(String name);
     User getuserbyid(int id);
}
