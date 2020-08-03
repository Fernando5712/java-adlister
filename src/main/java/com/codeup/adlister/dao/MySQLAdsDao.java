package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override
    public List<Ad> adsByUSer(long userId) {
        PreparedStatement stmt = null;
        try {
            String insertQUery = "SELECT * FROM ads WHERE user_id = ?";
            stmt = connection.prepareStatement(insertQUery, RETURN_GENERATED_KEYS);
            stmt.setLong(1,userId);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e){
            throw new RuntimeException("Error getting ad");
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    public Ad findById(long id){
        String findIdQuery = "SELECT * FROM ads WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(findIdQuery);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if(! rs.next()){
                return null;
            }
            return extractAd(rs);
        } catch (SQLException e){
            throw new RuntimeException("Error finding id", e);
        }
    }

    public Ad deleteAd(long id) {
        findById(id);
        try {
            String deleteQuery = "DELETE FROM ads WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(deleteQuery, RETURN_GENERATED_KEYS);
            stmt.setLong(1,id);
            stmt.executeUpdate();
            return null;
        }catch (SQLException e){
            throw new RuntimeException("Error cannot delete");
        }

    }


    @Override
    public List<Ad> search(String query) {
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE title LIKE ? OR description LIKE ?");
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error Searching" , e);
        }
    }
    @Override
    public List<Ad> adsByUser(long userId){
        PreparedStatement stmt = null;
        try {
            String insertQuery = "SELECT * FROM ads WHERE user_id = ?";
            stmt = connection.prepareStatement(insertQuery, RETURN_GENERATED_KEYS);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e){
            throw new RuntimeException("Cannot retrieve ad", e);
        }
    }

    @Override
    public Ad edit(long id, String title){
        findById(id);
        String editQuery = "UPDATE ads SET title = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(editQuery);
            stmt.setString(1, title);
            stmt.setLong(2, id);
            stmt.executeUpdate();
            return null;
        } catch (SQLException e){
            throw new RuntimeException("Error editing title");
        }
    }

    @Override
    public Ad editDescription(long id, String description){
        findById(id);
        String editDescriptionQuery = "UPDATE ads SET description = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(editDescriptionQuery);
            stmt.setString(1, description);
            stmt.setLong(2,id);
            stmt.executeUpdate();
            return null;
        } catch (SQLException e){
            throw new RuntimeException("Error editing description");
        }
    }

    @Override
    public Ad editTitle(long id, String title){
        findById(id);
        String editTitleQuery = "UPDATE ads SET title = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(editTitleQuery);
            stmt.setString(1, title);
            stmt.setLong(2,id);
            stmt.executeUpdate();
            return null;
        }catch (SQLException e){
            throw new RuntimeException("Error changing title");
        }
    }
}
