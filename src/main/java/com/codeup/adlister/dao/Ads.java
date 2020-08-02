package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    List<Ad> adsByUSer(long userId);

    Ad findById(long id);

    Ad deleteAd(long id);

    List<Ad> search(String query);

    List<Ad> adsByUser(long userId);

    Ad edit(long id, String title);

    Ad editDescription(long id, String description);
}
