package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet {
    protected void doGet(HttpServletResponse res, HttpServletRequest req) throws ServletException, IOException{
        long id = Long.parseLong(req.getParameter("deleteAds"));
        DaoFactory.getAdsDao().deleteAds(id);
        res.sendRedirect("/ads");
    }
}
