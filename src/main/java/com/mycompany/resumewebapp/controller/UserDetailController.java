package com.mycompany.resumewebapp.controller;

import com.mycompany.dao.impl.SkillDaoImpl;
import com.mycompany.dao.impl.UserDaoImpl;
import com.mycompany.dao.inter.SkillDaoInterface;
import com.mycompany.dao.inter.UserDaoInterface;
import com.mycompany.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDetailController", urlPatterns = "/UserDetail")
public class UserDetailController extends HttpServlet {
    public UserDaoInterface udi = new UserDaoImpl();
    public SkillDaoInterface sdi = new SkillDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String uidStr = request.getParameter("uid");
            System.out.println(uidStr);
            if (uidStr == null || uidStr.trim().isEmpty()) {

                throw new Exception("enter valid id!!!");
            }


            Integer uid = Integer.parseInt(request.getParameter("uid"));


            User u = new UserDaoImpl().getById(uid);
            request.setAttribute("user", u);
            request.getRequestDispatcher("userdetail.jsp").forward(request, response);

        } catch (Exception ex) {
            response.sendRedirect("error.jsp?msg=" + ex.getMessage());
            return;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        int id = Integer.valueOf(req.getParameter("uc_id"));
        String action = req.getParameter("action");
        if (action.equals("submit")) {
            String name = (req.getParameter("name"));
            String surname = req.getParameter("surname");
            User user = udi.getById(id);

            user.setName(name);
            user.setSurname(surname);
            udi.updateUser(user);


            resp.sendRedirect("UsersController");
        }
        if (action.equals("delete")) {
            udi.removeUser(id);
            resp.sendRedirect("UsersController");
        }

    }
}