package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.Dashboard;

public class DashboardDao {
	private Connection connection;

	public DashboardDao() {
		connection = Database.getInstance().getConnection();
	}

	public List<Dashboard> getDashboard() {
		List<Dashboard> dashboardList = new ArrayList<>();
		String sql = "SELECT "
				+ "    s.student_id,"
				+ "    CONCAT(sp.first_name, ' ', sp.last_name) AS name,"
				+ "    c.course_name,"
				+ "    sf.paid_fee,"
				+ "    (c.total_fee - sf.paid_fee) AS pending_fee,"
				+ "    c.total_fee,"
				+ "    GROUP_CONCAT(DISTINCT sub.subject_name SEPARATOR ', ') AS subjects,"
				+ "    GROUP_CONCAT(DISTINCT CONCAT(tp.first_name, ' ', tp.last_name) SEPARATOR ', ') AS teachers"
				+ "FROM "
				+ "    students "
				+ "JOIN "
				+ "    students_profile sp ON s.student_id = sp.student_id"
				+ "JOIN "
				+ "    student_course sc ON s.student_id = sc.student_id"
				+ "JOIN "
				+ "    course c ON sc.course_id = c.course_id"
				+ "JOIN "
				+ "    student_fees sf ON s.student_id = sf.student_id"
				+ "LEFT JOIN "
				+ "    course_subject cs ON c.course_id = cs.course_id"
				+ "LEFT JOIN "
				+ "    subject sub ON cs.subject_id = sub.subject_id"
				+ "LEFT JOIN "
				+ "    subject_teacher st ON sub.subject_id = st.subject_id"
				+ "LEFT JOIN "
				+ "    teachers t ON st.teacher_id = t.teacher_id"
				+ "LEFT JOIN "
				+ "    teacher_profile tp ON t.teacher_id = tp.teacher_id"
				+ "GROUP BY "
				+ "    s.student_id;"
				+ "";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); {

				int srNo = 1;
				while (rs.next()) {
					Dashboard sd = new Dashboard();
					sd.setSrNo(srNo++);
					sd.setStudentId(rs.getInt("student_id"));
					sd.setName(rs.getString("name"));
					sd.setCourse(rs.getString("course_name"));
					sd.setPaidFee(rs.getDouble("paid_fee"));
					sd.setPendingFee(rs.getDouble("pending_fee"));
					sd.setTotalFee(rs.getDouble("total_fee"));
					sd.setSubjects(rs.getString("subjects"));
					sd.setTeachers(rs.getString("teachers"));

					dashboardList.add(sd);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dashboardList;
	}

}
