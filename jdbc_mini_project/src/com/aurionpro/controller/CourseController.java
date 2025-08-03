package com.aurionpro.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.aurionpro.model.Course;
import com.aurionpro.model.Subject;
import com.aurionpro.service.CourseService;

public class CourseController {
	private static Scanner scanner = new Scanner(System.in);
	private static CourseService courseService;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	static {
		courseService = new CourseService();
	}
	
	public CourseController() {
	}
	
	public static void display() {
		try {
			while(true) {
				System.out.println("\n\n1. View all courses \n2. View all subjects \n3. Add new course \n4. Add new subject "
						+ "\n5. Add new subject into course \n6. View subject of course "
						+ "\n7. Search course by id \n8. Search course by name"
						+ "\n9. Delete course by id \n10. Exit");
				
				int userInput = scanner.nextInt();
				
				if(userInput == 10) break;
				
				switch(userInput) {
					case 1 : courseService.showAllCourses();
							break;
					case 2 : courseService.showAllSubjects();
							break;
					case 3 : System.out.println("\n\nEnter course id : ");
							int courseId = scanner.nextInt();
							System.out.println("\nEnter course name : ");
							String courseName = reader.readLine();
							System.out.println("\nEnter course fee : ");
							double courseFee = scanner.nextDouble();
							Course course = new Course(courseId, courseName,courseFee);
							courseService.addCourse(course);
							break;
					case 4 : System.out.println("\n\nEnter subject id : ");
							int subjectId = scanner.nextInt();
							System.out.println("\nEnter subject name : ");
							String subjectName = reader.readLine();
							courseService.addSubject(new Subject(subjectId,subjectName));
							break;
					case 5 : System.out.println("\n\nEnter course id : ");
							courseId = scanner.nextInt();
							System.out.println("\nEnter subject id : ");
							subjectId = scanner.nextInt();
							courseService.addSubjectIntoCourse(courseId, subjectId);;
							break;
					case 6 : System.out.println("\n\nEnter course id : ");
							courseId = scanner.nextInt();
							courseService.showAllSubjectsOfCourse(courseId);
							break;
					case 7 : System.out.println("\n\nEnter course id : ");
							courseId = scanner.nextInt();
							if(courseService.checkIfCourseExist(courseId)) {
								System.out.println("\nCongrats !!!!! It exist.");
							}
							else {
								System.out.println("\nSorry, no course exist with given id.");
							}
							break;
					case 8 : System.out.println("\n\nEnter course name : ");
							courseName = reader.readLine();
							if(courseService.checkIfCourseExist(courseName)) {
								System.out.println("\nCongrats !!!!! It exist.");
							}
							else {
								System.out.println("\nSorry, no course exist with given name.");
							}
							break;
					case 9 : System.out.println("\n\nEnter course id : ");
							courseId = scanner.nextInt();
							courseService.deleteCourse(courseId);
							break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
}
