import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    int id;
    String name;
    ArrayList<Course> registeredCourses = new ArrayList<>();

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("******Course Registration System********");
            System.out.println("1. Add a course");
            System.out.println("2. List available courses");
            System.out.println("3. Register a student");
            System.out.println("4. Register a student for a course");
            System.out.println("5. List registered courses for a student");
            System.out.println("6. Exit");
            System.out.print("*******Enter your choice: 1");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter course code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter course description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter course capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter course schedule: ");
                    String schedule = scanner.nextLine();

                    Course newCourse = new Course(code, title, description, capacity, schedule);
                    courses.add(newCourse);
                    System.out.println("Course added successfully!");
                    break;

                case 2:
                    System.out.println("Available Courses:");
                    for (Course course : courses) {
                        System.out.println("Code: " + course.code);
                        System.out.println("Title: " + course.title);
                        System.out.println("Description: " + course.description);
                        System.out.println("Capacity: " + course.capacity);
                        System.out.println("Schedule: " + course.schedule);
                        System.out.println("--------------");
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();

                    Student newStudent = new Student(studentId, studentName);
                    students.add(newStudent);
                    System.out.println("Student registered successfully!");
                    break;

                case 4:
                    System.out.print("Enter student ID: ");
                    int studentIdToRegister = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.println("Available Courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        System.out.println((i + 1) + ". " + courses.get(i).title);
                    }

                    System.out.print("Select a course to register (enter course number): ");
                    int courseChoice = scanner.nextInt();

                    if (courseChoice > 0 && courseChoice <= courses.size()) {
                        Course selectedCourse = courses.get(courseChoice - 1);
                        Student selectedStudent = null;

                        for (Student student : students) {
                            if (student.id == studentIdToRegister) {
                                selectedStudent = student;
                                break;
                            }
                        }

                        if (selectedStudent != null) {
                            if (selectedStudent.registeredCourses.size() < 3) {
                                if (selectedStudent.registeredCourses.contains(selectedCourse)) {
                                    System.out.println("You are already registered for this course.");
                                } else {
                                    if (selectedCourse.capacity > 0) {
                                        selectedStudent.registeredCourses.add(selectedCourse);
                                        selectedCourse.capacity--;
                                        System.out.println("Registration successful!");
                                    } else {
                                        System.out.println("Sorry, the course is full.");
                                    }
                                }
                            } else {
                                System.out.println("You have reached the maximum number of course registrations.");
                            }
                        } else {
                            System.out.println("Student with ID " + studentIdToRegister + " does not exist.");
                        }
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                    break;

                case 5:
                    System.out.print("Enter student ID to list registered courses: ");
                    int studentIdToDisplay = scanner.nextInt();

                    Student studentToDisplay = null;

                    for (Student student : students) {
                        if (student.id == studentIdToDisplay) {
                            studentToDisplay = student;
                            break;
                        }
                    }

                    if (studentToDisplay != null) {
                        System.out.println("Registered Courses for Student ID " + studentToDisplay.id + ":");
                        for (Course course : studentToDisplay.registeredCourses) {
                            System.out.println("Code: " + course.code);
                            System.out.println("Title: " + course.title);
                            System.out.println("Description: " + course.description);
                            System.out.println("Schedule: " + course.schedule);
                            System.out.println("--------------");
                        }
                    } else {
                        System.out.println("Student with ID " + studentIdToDisplay + " does not exist.");
                    }
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
