import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;



import java.util.HashMap;
import java.util.Map;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private Map<String, String> users = new HashMap<>();
    @Override
    public void init() throws ServletException {
        // Hardcoded users
        users.put("student1", "pass1");
        users.put("student2", "pass2");
    }





    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {

            // Valid user, create session and cookie
            HttpSession session = request.getSession();
            session.setAttribute("username", username);



            //    - Store usrname in cookie
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60); // 1 hour
            response.addCookie(userCookie);


            //    - Rediret to DashboardServlet
            response.sendRedirect("dashboard");
        }
        else {
            // to checkk Invalid credentials
            response.sendRedirect("login.html?error=1");
        }


        
    }
}