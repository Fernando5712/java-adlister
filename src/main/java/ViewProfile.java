import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (name = "ViewProfile", urlPatterns = "/profile")
public class ViewProfile {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession();

        if (session.getAttribute("user") == null){
            res.sendRedirect("/login");
        } else {
            req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req,res);
        }
    }
}
