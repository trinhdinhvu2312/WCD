package servlet;

import dao.ExamDAO;
import model.Exam;
import model.ScheduleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {

    private ExamDAO examDAO = new ExamDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("list".equals(action)) {
            try {
                List<ScheduleDTO> schedules = examDAO.getAllSchedules();
                request.setAttribute("schedules", schedules);
                request.getRequestDispatcher("/WEB-INF/views/schedule-list.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error loading schedules: " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            }
        } else if ("new".equals(action)) {
            try {
                List<Exam> exams = examDAO.getAllExams();
                request.setAttribute("exams", exams);
                request.getRequestDispatcher("/WEB-INF/views/new-schedule.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error loading exams: " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            handleCreateSchedule(request, response);
        }
    }

    private void handleCreateSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters
        String examIdStr = request.getParameter("examId");
        String timeSlot = request.getParameter("timeSlot");
        String location = request.getParameter("location");
        String examSitter = request.getParameter("examSitter");
        String startAtStr = request.getParameter("startAt");
        String endAtStr = request.getParameter("endAt");

        List<String> errors = new ArrayList<>();

        // Validation
        if (examIdStr == null || examIdStr.trim().isEmpty()) {
            errors.add("Exam is required");
        }
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            errors.add("Time slot is required");
        }
        if (location == null || location.trim().isEmpty()) {
            errors.add("Location is required");
        }
        if (examSitter == null || examSitter.trim().isEmpty()) {
            errors.add("Exam sitter is required");
        }
        if (startAtStr == null || startAtStr.trim().isEmpty()) {
            errors.add("Start at is required");
        }
        if (endAtStr == null || endAtStr.trim().isEmpty()) {
            errors.add("End at is required");
        }

        // Date validation
        Timestamp startAt = null;
        Timestamp endAt = null;

        if (!errors.isEmpty()) {
            // Keep input values and show errors
            request.setAttribute("errors", errors);
            request.setAttribute("examId", examIdStr);
            request.setAttribute("timeSlot", timeSlot);
            request.setAttribute("location", location);
            request.setAttribute("examSitter", examSitter);
            request.setAttribute("startAt", startAtStr);
            request.setAttribute("endAt", endAtStr);

            try {
                List<Exam> exams = examDAO.getAllExams();
                request.setAttribute("exams", exams);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("/WEB-INF/views/new-schedule.jsp").forward(request, response);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Date startDate = sdf.parse(startAtStr);
            Date endDate = sdf.parse(endAtStr);

            startAt = new Timestamp(startDate.getTime());
            endAt = new Timestamp(endDate.getTime());

            // Validate dates
            Date today = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String todayStr = dateFormat.format(today);
            String startDateStr = dateFormat.format(startDate);
            String endDateStr = dateFormat.format(endDate);

            if (!startDateStr.equals(endDateStr)) {
                errors.add("Start date and end date must be on the same day");
            }

            if (startDate.before(today)) {
                errors.add("Start date must be equal or greater than today");
            }

            if (endDate.before(today)) {
                errors.add("End date must be equal or greater than today");
            }

        } catch (ParseException e) {
            errors.add("Invalid date format. Please use dd-MM-yyyy hh:mm format");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("examId", examIdStr);
            request.setAttribute("timeSlot", timeSlot);
            request.setAttribute("location", location);
            request.setAttribute("examSitter", examSitter);
            request.setAttribute("startAt", startAtStr);
            request.setAttribute("endAt", endAtStr);

            try {
                List<Exam> exams = examDAO.getAllExams();
                request.setAttribute("exams", exams);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("/WEB-INF/views/new-schedule.jsp").forward(request, response);
            return;
        }

        try {
            int examId = Integer.parseInt(examIdStr);

            // Create scheduler and get generated ID
            int schedulerId = examDAO.createScheduler(timeSlot, location, examSitter, startAt, endAt);

            // Create exam attendance
            examDAO.createExamAttendance(examId, schedulerId);

            // Redirect to schedule list
            response.sendRedirect(request.getContextPath() + "/schedule?action=list");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error creating schedule: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}