package Controller;

import DAL.Validation;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/changepass")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("SignUp.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nhan thong tin
        String err1 = Validation.checkEmpty(req.getParameter("username"));
        String err2 = Validation.checkEmpty(req.getParameter("newpassword"));
        String err3 = Validation.checkEmpty(req.getParameter("repassword"));
        String username = req.getParameter("username");
        String newpassword = req.getParameter("newpassword");
        String repassword = req.getParameter("repassword");
        req.setAttribute("account", username);
        req.setAttribute("pass", newpassword);
        req.setAttribute("repass", repassword);

        if (err1 != "" || err2 != "" || err3 != "") {
//            req.setAttribute("err1", String.valueOf(err1));
//            req.setAttribute("err2", String.valueOf(err2));
//            req.setAttribute("err3", String.valueOf(err3));
            req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);
            return;
        }

        User u = new User(username, newpassword);
//        System.out.println("account");
//        System.out.println(u.getUserByAccount2(account));
//        User oldUser = u.getUserByAccount2(account);
//        if (u.getUserByAccount2(account) == null) {
//            req.setAttribute("err1", "User do not exist!");
//            req.getRequestDispatcher("changePassword.jsp").forward(req, resp);
//        }

        if (!newpassword.equals(repassword)) {
            req.setAttribute("err3", "Confirm password is not match!");
            req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);
            return;
        }

        // Xy li thong tin
        u.updatePassword(u);
        System.out.println(username);
        System.out.println(newpassword);
        req.setAttribute("username", username);
        req.setAttribute("newpassword", newpassword);
        req.getRequestDispatcher("Login.jsp").forward(req, resp);
    }
}