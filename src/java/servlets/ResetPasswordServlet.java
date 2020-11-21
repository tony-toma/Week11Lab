/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 *
 * @author 718707
 */
public class ResetPasswordServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        String uuid = request.getParameter("uuid");
        if(uuid != null) {
            HttpSession session = request.getSession();
            session.setAttribute("uuid", uuid);
            request.getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String newPassword = request.getParameter("new_password");
        
        if(newPassword != null) {
            HttpSession session = request.getSession();
            String uuid = (String) session.getAttribute("uuid");
            AccountService accountService = new AccountService();
            accountService.changePassword(uuid, newPassword);
            session.invalidate();
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        else {
            String email = request.getParameter("user_email");
            String url = request.getRequestURL().toString();
            String template = request.getServletContext().getRealPath("/WEB-INF/emailtemplates/resetpassword.html");
            AccountService service = new AccountService();

            service.resetPassword(email, template, url);
            String message = "A password reset link has been sent to your email";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
     
        

       
    }

}
