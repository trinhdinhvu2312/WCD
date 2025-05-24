<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exam Attendant Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
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
        .exam-image {
            text-align: center;
            margin: 30px 0;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            border: 2px dashed #ccc;
        }
        .exam-image p {
            font-size: 18px;
            color: #666;
            margin: 0;
        }
        .navigation {
            display: flex;
            justify-content: center;
            gap: 30px;
            margin-top: 40px;
        }
        .nav-link {
            display: inline-block;
            padding: 15px 30px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .nav-link:hover {
            background-color: #0056b3;
        }
        .description {
            text-align: center;
            margin: 20px 0;
            color: #555;
            line-height: 1.6;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Exam Attendant Application</h1>

        <div class="description">
            <p>Manage your exam schedules efficiently with our comprehensive exam management system.</p>
        </div>

        <div class="exam-image">
            <img src="${pageContext.request.contextPath}/assets/images/exam-schedule.jpeg" alt="Exam Schedule" style="max-width: 100%; height: auto;">
        </div>

        <div class="navigation">
            <a href="${pageContext.request.contextPath}/schedule?action=list" class="nav-link">
                📋 View Schedule
            </a>
            <a href="${pageContext.request.contextPath}/schedule?action=new" class="nav-link">
                ➕ New Schedule
            </a>
        </div>

        <div class="description" style="margin-top: 30px;">
            <p><strong>View Schedule:</strong> See all existing exam schedules that you can take or review</p>
            <p><strong>New Schedule:</strong> Create a new exam schedule with custom time slots and duration</p>
        </div>
    </div>
</body>
</html>