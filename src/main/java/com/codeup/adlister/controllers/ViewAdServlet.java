package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

@WebServlet(name = "controller.ViewAd", urlPatterns = "/ViewAd")
public class ViewAdServlet extends HttpServlet {
    protected void doGet(HttpServletResponse res, HttpServletRequest req) throws ServletException, IOException{
        long adId = Long.parseLong(req.getParameter("AdToView"));
        Ad ad = DaoFactory.getAdsDao().findById(adId);
        req.setAttribute("ad",ad);
        req.getRequestDispatcher("WEB-INF/ads/viewAnAd.jsp").forward(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        req.getSession().getAttribute("adToView");
        long adId = Long.parseLong(req.getParameter("adToView"));
        req.setAttribute("title",DaoFactory.getAdsDao().edit(adId, "AD TITLE"));
        req.setAttribute("description", DaoFactory.getAdsDao().editDescription(adId,"DESCRIBE AD"));
        String update = req.getParameter("Update Ad");
        String adTitle = req.getParameter("TItle");
        String adDescription = req.getParameter("description");
        Ad updateTitle = DaoFactory.getAdsDao().edit(adId, adTitle);
        res.sendRedirect("/ads");
        Ad updateDescription = DaoFactory.getAdsDao().editDescription(adId,adDescription);
        res.sendRedirect("/ads");
    }

}
