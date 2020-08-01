package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.Users;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsUserServlet", urlPatterns = "/ads/adsUser")
public class AdsUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        User user = (User)req.getSession().getAttribute("user");
        req.setAttribute("ads", DaoFactory.getAdsDao().adsByUser(user.getId()));
        req.getRequestDispatcher("/WEB-INF/ads/adsUser.jsp").forward(req, res);
    }
}
