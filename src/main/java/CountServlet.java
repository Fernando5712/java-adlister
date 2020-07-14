import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CountServlet", urlPatterns = "/count")
public class CountServlet extends HttpServlet {
    int count = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String restart = request.getParameter("reset");

        PrintWriter out = response.getWriter();

        if (restart != null){
            count = 0;
        }

        count += 1;

        out.println("<h1>Hey you've seen this page, " + count + " times. Hope you enjoy it!</h1>");
    }

}
