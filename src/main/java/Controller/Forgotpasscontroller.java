package Controller;

import DAL.Validation;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(name = "Forgotpasscontroller", value = "/forgot")
public class Forgotpasscontroller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("ForgotPassword.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nhan thong tin
        String err1 = Validation.checkEmpty(req.getParameter("username"));
        String err2 = Validation.checkEmpty(req.getParameter("email"));

        String username = req.getParameter("username");
        String email = req.getParameter("email");

        req.setAttribute("username", username);
        req.setAttribute("email", email);


        if (err1 != "" || err2 != "") {

            System.out.println("err");
            req.getRequestDispatcher("ForgotPassword.jsp").forward(req, resp);
        }

        User u1 = new User();
        User user = u1.getUserByAccount2(username);
        System.out.println(user.getUsername());
        System.out.println(username);
        if (!(user.getUsername().trim().equals(username.trim()))) {
            System.out.println("wrong account");
            req.setAttribute("err1", "Wrong account!");
            req.getRequestDispatcher("ForgotPassword.jsp").forward(req, resp);
            return;
        }

        System.out.println(user.getEmail());
        System.out.println(email);

        if (!(user.getEmail().equals(email))) {
            System.out.println("wrong email");
            req.setAttribute("err2", "Wrong email!");
            req.getRequestDispatcher("ForgotPassword.jsp").forward(req, resp);
        }

        System.out.println("here");
        req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);
    }
}