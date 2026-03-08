package com.smartagrocare.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartagrocare.dao.UserDAO;
import com.smartagrocare.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(name, email, password);

        UserDAO dao = new UserDAO();

        boolean status = dao.registerUser(user);

        if (status) {
            response.getWriter().println("<h2 style='color:green;'>Registration Successful!</h2>");
        } else {
            response.getWriter().println("<h2 style='color:red;'>Registration Failed!</h2>");
        }
    }
}