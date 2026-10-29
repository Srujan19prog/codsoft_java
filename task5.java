import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String code, title, description, schedule;
    int capacity;
    ArrayList<String> students = new ArrayList<>();

    Course(String code, String title, String description,
           int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    boolean register(String studentId) {
        if (students.contains(studentId)) {
            System.out.println("Already registered for this course.");
            return false;
        }

        if (students.size() >= capacity) {
            System.out.println("Course is full.");
            return false;
        }

        students.add(studentId);
        System.out.println("Registration successful!");
        return true;
    }

    boolean drop(String studentId) {
        if (students.remove(studentId)) {
            System.out.println("Course dropped successfully!");
            return true;
        }

        System.out.println("You are not registered for this course.");
        return false;
    }

    void display() {
        System.out.println("\nCourse Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Seats: "
                + (capacity - students.size()));
    }
}

class Student {
    String id, name;
    ArrayList<Course> registeredCourses = new ArrayList<>();

    Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    void registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("Already registered for this course.");
            return;
        }

        if (course.register(id)) {
            registeredCourses.add(course);
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.contains(course)
                && course.drop(id)) {
            registeredCourses.remove(course);
        } else if (!registeredCourses.contains(course)) {
            System.out.println("This course is not in your registrations.");
        }
    }

    void displayCourses() {
        System.out.println("\nStudent ID: " + id);
        System.out.println("Student Name: " + name);

        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }

        System.out.println("Registered Courses:");
        for (Course course : registeredCourses) {
            System.out.println(course.code + " - " + course.title);
        }
    }
}

public class task5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        courses.add(new Course("C101", "Java",
                "Java Programming", 2, "Monday 10 AM"));
        courses.add(new Course("C102", "Python",
                "Python Programming", 3, "Tuesday 11 AM"));
        courses.add(new Course("C103", "DBMS",
                "Database Management", 2, "Wednesday 2 PM"));

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("Student ID and name cannot be empty.");
            sc.close();
            return;
        }

        Student student = new Student(id, name);
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n===== COURSE REGISTRATION SYSTEM =====");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View My Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter a number from 1 to 5.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    for (Course course : courses) {
                        course.display();
                    }
                    break;

                case 2:
                    System.out.print("Enter course code: ");
                    String registerCode = sc.nextLine().trim();
                    Course registerCourse = findCourse(courses, registerCode);

                    if (registerCourse == null) {
                        System.out.println("Course not found.");
                    } else {
                        student.registerCourse(registerCourse);
                    }
                    break;

                case 3:
                    System.out.print("Enter course code to drop: ");
                    String dropCode = sc.nextLine().trim();
                    Course dropCourse = findCourse(courses, dropCode);

                    if (dropCourse == null) {
                        System.out.println("Course not found.");
                    } else {
                        student.dropCourse(dropCourse);
                    }
                    break;

                case 4:
                    student.displayCourses();
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    static Course findCourse(ArrayList<Course> courses, String code) {
        for (Course course : courses) {
            if (course.code.equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }
}