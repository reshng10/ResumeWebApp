package com.mycompany.resumewebapp.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.dao.impl.UserDaoImpl;
import com.mycompany.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private BCrypt.Verifyer verifyer =BCrypt.verifyer();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new UserDaoImpl().findByEmail(email);
        if (user == null) {
            resp.sendRedirect("ErrorController?msg=No such user found");
        }
            if (!(verifyer.verify(password.toCharArray(), user.getPassword().toCharArray()).verified)) {
                resp.sendRedirect("ErrorController?msg=Password is incorrect!");
            } else {
                req.getSession().setAttribute("loggedUser", user);
                resp.sendRedirect("UsersController");
            }
        }

}
