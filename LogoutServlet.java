import java.io.IOException;

import javax.servlet.annotation.WebServlet;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        //to invalidate the sessionsss
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

// to remove the usrname cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    cookie.setMaxAge(0); // Expire the cookie
                    response.addCookie(cookie);
                }
            }
        }




//to  Redirect to Login.html file
        response.sendRedirect("login.html");

    }
}