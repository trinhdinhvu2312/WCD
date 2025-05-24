<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Schedule - Exam Attendant Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
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
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"], input[type="datetime-local"], select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        input:focus, select:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0,123,255,0.3);
        }
        .error {
            color: #dc3545;
            font-size: 12px;
            margin-top: 5px;
        }
        .error-input {
            border-color: #dc3545;
        }
        .button-group {
            display: flex;
            gap: 15px;
            justify-content: center;
            margin-top: 30px;
        }
        .btn {
            padding: 12px 30px;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background-color: #545b62;
        }
        .required {
            color: #dc3545;
        }
        .info-text {
            font-size: 12px;
            color: #666;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Create New Schedule</h1>

        <form action="${pageContext.request.contextPath}/schedule" method="post">
            <input type="hidden" name="action" value="create">

            <div class="form-group">
                <label for="examId">Exam <span class="required">*</span></label>
                <select id="examId" name="examId" class="${not empty errors and errors.contains('Exam is required') ? 'error-input' : ''}">
                    <option value="">Select an exam</option>
                    <c:forEach var="exam" items="${exams}">
                        <option value="${exam.id}" ${param.examId == exam.id ? 'selected' : ''}>${exam.name}</option>
                    </c:forEach>
                </select>
                <c:if test="${not empty errors and errors.contains('Exam is required')}">
                    <div class="error">Exam is required</div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="timeSlot">Time Slot <span class="required">*</span></label>
                <input type="text" id="timeSlot" name="timeSlot" value="${param.timeSlot}"
                       placeholder="e.g., 10:00-11:30"
                       class="${not empty errors and errors.contains('Time slot is required') ? 'error-input' : ''}">
                <div class="info-text">Format: HH:MM-HH:MM</div>
                <c:if test="${not empty errors and errors.contains('Time slot is required')}">
                    <div class="error">Time slot is required</div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="location">Location <span class="required">*</span></label>
                <input type="text" id="location" name="location" value="${param.location}"
                       placeholder="e.g., Lab Bill Gates"
                       class="${not empty errors and errors.contains('Location is required') ? 'error-input' : ''}">
                <c:if test="${not empty errors and errors.contains('Location is required')}">
                    <div class="error">Location is required</div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="examSitter">Exam Sitter <span class="required">*</span></label>
                <input type="text" id="examSitter" name="examSitter" value="${param.examSitter}"
                       placeholder="e.g., Tran"
                       class="${not empty errors and errors.contains('Exam sitter is required') ? 'error-input' : ''}">
                <c:if test="${not empty errors and errors.contains('Exam sitter is required')}">
                    <div class="error">Exam sitter is required</div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="startAt">Start At <span class="required">*</span></label>
                <input type="text" id="startAt" name="startAt" value="${param.startAt}"
                       placeholder="dd-MM-yyyy HH:mm"
                       class="${not empty errors and (errors.contains('Start at is required') or errors.contains('Invalid date format. Please use dd-MM-yyyy hh:mm format') or errors.contains('Start date and end date must be on the same day') or errors.contains('Start date must be equal or greater than today')) ? 'error-input' : ''}">
                <div class="info-text">Format: dd-MM-yyyy HH:mm (e.g., 20-12-2023 10:00)</div>
                <c:if test="${not empty errors and errors.contains('Start at is required')}">
                    <div class="error">Start at is required</div>
                </c:if>
                <c:if test="${not empty errors and errors.contains('Invalid date format. Please use dd-MM-yyyy hh:mm format')}">
                    <div class="error">Invalid date format. Please use dd-MM-yyyy HH:mm format</div>
                </c:if>
                <c:if test="${not empty errors and errors.contains('Start date and end date must be on the same day')}">
                    <div class="error">Start date and end date must be on the same day</div>
                </c:if>
                <c:if test="${not empty errors and errors.contains('Start date must be equal or greater than today')}">
                    <div class="error">Start date must be equal or greater than today</div>
                </c:if>
            </div>

            <div class="form-group">
                <label for="endAt">End At <span class="required">*</span></label>
                <input type="text" id="endAt" name="endAt" value="${param.endAt}"
                       placeholder="dd-MM-yyyy HH:mm"
                       class="${not empty errors and (errors.contains('End at is required') or errors.contains('Invalid date format. Please use dd-MM-yyyy hh:mm format') or errors.contains('Start date and end date must be on the same day') or errors.contains('End date must be equal or greater than today')) ? 'error-input' : ''}">
                <div class="info-text">Format: dd-MM-yyyy HH:mm (e.g., 20-12-2023 11:30)</div>
                <c:if test="${not empty errors and errors.contains('End at is required')}">
                    <div class="error">End at is required</div>
                </c:if>
                <c:if test="${not empty errors and errors.contains('Invalid date format. Please use dd-MM-yyyy hh:mm format')}">
                    <div class="error">Invalid date format. Please use dd-MM-yyyy HH:mm format</div>
                </c:if>
                <c:if test="${not empty errors and errors.contains('Start date and end date must be on the same day')}">
                    <div class="error">Start date and end date must be on the same day</div>
                </c:if>
                <c:if test="${not empty errors and errors.contains('End date must be equal or greater than today')}">
                    <div class="error">End date must be equal or greater than today</div>
                </c:if>
            </div>

            <div class="button-group">
                <a href="${pageContext.request.contextPath}/schedule?action=list" class="btn btn-secondary">Cancel</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
</body>
</html>