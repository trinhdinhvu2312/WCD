package dao;

import model.Exam;
import model.ScheduleDTO;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {

    public List<Exam> getAllExams() throws SQLException {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM exams";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Exam exam = new Exam();
                exam.setId(rs.getInt("id"));
                exam.setName(rs.getString("name"));
                exam.setDuration(rs.getInt("duration"));
                exam.setDescription(rs.getString("description"));
                exam.setCreatedAt(rs.getTimestamp("created_at"));
                exam.setUpdatedAt(rs.getTimestamp("updated_at"));
                exams.add(exam);
            }
        }
        return exams;
    }

    public List<ScheduleDTO> getAllSchedules() throws SQLException {
        List<ScheduleDTO> schedules = new ArrayList<>();
        String sql = "SELECT e.name, e.duration, s.time_slot, s.exam_sitter, s.start_at, s.end_at " +
                    "FROM exams e " +
                    "JOIN exam_attendance ea ON e.id = ea.exam_id " +
                    "JOIN schedulers s ON ea.scheduler_id = s.id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ScheduleDTO schedule = new ScheduleDTO();
                schedule.setExamName(rs.getString("name"));
                schedule.setDuration(rs.getInt("duration"));
                schedule.setTimeSlot(rs.getString("time_slot"));
                schedule.setExamSitter(rs.getString("exam_sitter"));
                schedule.setStartAt(rs.getTimestamp("start_at"));
                schedule.setEndAt(rs.getTimestamp("end_at"));
                schedules.add(schedule);
            }
        }
        return schedules;
    }

    public int createScheduler(String timeSlot, String location, String examSitter,
                              Timestamp startAt, Timestamp endAt) throws SQLException {
        String sql = "INSERT INTO schedulers (time_slot, location, exam_sitter, start_at, end_at) " +
                    "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, timeSlot);
            ps.setString(2, location);
            ps.setString(3, examSitter);
            ps.setTimestamp(4, startAt);
            ps.setTimestamp(5, endAt);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating scheduler failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating scheduler failed, no ID obtained.");
                }
            }
        }
    }

    public void createExamAttendance(int examId, int schedulerId) throws SQLException {
        String sql = "INSERT INTO exam_attendance (exam_id, scheduler_id, student_roll_no) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, examId);
            ps.setInt(2, schedulerId);
            ps.setString(3, null);

            ps.executeUpdate();
        }
    }
}