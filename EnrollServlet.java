import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Course;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {

    private List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSE101", "Intro to Programming", "Dr. Smith"));
        courses.add(new Course("CSE102", "Data Structures", "Prof. Johnson"));
        courses.add(new Course("CSE103", "Database Systems", "Dr. Allen"));
        return courses;
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }
        String courseId = request.getParameter("courseId");
        if (courseId == null) {
            response.sendRedirect("dashboard");
            return;
        }

        // to Find course by ID
        Course selected = null;
        for (Course c : getAllCourses()) {
            if (c.getCourseId().equals(courseId)) {
                selected = c;
                break;
            }
        }

        if (selected != null) {
            List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
            if (enrolledCourses == null) {
                enrolledCourses = new ArrayList<>();
            }

            // to  Avoid duplicate enrollment
            boolean alreadyEnrolled = enrolledCourses.stream()
                    .anyMatch(c -> c.getCourseId().equals(courseId));
            if (!alreadyEnrolled) {
                enrolledCourses.add(selected);
                session.setAttribute("enrolledCourses", enrolledCourses);
            }
        }

        // to  Redirect with success message
        response.sendRedirect("dashboard?success=" + courseId);




            }
}