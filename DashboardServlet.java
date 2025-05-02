import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Course;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {





    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {


        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }


        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSE101", "Intro to Programming", "Dr. Smith"));
        courses.add(new Course("CSE102", "Data Structures", "Prof. Johnson"));
        courses.add(new Course("CSE103", "Database Systems", "Dr. Allen"));


        request.setAttribute("courseList", courses);


        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);




    }
}