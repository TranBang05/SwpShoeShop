package Controller;

import Model.User;
import OTPSendMail.SendMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserVerify", value = "/userVerify")
public class UserVerify extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()){
            String username = request.getParameter("username");
            String email=request.getParameter("email");
            String password = request.getParameter("password");
            SendMail sm = new SendMail();
            String code=sm.getRandom();
            User user = new User(username,password, email,code);
            boolean test=sm.sendEmail(user);
            if(test){
                HttpSession session = request.getSession();
                session.setAttribute("authcode",user);
                response.sendRedirect("verify.jsp");
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}