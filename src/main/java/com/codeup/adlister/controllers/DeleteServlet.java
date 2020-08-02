package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.DeleteServlet", urlPatterns = "/ads/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        long id = Long.parseLong(req.getParameter("deleteAd"));
        DaoFactory.getAdsDao().deleteAd(id);
        res.sendRedirect("/ads");
    }
}
