package Controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.User;

import java.io.IOException;

@WebServlet(name = "Logincontroller", value = "/login")
public class Logincontroller extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //nhan thong tin
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        String err="";
        //su ly thong tin
        User u= new User(username, password);

        boolean check=u.checkUser();

        if(check){
            //luu thong tin login vao session
            HttpSession session=req.getSession();
            session.setAttribute("username", u);
            req.setAttribute("username",username);
            req.getRequestDispatcher("Home.jsp").forward(req, resp);

        }else{
            err="Account exit";
            req.setAttribute("err", err);
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }

    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }
}