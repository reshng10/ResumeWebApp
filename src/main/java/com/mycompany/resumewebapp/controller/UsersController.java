package com.mycompany.resumewebapp.controller;

import com.mycompany.dao.impl.UserDaoImpl;
import com.mycompany.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersController", urlPatterns = "/UsersController")
public class UsersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =request.getParameter("name");
        String surName =request.getParameter("surname");

        String nationalityIdStr =request.getParameter("id");
        Integer nationalityId = null;

        if(nationalityIdStr!=null && !nationalityIdStr.trim().isEmpty()){
            nationalityId=Integer.parseInt(nationalityIdStr);
        }
        List<User> userList =new UserDaoImpl().getAll(name,surName,nationalityId);
             request.setAttribute("userList",userList);

        request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
