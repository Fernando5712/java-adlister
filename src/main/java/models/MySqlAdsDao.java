package models;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlAdsDao implements Ads {
    private Connection connection;

    public void MySQLAdsDao(Config config){
        try{
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ad> all() {
        List<Ad> adList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ads");
            while(rs.next()){
                adList.add(new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adList;
    }

    @Override
    public Long insert(Ad ad) {
        long newAdId = 0;
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO ads (user_id, title, description) VALUES(%d, '%s', '%s')",
                    ad.getUserId(), ad.getTitle(), ad.getDescription()), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            newAdId = rs.getLong(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newAdId;
    }
}
