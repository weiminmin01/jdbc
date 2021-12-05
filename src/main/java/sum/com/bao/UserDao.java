package sum.com.bao;

import sum.com.bean.User;

import java.util.List;

public interface UserDao  {
  public boolean add(User user);
  public  void update(User user);
  public void  delete(User user);
  public List<User> getuserlist();
  public User getuser( int id);

}
