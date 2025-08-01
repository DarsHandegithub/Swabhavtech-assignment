
package com.aurionpro.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.aurionpro.dao.TeacherDao;
import com.aurionpro.database.Database;
import com.aurionpro.model.Teacher;
import com.aurionpro.model.TeacherProfile;
import com.aurionpro.service.TeacherService;

public class TeacherController {
	
	Scanner scanner = new Scanner(System.in);
	TeacherService service = new TeacherService();
	TeacherDao tm = TeacherDao.getInstance();
	
	public static void main(String[] args) {
		Database db = Database.getInstance();
		db.getConnection();
		TeacherController controller = new TeacherController();
		controller.menu();
	}
	
	public void menu() {
		while (true) {
			System.out.println("\n======= TEACHER MANAGEMENT MENU =======");
			System.out.println("1. View All Teachers");
			System.out.println("2. Add New Teacher");
			System.out.println("3. Assign Subject");
			System.out.println("4. Remove a subject");
			System.out.println("5. Search Teacher");
			System.out.println("6. Delete Teacher");
			System.out.println("7. Activate Teacher");
			System.out.println("8. View Teacher Profile");
			System.out.println("0. Go back");
			System.out.print("Enter your choice: ");
			int choice;
			try {
				choice = scanner.nextInt();
				scanner.nextLine(); 
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please enter a number.");
				scanner.nextLine();
				continue;
			}
			switch (choice) {
			case 1:
				handleShowAllTeachers();
				
				break;
				
			case 2:
				try { 
					System.out.print("Enter Teacher ID: ");
					int teacherId = scanner.nextInt();
					scanner.nextLine();

					System.out.print("Enter First Name: ");
					String fname = scanner.nextLine().trim();

					System.out.print("Enter Last Name: ");
					String lname = scanner.nextLine().trim();
					
					System.out.print("Enter Mobile No (10 digits): ");
					String mobile = scanner.nextLine().trim();
					
					System.out.print("Enter Email ID: ");
					String email = scanner.nextLine().trim();
					
					Teacher teacher = new Teacher(teacherId, fname, lname, mobile, email, true);
					
					System.out.println("Enter City: ");
					String city = scanner.nextLine();
					
					System.out.println("Enter Qualification: ");
					String qualification = scanner.nextLine();
					
					System.out.println("Enter Experience: ");
					int experience = scanner.nextInt();
					
					System.out.println("Enter Salary: ");
					double salary = scanner.nextDouble();
					
					TeacherProfile teacherProfile = new TeacherProfile(teacherId, city, qualification, experience, salary);
					//service.addTeacherProfile(teacherProfile);
					service.addTeacher(teacher, teacherProfile);
					
				} catch (InputMismatchException e) {
					System.out.println("Please enter valid data types.");
					scanner.nextLine(); 
				}
				break;
			
			case 3:
			    try {
			        System.out.print("Enter Course Subject ID: ");
			        String subCourseId = scanner.nextLine().trim();

			        System.out.print("Enter Teacher ID: ");
			        int teacherId = scanner.nextInt();
			        scanner.nextLine();

			        service.assignSubject(subCourseId, teacherId);
			    } catch (InputMismatchException e) {
			        System.out.println("Invalid input! Please enter numeric values where needed.");
			        scanner.nextLine(); 
			    }
			    break;

				
			case 4:
				try {
					System.out.print("Enter Subject_Course ID: ");
					String courseSubId = scanner.nextLine().trim();

					System.out.print("Enter Teacher ID: ");
					int teacherId = scanner.nextInt();
					service.removeASubject(courseSubId, teacherId);
				} catch (InputMismatchException e) {
					System.out.println("ID must be a valid number.");
					scanner.nextLine();
				}
				break;
				
			case 5:
				try {
					System.out.print("Enter Teacher ID to search: ");
					int searchId = scanner.nextInt();
					service.searchATeacher(searchId);
				} catch (InputMismatchException e) {
					System.out.println("ID must be a valid number.");
					scanner.nextLine();
				}
				break;

			case 6:
				try {
					System.out.print("Enter Teacher ID to delete: ");
					int deleteId = scanner.nextInt();
					service.deleteTeacher(deleteId);
				} catch (InputMismatchException e) {
					System.out.println("ID must be a valid number.");
					scanner.nextLine();
				}
				break;

			case 7:
			    try {
			        System.out.print("Enter Teacher ID to activate: ");
			        int teacherId = scanner.nextInt();

			        if (teacherId <= 0) {
			            System.out.println("Teacher ID must be a positive number.");
			            break;
			        }

			        service.activateTeacher(teacherId);
			    } catch (InputMismatchException e) {
			        System.out.println("Please enter a numeric Teacher ID.");
			        scanner.nextLine(); 
			    }
			    break;

			case 8:
				handleShowAllTeacherProfiles();
				break;
			

			case 0:
				System.out.println("Exiting program. Goodbye!");
				scanner.close();
				System.exit(0);

			default:
				System.out.println("Invalid choice.");
			}
		}
	}
	
	private void handleShowAllTeachers() {
	    List<Teacher> teachers = service.fetchAllActiveTeachers();
	    if (teachers.isEmpty()) {
	        System.out.println("No active teachers found.");
	    } else {
	        String format = "| %-5s | %-15s | %-15s | %-15s | %-25s |%n";
	        String line = "+-------+-----------------+-----------------+-----------------+---------------------------+";

	        System.out.println(line);
	        System.out.printf(format, "ID", "First Name", "Last Name", "Mobile", "Email");
	        System.out.println(line);

	        for (Teacher t : teachers) {
	            System.out.printf(format,
	                    t.getTeacherId(),
	                    t.getFirstName(),
	                    t.getLastName(),
	                    t.getMobileNo(),
	                    t.getEmailId());
	        }

	        System.out.println(line);
	    }
	}
	
	private void handleShowAllTeacherProfiles() {
	    List<TeacherProfile> profiles = service.fetchAllTeacherProfiles();

	    if (profiles.isEmpty()) {
	        System.out.println("❌ No teacher profiles found.");
	        return;
	    }

	    String format = "| %-12s | %-15s | %-40s | %-10s | %-10s |%n";
	    String line = "+--------------+-----------------+------------------------------------------+------------+------------+";

	    System.out.println(line);
	    System.out.printf(format, "Teacher ID", "City", "Qualification", "Experience", "Salary");
	    System.out.println(line);

	    for (TeacherProfile tp : profiles) {
	        System.out.printf(format,
	                tp.getTeacherId(),
	                tp.getCity(),
	                tp.getQualification(),
	                tp.getExperience(),
	                String.format("%.2f", tp.getSalary()));
	    }

	    System.out.println(line);
	}


	
}
