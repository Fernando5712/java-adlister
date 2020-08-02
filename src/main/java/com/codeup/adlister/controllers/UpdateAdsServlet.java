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

@WebServlet(name = "controller.UpdateAdsServlet", urlPatterns = "/ads/updateAds")
public class UpdateAdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        long adId = Long.parseLong(req.getParameter("editAd"));
        Ad ad = DaoFactory.getAdsDao().findById(adId);
        req.setAttribute("ad",ad);
        req.getRequestDispatcher("/WEB-INF/ads/editAds.jsp").forward(req,res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        req.getSession().getAttribute("updateAd");
        long adId = Long.parseLong(req.getParameter("updateAd"));
        req.setAttribute("title",DaoFactory.getAdsDao().editTitle(adId,"title"));
        req.setAttribute("description",DaoFactory.getAdsDao().editDescription(adId,"description"));

        String updateAd = req.getParameter("updateAd");
        String adTitle = req.getParameter("title");
        String adDescription = req.getParameter("description");

        if (adTitle != null){
            Ad changeTitle = DaoFactory.getAdsDao().editTitle(adId,adTitle);
        }
        if (adDescription != null){
            Ad changeDescription = DaoFactory.getAdsDao().editDescription(adId,adDescription);
        }
        res.sendRedirect("/ads");

    }
}
