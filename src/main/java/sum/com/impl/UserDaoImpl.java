package sum.com.impl;

import sum.com.bean.User;
import sum.com.bao.BaseDao;
import sum.com.bao.UserDao;
import sun.plugin.dom.core.Element;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {



    @Override
    public boolean add(User user) {
        boolean flag = false;
        try {
            String sql = "insert into smbms_user(id,userCode,userName,userPassword,gender,birthday,phone,address, userRole, createdBy,creationDate,modifyBy) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getId(),user.getUserCode(),user.getUserName(),user.getUserPassword(),user.getGender(),
                    user.getBirthday(),user.getPhone(),user.getAddress(),user.getUserRole(),user.getCreatedBy(),user.getCreationDate(),user.getModifyBy()};
            int i = this.executeUpdate(sql,params);
            if (i > 0) {
                System.out.println("执行成功");
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResource();
        }
        return flag;

    }

    @Override
    public void update(User user) {
        try {
            String sql = "update smbms_user set userName=? where id=?";
            Object[] params = {user.getUserName(),user.getId()};
            int i = this.executeUpdate(sql,params);
            if (i > 0) {
                System.out.println("执行成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeResource();
        }
    }

    @Override
    public void delete(User user) {
        try {
            String sql = "delete from smbms_user where id=?";
            Object[] params ={user.getId()};
            int i = this.executeUpdate(sql,params);
            if (i > 0) {
                System.out.println("执行成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            this.closeResource();
        }
    }

    @Override
    public List<User> getuserlist() {

        List<User> list = new ArrayList<User>();
        try {
              User user=null;
            String sql = "select * from smbms_user";
            Object[] params = {};
            rs = this.executeSQL(sql, params);
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String userCode = rs.getString("userCode");
                String userName=rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                int gender = rs.getInt("gender");
                Timestamp birthday = rs.getTimestamp("birthday");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int userRole = rs.getInt("userRole");
                int createdBy = rs.getInt("createdBy");
                Timestamp creationDate = rs.getTimestamp("creationDate");
                int modifyBy = rs.getInt("modifyBy");
                Timestamp modifyDate = rs.getTimestamp("modifyDate");
                user = new User();
                user.setId(id1);
                user.setUserCode(userCode);
                user.setUserName(userName);
                user.setUserPassword(userPassword);
                user.setGender(gender);
                user.setBirthday( birthday);
                user.setPhone(phone);
                user.setAddress(address);
                user.setUserRole(userRole);
                user.setCreatedBy(createdBy);
                user.setCreationDate(creationDate);
                user.setModifyBy(modifyBy);
                user.setModifyDate(modifyDate);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource();
        }
        for(User u:list){
            System.out.println(u.toString());
        }
        return list;

    }

    @Override
    public User getuser(int id) {
        User user = null;
        try {
            String sql = "select * from  smbms_user where id=?";
            Object[] params = {id};
            ResultSet rs = this.executeSQL(sql, params);
            if (rs.next()) {
                int id1 = rs.getInt("id");
                String userCode = rs.getString("userCode");
                String userName=rs.getString("userName");
                String userPassword = rs.getString("userPassword");
                int gender = rs.getInt("gender");
                Date birthday = rs.getDate("birthday");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int userRole = rs.getInt("userRole");
                int createdBy = rs.getInt("createdBy");
                Timestamp creationDate = rs.getTimestamp("creationDate");
                int modifyBy = rs.getInt("modifyBy");
                Timestamp modifyDate = rs.getTimestamp("modifyDate");
                user = new User();
                user.setId(id1);
                user.setUserCode(userCode);
                user.setUserName(userName);
                user.setUserPassword(userPassword);
                user.setGender(gender);
                user.setBirthday( birthday);
                user.setPhone(phone);
                user.setAddress(address);
                user.setUserRole(userRole);
                user.setCreatedBy(createdBy);
                user.setCreationDate( creationDate);
                user.setModifyBy(modifyBy);
                user.setModifyDate( modifyDate);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            this.closeResource();
        }
        System.out.println(user);
        return user ;

    }

    public static void main(String[] args) {
     UserDao userDao=new UserDaoImpl();
     //删除
   /* User user =new User();
   user.setId(18);
   userDao.delete(user);*/
   //查询
  /*userDao.getuser(6);
    userDao.getuserlist();*/
   //添加
     /*   User user = new User();
        user.setId(20);
        user.setUserName("小张");
        userDao.add(user);*/
  //修改
      User user = new User();
        user.setUserName("柱子");
        user.setId(20);
        userDao.update(user);
            }
}

