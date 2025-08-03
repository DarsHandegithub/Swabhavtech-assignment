package com.aurionpro.service;

import com.aurionpro.dao.CourseDao;
import com.aurionpro.model.Course;
import com.aurionpro.model.Subject;

public class CourseService {
	private CourseDao courseDao = null;
	
	public CourseService() {
		this.courseDao = new CourseDao();
	}
	
	public void showAllCourses() {
		courseDao.showAllCourses();
	}
	
	public void showAllSubjects() {
		courseDao.showAllSubjects();
	}
	
	public void addCourse(Course course) {
		courseDao.addCourse(course);
	}
	
	public void addSubject(Subject subject) {
		courseDao.addSubject(subject);
	}
	
	public void addSubjectIntoCourse(int courseId, int subjectId) {
		courseDao.addSubjectIntoCourse(courseId, subjectId);
	}
	
	public void showAllSubjectsOfCourse(int courseId) {
		courseDao.showAllSubjectsOfCourse(courseId);
	}
	
	public boolean checkIfCourseExist(int courseId) {
		return courseDao.checkIfCourseExist(courseId);
	}
	
	public boolean checkIfCourseExist(String courseName) {
		return courseDao.checkIfCourseExist(courseName);
	}
	
	public void deleteCourse(int courseId) {
		courseDao.deleteCourse(courseId);
	}
	
}
