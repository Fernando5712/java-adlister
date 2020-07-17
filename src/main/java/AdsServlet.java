import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdsServlet", urlPatterns = "/ads")
public class AdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Ads adDao = DaoFactory.getAdsDao();
        List<Ad> allAds = new ArrayList<>();
        allAds.add(adDao.all().get(0));
        allAds.add(adDao.all().get(1));
        allAds.add(adDao.all().get(2));
        allAds.add(adDao.all().get(3));
        req.setAttribute("allAds", allAds);
        req.getRequestDispatcher("/ads/index.jsp").forward(req, resp);
    }
}
