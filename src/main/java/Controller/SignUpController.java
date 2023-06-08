package Controller;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUpController", value = "/signup")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = resp.getWriter()) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String repassword = req.getParameter("repassword");
            String email = req.getParameter("email");

            String err="";
            User u= new User(username);
            boolean checkk=u.checkAccount();


            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                req.setAttribute("username", username);
                req.setAttribute("password", password);
                req.setAttribute("repassword", repassword);
                req.setAttribute("email", email);
                req.setAttribute("messEmpty", "Thông tin của bạn đăng kí đang để trống");
                req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
            }else if (!repassword.equals(password)) {
                req.setAttribute("username",username);
                req.setAttribute("password", password);
                req.setAttribute("repassword", repassword);
                req.setAttribute("messPass", "Mật Khẩu Không Khớp");
                req.getRequestDispatcher("SignUp.jsp").forward(req, resp);

            }else if(checkk){
                err="Tài khoản của bạn đã tồn tại!";
                req.setAttribute("err", err);
                req.getRequestDispatcher("SignUp.jsp").forward(req, resp);
            }else{
                User us=new User(username, password, email);
                us.addUser();

                req.setAttribute("username", username);
                req.setAttribute("password", password);
                req.setAttribute("repassword", repassword);
                req.setAttribute("email", email);


                req.setAttribute("messU", "Đăng kí thành công");
                req.getRequestDispatcher("Login.jsp").forward(req, resp);
            }
        }
    }
}