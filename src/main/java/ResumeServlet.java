import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ResumeServlet", urlPatterns = "/resume")
public class ResumeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        String content = "<h1>My Resume</h1>";
        content += "<p>I write a lot of code. Check my GitHub</p>";
        content += "<hr><br>";
        content += "<a href='https://github.com/'>My GitHub Profile</a><br>";
        content += "<a href='/portfolio'>My Portfolio</a>";

        PrintWriter out = res.getWriter();
        out.println(content);
    }
}
