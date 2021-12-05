package sum.com.bao;

import sum.com.util.ConfigManager;

import java.sql.*;

public class BaseDao {
    protected Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;



    public boolean getConnection() {
   
        String driver = ConfigManager.getInstance().getString("driver");
        String url = ConfigManager.getInstance().getString("url");
        String username = ConfigManager.getInstance().getString("Username");
        String password = ConfigManager.getInstance().getString("password");
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



//编写增删改公共方法
    public int executeUpdate(String sql, Object[] params) {
        int updateRows = 0;
        if(getConnection()){
            try {
                ps=conn.prepareStatement(sql);
                for(int i=0;i<params.length;i++){
                    ps.setObject(i+1, params[i]);
                }
                updateRows=ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return updateRows;
    }

//编写查询公共方法
    public ResultSet executeSQL(String sql,Object[] params) {
        if(getConnection()){
            try {
                ps=conn.prepareStatement(sql);

                for(int i=0;i<params.length;i++){
                    ps.setObject(i+1, params[i]);
                }
                rs=ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }


    public boolean closeResource() {
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
