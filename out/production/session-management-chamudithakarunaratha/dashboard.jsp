<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Course" %>

<!DOCTYPE html>
<html>
<head>
    <title>Course Dashboard</title>
</head>
<body>
<h1>Welcome, ${sessionScope.username}!</h1>

<form action="logout" method="post">
    <input type="submit" value="Logout" />
</form>

<c:if test="${not empty param.success}">
    <p style="color:green;">Successfully enrolled in course: ${param.success}</p>
</c:if>

<h2>Available Courses</h2>
<table border="1">
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Instructor</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${courseList}" var="course">
        <tr>
            <td>${course.courseId}</td>
            <td>${course.courseName}</td>
            <td>${course.instructor}</td>
            <td><a href="enroll?courseId=${course.courseId}">Enroll</a></td>
        </tr>
    </c:forEach>
</table>

<h2>Your Enrolled Courses</h2>
<c:choose>
    <c:when test="${not empty sessionScope.enrolledCourses}">
        <ul>
            <c:forEach items="${sessionScope.enrolledCourses}" var="enrolled">
                <li>${enrolled.courseId} - ${enrolled.courseName} (Instructor: ${enrolled.instructor})</li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>You have not enrolled in any courses.</p>
    </c:otherwise>
</c:choose>

</body>
</html>
