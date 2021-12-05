package sum.com.servlet;

import sum.com.bean.User;
import sum.com.impl.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "Servlet", value = "/Servlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     request.setCharacterEncoding("utf-8");
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String userpassword = request.getParameter("userPassword");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        User user = new User();
        UserDaoImpl userDao = new UserDaoImpl();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userpassword);
        user.setPhone(phone);
        user.setAddress(address);
        userDao.add(user);


    }
}
