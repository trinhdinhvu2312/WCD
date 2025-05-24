<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Schedule List - Exam Attendant Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        .back-link {
            display: inline-block;
            margin-bottom: 20px;
            padding: 8px 16px;
            background-color: #6c757d;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 14px;
        }
        .back-link:hover {
            background-color: #545b62;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #333;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .no-schedules {
            text-align: center;
            padding: 40px;
            color: #666;
            font-style: italic;
        }
        .duration {
            font-weight: bold;
            color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/home" class="back-link">← Back home</a>

        <h1>Exam Schedules</h1>

        <c:choose>
            <c:when test="${not empty schedules}">
                <table>
                    <thead>
                        <tr>
                            <th>Exam Name</th>
                            <th>Duration (minutes)</th>
                            <th>Time Slot</th>
                            <th>Exam Sitter</th>
                            <th>Start At</th>
                            <th>End At</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="schedule" items="${schedules}">
                            <tr>
                                <td>${schedule.examName}</td>
                                <td class="duration">${schedule.duration} min</td>
                                <td>${schedule.timeSlot}</td>
                                <td>${schedule.examSitter}</td>
                                <td>
                                    <fmt:formatDate value="${schedule.startAt}" pattern="dd-MM-yyyy HH:mm" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${schedule.endAt}" pattern="dd-MM-yyyy HH:mm" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="no-schedules">
                    <p>No exam schedules found.</p>
                    <p><a href="${pageContext.request.contextPath}/schedule?action=new">Create a new schedule</a></p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>